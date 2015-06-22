package com.traveler54.util;

import java.util.regex.Pattern;

public class StringFilterUtil {

	public static String filter(String orginText) {
		String text = orginText;
		try {
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
			String regEx_html1 = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html2 = "<[^>]+";// 定义HTML标签的正则表达式
			String regEx_spec1 = "&nbsp;";// 定义空格标签的正则表达式
			String regEx_spec2 = "\n";//定义换行标签的正则表达式
			text = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE)
					.matcher(text).replaceAll("");
			text = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE)
					.matcher(text).replaceAll("");
			text = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE)
					.matcher(text).replaceAll("");
			text = Pattern.compile(regEx_html2, Pattern.CASE_INSENSITIVE)
					.matcher(text).replaceAll("");
			text = Pattern.compile(regEx_spec1, Pattern.CASE_INSENSITIVE)
					.matcher(text).replaceAll("");
			text = Pattern.compile(regEx_spec2, Pattern.CASE_INSENSITIVE)
					.matcher(text).replaceAll("");
		} catch (Exception e) {
			System.err.println("StringFilter:" + e.getMessage());
		}
		return text;
	}

	/**
	 * 截取一段字符的长度(汉、日、韩文字符长度为2),不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * @param str
	 *            原始字符串
	 * @param specialCharsLength
	 *            截取长度(汉、日、韩文字符长度为2)
	 * @return
	 */
	public static String trim(String str, int specialCharsLength) {
		if (str == null || "".equals(str) || specialCharsLength < 1) {
			return "";
		}
		char[] chars = str.toCharArray();
		int charsLength = getCharsLength(chars, specialCharsLength);
		return new String(chars, 0, charsLength);
	}

	/**
	 * 获取一段字符的长度，输入长度中汉、日、韩文字符长度为2，输出长度中所有字符均长度为1
	 * 
	 * @param chars
	 *            一段字符
	 * @param specialCharsLength
	 *            输入长度，汉、日、韩文字符长度为2
	 * @return 输出长度，所有字符均长度为1
	 */
	private static int getCharsLength(char[] chars, int specialCharsLength) {
		int count = 0;
		int normalCharsLength = 0;
		for (int i = 0; i < chars.length; i++) {
			int specialCharLength = getSpecialCharLength(chars[i]);
			if (count <= specialCharsLength - specialCharLength) {
				count += specialCharLength;
				normalCharsLength++;
			} else {
				break;
			}
		}
		return normalCharsLength;
	}

	/**
	 * 获取字符长度：汉、日、韩文字符长度为2，ASCII码等字符长度为1
	 * 
	 * @param c
	 *            字符
	 * @return 字符长度
	 */
	private static int getSpecialCharLength(char c) {
		if (isLetter(c)) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param char c, 需要判断的字符
	 * @return boolean, 返回true,Ascill字符
	 */
	private static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

}
