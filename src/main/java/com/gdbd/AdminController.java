package com.gdbd;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.Page;


@Before({BDInterceptor.class, SessionInViewInterceptor.class})
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
		User user = this.getSessionAttr("user");
		int top = 5; //前多少条通知用于首页显示
		//保单受理通知
		List<Message> bdslMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=0 order by create_time desc", Message.MESSAGE_TYPE_BDSL).getList();
		//理赔受理通知
		List<Message> lpslMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=0 order by create_time desc", Message.MESSAGE_TYPE_LPSL).getList();
		//系统通知
		List<Message> sysMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=0 order by create_time desc", Message.MESSAGE_TYPE_SYS).getList();
		
		setAttr("bdslMsgs", bdslMsgs);
		setAttr("lpslMsgs",lpslMsgs);
		setAttr("sysMsgs", sysMsgs);
		
		setAttr("activeMenu", Constants.ACTIVE_MENU_ADMIN_MESSAGE);
		render("/admin/message.html");
	}
	
	/** 数据统计 */
	public void statisticalChart(){
		setAttr("activeMenu", Constants.ACTIVE_MENU_ADMIN_STATISTICAL);
		render("/admin/chart.html");
	}
	
	/** 设置管理 */
	public void setting(){
		setAttr("activeMenu", Constants.ACTIVE_MENU_ADMIN_SETTING);
		int pageNumber = getParaToInt("pageSize", 1); 
		int pageSize = getParaToInt("pageSize", 10);
		String select = "select *";
		String sqlExceptSelect = "from user where record_status=? and role <> 2 order by create_time desc";
		Page<User> users = User.dao.paginate(pageNumber, pageSize,select,sqlExceptSelect,Constants.RECORD_STATUS_COMMON);
		setAttr("users", users);
		render("/admin/setting.html");
	}
	
	/** 添加一个用户 */
	public void settingAddUser(){
		
	}
	
	/** 保存一个用户 */
	public void settingSaveUser(){
		
	}
	
	/** 编辑一个用户 */
	public void settingEditUser(){
		User user = User.dao.findById(getPara("userId"));
		setAttr("user", user);
		setAttr("activeMenu", Constants.ACTIVE_MENU_ADMIN_SETTING);
		render("/admin/setting-user-edit.html");
	}
	
	/** 更新一个用户 */
	public void settingUpdateUser(){
		
		redirect("/admin/setting");
	}
	
	/** 删除一个用户 */
	public void settingRemoveUser(){
		User.dao.findById(getPara("userId")).set("record_status", Constants.RECORD_STATUS_DEL).update();
		redirect("/admin/setting");
	}
	
	/** 保单审批 */
	public void approveBd(){
		
		if(!getPara("messageId", "").equals("")){
			Message.dao.findById(getPara("messageId")).set("read_flag", Message.MESSAGE_READ).update();
		}
		//查询保单类别
		Baodan baodan = Baodan.dao.findById(getParaToInt("baodanId"));
		if(baodan.getInt("assurance_class_id") == AssuranceClass.CLASS_ZHX){//综合险
			forwardAction("/zhxbaodan/approve");
		}else if(baodan.getInt("assurance_class_id") == AssuranceClass.CLASS_ZRX){//责任险
			forwardAction("/zrxbaodan/approve");
		}
	}
	
	/** 审批保存 */
	public void approveBdSave(){
		Baodan baodan = Baodan.dao.findById(getParaToInt("baodanId"));
		if(getPara("approve_result").equals("1")){
			baodan.set("status", Baodan.STATUS_APPROVE_OK);
		}else{
			baodan.set("status", Baodan.STATUS_APPROVE_GOBACK);
			baodan.set("reason", getPara("reason"));
		}
		baodan.update();
		redirect("/admin/index");
	}
	
	/** 保单查询 */
	public void bdQuery(){
		User user = this.getSessionAttr("user");
		int pageNumber = getParaToInt("pageSize", 1); 
		int pageSize = getParaToInt("pageSize", 10);
		String select = "select *";
		String sqlExceptSelect = "from baodan where record_status=? order by create_time desc";
		Page<Baodan> baodans = Baodan.dao.paginate(pageNumber, pageSize,select,sqlExceptSelect,
			Constants.RECORD_STATUS_COMMON);
		setAttr("baodans", baodans);
		setAttr("activeMenu", Constants.ACTIVE_MENU_BDQUERY);
		render("/admin/bd-list.html");
	}
	
}


