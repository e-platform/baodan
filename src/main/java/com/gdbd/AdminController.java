package com.gdbd;

import com.jfinal.aop.Before;


@Before(BDInterceptor.class)
public class AdminController extends BaseController {
	/**
	 * method:
	 * 		1、message()		//首页消息
	 * 		2、messageList()		//所有消息列表
	 * 		3、messageDetail()	//消息详情
	 * 		4、acceptBd()		//保单受理
	 * 		5、acceptBdSave()	//保单受理结果保存
	 * 		6、bdQuery()			//保单查询
	 * 		7、statisticalChart()	//统计图表
	 * 		8、setting()			//设置管理
	 * 		9、settingSave()		//设置保存
	 * 		10、settingAddUser()	//设置添加一个用户
	 * 		11、settingSaveUser()	//设置保存一个用户
	 * 		12、settingRemoveUser()	//设置删除一个用户
	 * 		13、settingEditUser()	//设置编辑一个用户
	 * 		14、settingUpdateUser()	//设置更新一个用户
	 * 
	 */
	
	/** 管理员首页 */
	public void index(){
		redirect("/message/indexAdmin");
	}
	
	/** 数据统计 */
	public void statisticalChart(){
		
	}
	
	/** 设置管理 */
	public void setting(){
		
	}
	
	/** 添加一个用户 */
	public void settingAddUser(){
		
	}
	
	/** 保存一个用户 */
	public void settingSaveUser(){
		
	}
	
	/** 编辑一个用户 */
	public void settingEditUser(){
		
	}
	
	/** 更新一个用户 */
	public void settingUpdateUser(){
		
	}
	
	/** 删除一个用户 */
	public void settingRemoveUser(){
		
	}
	
	/** 保单审批 */
	public void approveBd(){
		
	}
	
	/** 审批保存 */
	public void approveBdSave(){
		
	}
}


