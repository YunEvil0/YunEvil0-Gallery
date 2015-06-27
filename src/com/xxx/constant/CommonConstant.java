package com.xxx.constant;
/**
 * 公用常量类
 * @author EX-WANGXIAOYU590
 *
 */
public class CommonConstant {
	/** 日志-调用外部接口日志 **/
	public static final String LOG_TYPE_API="apiLog";
	/** 日志-抓数据任务日志 **/
	public static final String LOG_TYPE_TASK="taskLog";
	/** 日志-抓数据日志 **/
	public static final String LOG_TYPE_CATCH="catchLog";
	/** 日志-WLT POINT日志 **/
	public static final String LOG_TYPE_WLT_POINT="wltPointLog";
	
	/** 业务系统，配置，key常量 -淘宝帐号 **/
	public static final String TBK_CONFIG_TB_ACCOUNT="tb1";
	
	/** 配置-KEY-淘宝开放平台接 URL **/
	public static final String CONFIG_OPEN_TB_API_URL="openTBApi_url";
	/** 配置-KEY-淘宝开放平台接 APPKEY **/
	public static final String CONFIG_OPEN_TB_API_APPKEY="openTB_appKey";
	/** 配置-KEY-淘宝开放平台接 APPSECRET **/
	public static final String CONFIG_OPEN_TB_API_APPSECRET="openTB_appSecret";
	/** 配置-KEY-获取万里通用户名URL **/
	public static final String CONFIG_GETWAY_GETWLTUSERNAME="getway_url_getwltusername";
	/** 配置-KEY-信用卡频道10元发送短信-开关**/
	public static final String CONFIG_GETWAY_POINT_SEND_MASSAGE_SWITCH="wlt_send_message_call_switch";
	
	/** 配置-KEY-message.i-che.net 短信平台开关 **/
	public static final String CONFIG_GETWAY_SMS_SWITCH="message_platform_sms_switch";
	/** 配置-KEY-message.i-che.net 短信平台下行url **/
	public static final String CONFIG_GETWAY_SMS_PLATFORM_URL="message_platform_sms_platform_url";
	/** 配置-KEY-message.i-che.net 短信平台clientId **/
	public static final String CONFIG_GETWAY_SMS_CLIENT_ID="message_platform_client_id";
	/** 配置-KEY-message.i-che.net 短信平台client md5 key **/
	public static final String CONFIG_GETWAY_SMS_CLIENT_MD5_KEY="message_platform_md5_key";
	
	
	/** 配置-KEY-万里通积分BIS接口-开关**/
	public static final String CONFIG_GETWAY_WLT_POINT_CALL_SWITCH="wlt_point_call_switch";
	/** 配置-KEY-万里通会员信息BIS接口-开关**/
	public static final String CONFIG_GETWAY_WLT_MEMBER_CALL_SWITCH="wlt_member_call_switch";
	/** 配置-KEY-万里通积分BIS接口-请求地址**/
	public static final String CONFIG_GETWAY_WLT_POINT_SERVICE_URL="wlt_point_service_url";
	/** 配置-KEY-万里通积分BIS接口-SSL-keystore**/
	public static final String CONFIG_GETWAY_WLT_POINT_SERVICE_KEYSTORE="wlt_point_service_keyStore";
	/** 配置-KEY-万里通积分BIS接口-SSL-keyStorePassword**/
	public static final String CONFIG_GETWAY_WLT_POINT_SERVICE_KEYSTOREPASSWORD="wlt_point_service_keyStorePassword";
	/** 配置-KEY-万里通积分BIS接口-SSL-trustStore**/
	public static final String CONFIG_GETWAY_WLT_POINT_SERVICE_TRUSTSTORE="wlt_point_service_trustStore";
	/** 配置-KEY-万里通积分BIS接口-SSL-trustStorePassword**/
	public static final String CONFIG_GETWAY_WLT_POINT_SERVICE_TRUSTSTOREPASSWORD="wlt_point_service_trustStorePassword";
	/** 配置-KEY-万里通积分BIS接口-SSL-charset**/
	public static final String CONFIG_GETWAY_WLT_POINT_SERVICE_CHARSET="wlt_point_service_charset";
	
	/** 配置-KEY-万里通积分产品模块-合作伙伴**/
	public static final String CONFIG_GETWAY_WLT_POINT_SERVICE_POINT_PARTNER="wlt_point_service_pointPartner";
	/** 配置-KEY-万里通积分产品模块-产品编码-充话费送500积分**/
	public static final String CONFIG_WLT_POINT_PRODUCT_NO="wlt_point_productNo";
	/** 配置-KEY-万里通积分产品模块-产品编码-充话费送5000积分**/
	public static final String CONFIG_WLT_POINT_PRODUCT_NO_HD_CHARGE_2="wlt_point_productNo_hdcharge_2";
	/** 配置-KEY-万里通积分产品模块-产品编码-信用卡频道送10元**/
	public static final String CONFIG_CREDIT_POINT_PRODUCT_NO="wlt_point_productNo_hdcredit";
	
	/** 配置-KEY-活动-充话费非虚拟类目id**/
	public static final String CONFIG_HD_CHARGE_SEND5000_ALL_INVENTED_PARTNERID="all_invented_categoryId";
	/** 配置-KEY-活动-充话费送5000积分-订单创建时间-开始时间**/
	public static final String CONFIG_HD_CHARGE_SEND5000_START_TIME="chargeTBMemberOrderStartTime";
	/** 配置-KEY-活动-充话费送5000积分-订单创建时间-结束时间**/
	public static final String CONFIG_HD_CHARGE_SEND5000_END_TIME="chargeTBMemberOrderEndTime";
	/** 配置-KEY-活动-信用卡频道送10元积分-订单创建时间-开始时间**/
	public static final String CONFIG_HD_CREDIT_SEND_START_TIME="creditSendPointStartTime";
	/** 配置-KEY-活动-信用卡频道送10元积分-订单创建时间-结束时间**/
	public static final String CONFIG_HD_CREDIT_SEND_END_TIME="creditSendPointEndTime";
	
	
	
	/**　lastModifyBy SYSTEM **/
	public static final short SYSTEM_MODIFY=0;
	/** 数据来源 淘宝**/
	public static final short SOURCE_ID_TB=1;
	/** 数据来源 淘宝客**/
	public static final short SOURCE_ID_TBK=2;
	/** 数据来源 天猫**/
	public static final short SOURCE_ID_TMALL=3;
	/** 数据来源 聚划算**/
	public static final short SOURCE_ID_JU=4;
	
	/** Freemaker常量配置-积分模版  **/
	public static final String FREEMAKER_TEMPLATE_FILE_CREATE_CREDIT="/createCreditHtml.ftl";
	/** Freemaker常量配置-积分静态页  **/
	public static final String FREEMAKER_HTML_FILE_CREATE_CREDIT_HTML="/lastestPoint.html";
	/** Freemaker常量配置-HD_DAREN模版  **/
	public static final String FREEMAKER_TEMPLATE_FILE_HD_DAREN="/darenTop.ftl";
	/** Freemaker常量配置-HD_DAREN静态页  **/
	public static final String FREEMAKER_HTML_FILE_HD_DAREN_HTML="/darenTop.js";
	
}
