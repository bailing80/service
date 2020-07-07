package com.yian.admin.sevice.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yian.admin.dao.SysExdelMapper;
import com.yian.admin.dao.SysExdelReturnMapper;
import com.yian.admin.dao.SysMarketMapper;
import com.yian.admin.model.SysClientAndExpress;
import com.yian.admin.model.SysExdel;
import com.yian.admin.model.SysExdelReturn;
import com.yian.admin.model.SysMarket;
import com.yian.admin.sevice.SysExdelService;
import com.yian.admin.util.MD5Utils;
import com.louis.kitty.common.utils.DateTimeUtils;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
/**
 * 
 * @author huigai
 * augest 22 2019
 * 快递信息维护管理
 *  
 */

@Service
@Slf4j
public class SysExdelServiceImpl  implements SysExdelService {
    
	private static final Logger log = LoggerFactory.getLogger(SysExdelServiceImpl.class);
	
	private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel

	//实时查询请求地址

	private static final String SYNQUERY_URL = "http://poll.kuaidi100.com/poll/query.do";
	private String key = "nDyKLnYO2555";							//贵司的授权key
	private String customer = "45C58167AC05BEB4B6E7B8132B2CC243";	//贵司的查询公司编号

	@Autowired
	private SysExdelMapper sysExdelMapper;
	@Autowired
	private SysMarketMapper sysMarketMapper;
	@Autowired
	private SysExdelReturnMapper sysExdelReturnMapper;

	@Override
	public int save(SysExdel record)
	{
		log.info("Enter SysExdelServiceImpl save function:");
		//record.setGiftSend("Yes");
		record.setCreatetime(DateTimeUtils.getDateTime());
		//log.info("-系统时间 --:"+DateTimeUtils.getDateTime());
		if(record.getId() == null || record.getId() == 0) {	
			
			return sysExdelMapper.insertSelective(record);			
		}

		return sysExdelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysExdel record) {
		return sysExdelMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysExdel> records) {
		for(SysExdel record:records) {
			delete(record);
		}
		return 1;
	}
	
	@Override
	public List<SysClientAndExpress> leadout()
	{
		log.info("Enter SysExdelServiceImpl leadout function:");
		
		syncMarketToExdel();
		
		List<SysClientAndExpress> records = sysExdelMapper.exportClientAndExpress();
		for(SysClientAndExpress record:records) {
			log.info("name: " + record.getName() + ",tel: " + record.getTelephone() + ",address: " + record.getToLocation() 
					+ ",Expressname: " + record.getExpressName() + ",ExpressNumber:" + record.getExpressNumber() 
					+ ",createTime:" + record.getCreatetime()); 
		}
		return records;
	}
	
