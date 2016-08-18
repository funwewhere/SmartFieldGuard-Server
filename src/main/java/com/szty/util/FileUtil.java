package com.szty.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	
//	private final static String SavePath = "E:/Myphoto/temp";

	private final static String SavePath = "/data/upload";
	
	public String saveFile(String type, MultipartFile imageFile, String saveFilePath) throws Exception  {
		if (imageFile.isEmpty() || type == null || type.isEmpty()) {
			throw new Exception("错误上传参数");
		}
		
		if (saveFilePath == null || StringUtils.isBlank(saveFilePath)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
			saveFilePath = format.format(new Date()) +"/" + RandomNumberUtil.getValidateCode(8);
		}
		saveFilePath = saveFilePath.trim();
		String fileName = imageFile.getOriginalFilename();
		int index = fileName.lastIndexOf(".");
		StringBuffer stringBuffer = new StringBuffer("/SZTY");
		stringBuffer.append("/");
		stringBuffer.append(type);
		stringBuffer.append("/");
		stringBuffer.append(saveFilePath);
		stringBuffer.append(fileName.substring(index));
		String filePath = stringBuffer.toString();
		File file = new File(SavePath + filePath);
		if(!file.exists()){
			file.mkdirs();
			file.createNewFile();
		}
		imageFile.transferTo(file);

		return filePath;
	}
}
