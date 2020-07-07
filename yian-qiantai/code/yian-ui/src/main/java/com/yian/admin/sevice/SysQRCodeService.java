package com.yian.admin.sevice;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.yian.admin.model.SysQRCode;
import com.louis.kitty.core.service.CurdService;

/**
 * 二维码管理
 * @author huigai
 * @date august 22, 2019
 */
public interface SysQRCodeService extends CurdService<SysQRCode> {

	List<SysQRCode> findByLable(String lable);

	List<SysQRCode> findMarPage();
	
	int importQrcode(MultipartFile file, String activityName) throws Exception;

	String StrResult();

	SysQRCode findBymarket();

}
