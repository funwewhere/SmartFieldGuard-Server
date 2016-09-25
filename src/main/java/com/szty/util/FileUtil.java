package com.szty.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.szty.enums.FileTpye;

@Component
public class FileUtil {
	
//	@Value("${fileSavePath}")
	private static String SavePath = "/data/upload";
	
	public String saveFile(FileTpye fileType, MultipartFile imageFile, String saveFilePath) throws Exception  {
		
		if (imageFile.isEmpty() || fileType == null) {
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
		stringBuffer.append(fileType.name());
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
	
	public String saveFileAndMini(FileTpye fileType, MultipartFile originFile, MultipartFile miniFile, String saveFilePath) throws Exception  {
		String savePath = saveFile(fileType, originFile, saveFilePath);
		if(saveFilePath == null) {
			saveFilePath = savePath.substring(savePath.lastIndexOf(fileType.name()) + fileType.name().length() + 1, savePath.lastIndexOf("."));
		}
		saveFile(fileType, miniFile, saveFilePath + "_mini");
		return savePath;
	}
}
