package com.xxx.util;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


/**
 * 文件自处理类
 * 
 * @author Administrator
 * 
 */
public class FileUtil {

	/**
	 * 获得文件后缀
	 * @param fileName
	 * @return
	 */
	public String getFileExtName(String fileName){
		if(fileName.lastIndexOf(".") > 0){
			return fileName.substring(fileName.lastIndexOf(".")+1);
		}
		return null;
	}
	
	/**
	 * 判断文件后缀是否符合限制
	 * @param suffixSet
	 * @param ext_name
	 * @return
	 */
	public boolean isAvailableExtName(List<String> suffix,String ext_name){
		if(suffix==null || StringUtils.isEmpty(ext_name)){
			return false;
		}
		for(String str:suffix){
			if(str.equalsIgnoreCase(ext_name)){
				return true;
			}
		}
		return true;
	}
	
	/**
	 * 通过文件头部特征码，判断是否符合限制
	 * @param bytes
	 * @param fileTypePreFix
	 * @return
	 */
	public boolean isAvailableFileType(byte[] bytes,List<String> fileTypePreFixList){
		String fileHexString = this.getFileHexString(bytes);
		for(String preFix:fileTypePreFixList){
			if(fileHexString.toUpperCase().startsWith(preFix)){
				return true;
			}
		}
		return false;
	}
	
	public String getFileHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
