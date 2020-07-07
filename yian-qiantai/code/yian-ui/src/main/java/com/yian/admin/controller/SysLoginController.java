package com.yian.admin.controller;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yian.admin.model.SysDoctor;
import com.yian.admin.model.SysDoctorVO;
import com.yian.admin.model.SysMarket;
import com.yian.admin.model.SysSalesman;
import com.yian.admin.security.JwtAuthenticatioToken;
import com.yian.admin.sevice.SysDoctorService;
import com.yian.admin.sevice.SysMarketService;
import com.yian.admin.sevice.SysQRCodeService;
import com.yian.admin.sevice.SysSalesmanService;
import com.yian.admin.util.SecurityUtils;
import com.yian.admin.vo.LoginBean;
import lombok.extern.slf4j.Slf4j;
import com.louis.kitty.core.http.HttpResult;

/**
 * 登录控制器
 * @author earl
 * @date Oct 29, 2018
 */
@Slf4j
@RestController
public class SysLoginController {

	private static final Logger log = LoggerFactory.getLogger(SysLoginController.class);
	

	@Autowired  
	private SysQRCodeService sysQRCodeService;
	 
	
	@Autowired  
	private SysDoctorService sysDoctorService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private SysMarketService marketService;
	/**
	 *  -- 活动二维码在家
	 * @param response
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@GetMapping("/srcmarketjsp")
	public HttpResult market(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");		
	    return HttpResult.ok(sysQRCodeService.findBymarket());
	}
	/**
	 *  --判断医院
	 * @param loginBean
	 * @param response
	 * @param request
	 * @return
	 */
	@PostMapping("/findhospital")
	public HttpResult findhospital(@RequestBody String name)
	{
		
	    //log.info("~~~~~~~~~~~医院名称:"+ name);
	    List<SysDoctorVO>  lisdoctor=sysDoctorService.findhospital(name);
	    //log.info("lisdoctor size: " + lisdoctor.size());
	    return HttpResult.ok(lisdoctor);
	}
	
	
	
	/**
	 * 登录接口
	 */
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
		 String username = "test";//loginBean.getAccount();
		 String password = "123456";//loginBean.getPassword();	
		 log.info("-----------------------------------------------------");
		 SysMarket sysmarket= new SysMarket();
		 sysmarket.setTel(loginBean.getTel());//客户手机
		 sysmarket.setName(loginBean.getName());//客户名称
		 sysmarket.setHospital(loginBean.getHospitalName());//医院名称
		 sysmarket.setDepartmentoffices(loginBean.getDepartmentoffices());//详细地址
		 sysmarket.setAddress(loginBean.getAddress());//详细地址
		 sysmarket.setSellname(loginBean.getSellname());//销售名称
		 sysmarket.setActivityname(loginBean.getActivityname());//活动名
		 sysmarket.setQrcodeId(loginBean.getQrcodeId());//活动名id
		 JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
		 /* SysDoctor strhospitalName=sysDoctorService.findandhospitalName(loginBean.getHospitalName());
		  if(null ==  strhospitalName) //医院名称
		  {		  
			 String strhospital="医院名称不存在！ ";
			  token.setStrmsg(strhospital);	
		  }
		  SysSalesman salesname=sSysSalesmanService.findandName(loginBean.getSellname());//销售名称
		  if( null == salesname) //销售人员
		  {
			  String sales="销售名称不存在！";
			  token.setStrmsg(sales);	
		  }
		  if( null != strhospitalName &&  null != salesname)
		  {*/
			  String  strsave= marketService.vueSave(sysmarket);
			  token.setStrmsg(strsave);
		 // }		 
		  //log.info("token:"+token.getStrmsg());
		return HttpResult.ok(token);
	}
	@PostMapping(value = "/getSalesNames")
	public HttpResult getSalesNames() {
		return HttpResult.ok(marketService.getSalesNames());
	}
}