   @Transactional(readOnly = false,rollbackFor = Exception.class)
   @Override
   public String batchImport(String fileName, MultipartFile file) throws Exception 
   {
	   String str="导入数据成功";
	   if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$"))
	   {
	       throw new Exception("上传文件格式不正确");
	   }
	   List<SysExdel> records=new ArrayList<>();
	   try {
	       InputStream in =file.getInputStream();	     
		    // 创建Excel工作薄
		   Workbook work = this.getWorkbook(in, fileName);
		   if (null == work) {
		       throw new Exception("创建Excel工作薄为空或excel格式不对！");
		   }
		   in.close();
		   Sheet sheet = work.getSheetAt(0);
		   //遍历行
		   for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum() + 1; i++) 
		   {
		       Row row= sheet.getRow(i);
		       if (row == null || i == 0)
		       {
		           continue;
		       }
		       
		       SysExdel exdel = new SysExdel();
		       exdel.setGiftSend("Yes");
			   //遍历列
		       for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) 
		       {           	   	   
		           Cell cell = row.getCell(j);
		           //{0=序号, 1=客户名称, 2=联系方式, 3=目的地, 4=发送地, 5=快递名称, 6=快递单号，7=创建时间}           
		           Object cellValue = this.getCellValue(cell);              	   
		           if(cell != null)
		           {
		               switch (j){
		                   case 0 :                 	   
		                       //exdel.setId(cellValue.toString());
		                       break;
		                   case 1:
		                	   exdel.setName(cellValue.toString());
		                       break;
		                   case 2:
		                	   exdel.setTelephone(cellValue.toString());
		                       break;
		                   case 3: 
		                	   exdel.setToLocation(cellValue.toString());
		                       break;
		                   case 4: 
		                	   exdel.setFromLocation(cellValue.toString());
		                       break;
		                   case 5: 
		                	   exdel.setExpressName(cellValue.toString());
		                       break;
		                   case 6: 
		                	   exdel.setExpressNumber(cellValue.toString());
		                       break;
		                   case 7: 
		                	   exdel.setCreatetime(DateTimeUtils.getDateTime().toString());
		                       break;   
		                   default:
		                       continue;
		                       }
		               }
		           }
					if(exdel != null )
					{
					    records.add(exdel);
					    //log.info("已存在 :"+exdel.getName()+" 手机号:"+exdel.getTelephone());  
						SysExdel tempExdel=sysExdelMapper.findNameandTel(exdel.getName(),exdel.getTelephone());   
						if(tempExdel == null )
						{
							log.info("成功插入数据：客户名称" + exdel.getName()+" 手机号:"+exdel.getTelephone());
							sysExdelMapper.insertSelective(exdel);
						}
						else
						{
							log.info("该客户已存在，更新快递状态！");
							log.info("name: " + exdel.getName() + ",tel: " + exdel.getTelephone() + ",address: " + exdel.getToLocation() 
							+ ",Expressname: " + exdel.getExpressName() + ",ExpressNumber:" + exdel.getExpressNumber() 
							+ ",createTime:" + exdel.getCreatetime()); 
							sysExdelMapper.updateExpressInfo(exdel.getName(),exdel.getTelephone(),exdel.getFromLocation(),exdel.getExpressName(),
									exdel.getExpressNumber(),exdel.getGiftSend());
							str="0000";
						}
					}
		       }
		   } catch (Exception e) {
			   	log.error(String.format("parse excel exception!"), e);
		   }
	   return str;
   }
   
   public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception
   {
       Workbook wb = null;
       String fileType = fileName.substring(fileName.lastIndexOf("."));
       if (EXCEL_2003_DOWN.equals(fileType)) {
    	   wb = new HSSFWorkbook(inStr); // 2003-
       } else if (EXCEL_2007_UP.equals(fileType)) {
    	   wb = new XSSFWorkbook(inStr); //2007+
       }
       return wb;
   }
   
   private Object getCellValue(Cell cell) 
   {
       Object value = null;
       DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
       switch (cell.getCellType()) 
       {
	       case Cell.CELL_TYPE_STRING:
	           value = cell.getRichStringCellValue().getString();
	           break;
	       case Cell.CELL_TYPE_NUMERIC:
	    	   value=df.format(cell.getNumericCellValue());
		       break;
		   case Cell.CELL_TYPE_BOOLEAN:
		       value = cell.getBooleanCellValue();
		       break;
		   case Cell.CELL_TYPE_BLANK:
		       value = "";
	               break;
	       default:
	           break;
       }
       return value;
   }


	@Override
	public void syncMarketToExdel()
	{
		//log.info("Enter SysExdelServiceImpl syncMarketToExdel function:");
		//导出客户信息并保存在快递表中
		List<SysMarket> clientRecords = sysMarketMapper.findPage();
		//long id = 1;
		for(SysMarket clientRecord:clientRecords) {
			//log.info("name: " + clientRecord.getName() + ",tel: " + clientRecord.getTel() + ",address: " + clientRecord.getAddress()); 
			
			SysExdel record = new SysExdel();
			record.setName(clientRecord.getName());
			record.setTelephone(clientRecord.getTel());
			record.setToLocation(clientRecord.getAddress());
			save(record);
		}
	}
	
	@Override
	public SysExdel findById(Long id) {
		return sysExdelMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		String name=columnFilter.getValue();
		
		if(columnFilter != null) {
			log.debug("exdelServiceImpl:"+name);
			return MybatisPageHelper.findPage(pageRequest, sysExdelMapper, "findPageByLabel",columnFilter.getValue());
		}
		
		log.debug("exdelServiceImpl:"+name);
		return MybatisPageHelper.findPage(pageRequest, sysExdelMapper);
	}

	/**
	 * 获取过滤字段的值
	 * @param filterName
	 * @return
	 */
	public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
		String value = null;
		ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
		if(columnFilter != null) {
			value = columnFilter.getValue();
		}
		return value;
	}
	
	@Override
	public List<SysExdel> findByLable(String name) {
		return sysExdelMapper.findByLable(name);
	}
	
   @Override
	public List<SysExdel> findMarPage()
	{
	   log.info("----------------------");
	   return sysExdelMapper.findPage();
	}

   @Override
   public int queryExpInfo(SysExdel exdel) {
	   log.info("Enter SysExdelServiceImpl queryExpInfo function:");
		int resultv2 = 0;				//开启行政规划解析
		String result = synQueryData(exdel.getExpressName(), exdel.getExpressNumber(), exdel.getTelephone(), 
				exdel.getFromLocation(), exdel.getToLocation(), resultv2);
		log.info("Query result: " + result);
		return 1;
   }
   /**
	 * 实时查询快递单号
	 * @param com			快递公司编码
	 * @param num			快递单号
	 * @param phone			手机号
	 * @param from			出发地城市
	 * @param to			目的地城市
	 * @param resultv2		开通区域解析功能：0-关闭；1-开通
	 * @return
	 */
	public String synQueryData(String com, String num, String phone, String from, String to, int resultv2) {

		StringBuilder param = new StringBuilder("{");
		param.append("\"com\":\"").append(com).append("\"");
		param.append(",\"num\":\"").append(num).append("\"");
		param.append(",\"phone\":\"").append(phone).append("\"");
		param.append(",\"from\":\"").append(from).append("\"");
		param.append(",\"to\":\"").append(to).append("\"");
		if(1 == resultv2) {
			param.append(",\"resultv2\":1");
		} else {
			param.append(",\"resultv2\":0");
		}
		param.append("}");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("customer", this.customer);
		String sign = MD5Utils.encode(param + this.key + this.customer);
		params.put("sign", sign);
		params.put("param", param.toString());
		
		String resultStr = this.post(params);
		log.info(resultStr);
		SysExdelReturn recordExdelReturn = new SysExdelReturn();
		JSONObject jsonArray = JSONObject.fromObject(resultStr);
		recordExdelReturn.setMessage(jsonArray.getString("message"));
		recordExdelReturn.setState(Integer.parseInt(jsonArray.getString("state")));
		recordExdelReturn.setStatusExp(Integer.parseInt(jsonArray.getString("status")));
		recordExdelReturn.setConditionA(jsonArray.getString("condition"));
		recordExdelReturn.setIscheck(Integer.parseInt(jsonArray.getString("ischeck")));
		recordExdelReturn.setCom(jsonArray.getString("com"));
		recordExdelReturn.setNu(jsonArray.getString("nu"));
		recordExdelReturn.setData(jsonArray.getString("data"));
		sysExdelReturnMapper.insertSelective(recordExdelReturn);
		
		log.info(jsonArray.getString("message"));
		log.info(jsonArray.getString("state"));
		log.info(jsonArray.getString("status"));
		log.info(jsonArray.getString("condition"));
		log.info(jsonArray.getString("ischeck"));
		log.info(jsonArray.getString("com"));
		log.info(jsonArray.getString("nu"));
		log.info(jsonArray.getString("data"));
		return resultStr;
	}
	
	/**
	 * 发送post请求
	 */
	public String post(Map<String, String> params) {
		StringBuffer response = new StringBuffer("");
		
		BufferedReader reader = null;
		/**
		try {
			StringBuilder builder = new StringBuilder();
			for (Map.Entry<String, String> param : params.entrySet()) {
				if (builder.length() > 0) {
					builder.append('&');
				}
				builder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				builder.append('=');
				builder.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			byte[] bytes = builder.toString().getBytes("UTF-8");

			URL url = new URL(SYNQUERY_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setRequestMethod("POST");*/
			//conn.setRequestProperty("accept", "*/*");
			/**conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(bytes.length));
			conn.setDoOutput(true);
			conn.getOutputStream().write(bytes);

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			String line = "";
           while ((line = reader.readLine()) != null) {
        	   log.info("Return info: " + line);
        	   response.append(line);
           }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}**/
		//测试返回数据实例
		String returnstr="{\r\n" + 
				"	\"message\": \"ok\",\r\n" + 
				"	\"state\": \"0\",\r\n" + 
				"	\"status\": \"200\",\r\n" + 
				"	\"condition\": \"F00\",\r\n" + 
				"	\"ischeck\": \"0\",\r\n" + 
				"	\"com\": \"yuantong\",\r\n" + 
				"	\"nu\": \"V030344422\",\r\n" + 
				"	\"data\": [{\r\n" + 
				"			\"context\": \"上海分拨中心/装件入车扫描 \",\r\n" + 
				"			\"time\": \"2012-08-28 16:33:19\",\r\n" + 
				"			\"ftime\": \"2012-08-28 16:33:19\",\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"context\": \"上海分拨中心/下车扫描 \",\r\n" + 
				"			\"time\": \"2012-08-27 23:22:42\",\r\n" + 
				"			\"ftime\": \"2012-08-27 23:22:42\",\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
		response.append(returnstr);
		return response.toString();
	}
}
