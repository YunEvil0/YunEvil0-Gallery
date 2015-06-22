package com.traveler54.constant;
/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public class StatusCodeConstant {
	public final static int SUCCESSFUL = 200;
	public final static int TB_API_EXCEPTION = 400101;
	public final static int EXCEPTION = 400102;
	public final static String FAILED="400";
	
	public static String EXCEPTION_THRID_API_RESP_INVAILD = "40002001";
	public static String EXCEPTION_THRID_API_CONNECT_FAILD = "40002002";
	public static String EXCEPTION_THRID_API_UNKOWN_EX = "40002003";
	
	public static String EXCEPTION_BIS_WLT_MEMBER_ID_NOT_EXIST="40003001";
	public static String EXCEPTION_BIS_DB_MEMBER_ID_IS_NULL="40003002";
	public static String EXCEPTION_BIS_DATEFORMAT_ERROR="40003003";
	public static String EXCEPTION_BIS_ORDERCREATE_NULL="40003004";
	/**
	 * 文件找不到
	 */
	public static String EXCEPTION_BIS_FILE_NOT_FOUND="40003005";
	/**
	 * 后缀不支持
	 */
	public static String EXCEPTION_BIS_UNSUPPORTEDSUFFIX="40003006";
	/**
	 * 数据采集异常
	 */
	public static String EXCEPTION_BIS_BALANCEDATA="40003007";
	/**
	 * 数据采集异常
	 */
	public static String EXCEPTION_BIS_DATE_ISEMPTY="40003008";

	/** **/
	public static String EXCEPTION_BIS_HD_CAHRGE_POINT_DATA_PARSE_ERROR="40003008";
	public static String EXCEPTION_BIS_HD_CHARGE100_SEND5000_ALL_MEMBER_DATA_PARSE_ERROR="40003018";
	public static String EXCEPTION_BIS_HD_CAHRGE_POINT_DATA_NULL="40003009";
	public static String EXCEPTION_BIS_HD_CHARGE100_SEND5000_ALL_MEMBER_PARAM_ERROR="40003018";
	
	public static String EXCEPTION_BIS_HD_CAHRGE_POINT_NO_PRODUCT_NUM="40003010";
	public static String EXCEPTION_BIS_HD_CREDIT_POINT_NO_PRODUCT_NUM="40003110";

	public static String EXCEPTION_DB_ORDER_REPORT_DATA_NOT_FOUND="40004001";
	public static String EXCEPTION_DB_DATA_INSERT_FAILD="40004002";
	public static String EXCEPTION_DB_DATA_UPDATE_FAILD="40004003";
	public static String EXCEPTION_DB_DATA_NULL="40004004";
	public static String EXCEPTION_DB_QUERY_FAILD="40004005";
	public static String EXCEPTION_DB_QUERY_POINT_FAILD="40004006";
	public static String EXCEPTION_DB_QUERY_PERSON_FAILD="40004007";
	public static String EXCEPTION_DB_INSERT_PERSON_FAILD="40004008";
	public static String EXCEPTION_DB_HD_CREDIT_NOT_FOUND="40004009";
	

	public static String EXCEPTION_WLT_POINT_CONNECT_FAILD="40005001";
	public static String EXCEPTION_WLT_POINT_RESP_NULL="40005002";
	public static String EXCEPTION_WLT_POINT_RETURN_CODE_NULL="40005003";
	public static String EXCEPTION_WLT_POINT_ENCODE_RESP_FAILD="40005004";
}
