package com.gdbd;

/**
 * 常量定义
 * @author zenglongx
 * @date 2014-6-14 下午1:21:35
 */
public class Constants {
	
	
	//-------------------------用户菜单---------------------------//
	/** 我的消息*/
	public static final String ACTIVE_MENU_MESSAGE = "0";
	/** 保单查询*/
	public static final String ACTIVE_MENU_BDQUERY = "1";
	/** 填写保单*/
	public static final String ACTIVE_MENU_BDCREATE = "2";
	/** 申请理赔*/
	public static final String ACTIVE_MENU_LPAPPLY = "3";
	//-------------------------用户菜单---------------------------//
	
	//-------------------------管理员菜单---------------------------//
	/** 申请处理*/
	public static final String ACTIVE_MENU_ADMIN_MESSAGE = "0";
	/** 保单查询*/
	public static final String ACTIVE_MENU_ADMIN_BDQUERY = "1";
	/** 数据统计*/
	public static final String ACTIVE_MENU_ADMIN_STATISTICAL = "2";
	/** 设置管理 */
	public static final String ACTIVE_MENU_ADMIN_SETTING = "3";
	//-------------------------管理员菜单---------------------------//
	
	/**记录状态 正常*/
	public static final int RECORD_STATUS_COMMON = 0;
	/**记录状态 已删除*/
	public static final int RECORD_STATUS_DEL = 1;
}
