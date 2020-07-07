package com.yian.admin.sevice;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.yian.admin.model.SysDoctor;
import com.yian.admin.model.SysDoctorVO;
import com.louis.kitty.core.service.CurdService;

/**
 * 客户管理管理
 * @author earl
 * @date august 8, 2019
 */
public interface SysDoctorService extends CurdService<SysDoctor> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysDoctor> findByLable(String lable);


	List<SysDoctor> findMarPage();


	String batchImport(String fileName, MultipartFile file) throws Exception;


	SysDoctor findNameandTel(String name, String tel);


	List<SysDoctorVO> findhospital(String hospitalName);

}
