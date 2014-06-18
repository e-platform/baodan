package com.gdbd;

import java.text.SimpleDateFormat;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.Page;


@Before({BDInterceptor.class, SessionInViewInterceptor.class})
public class MessageController extends BaseController{
	
	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/** 消息首页 */
	public void index(){
		User user = this.getSessionAttr("user");
		int top = 5; //前多少条通知用于首页显示
		//保单受理通知
		List<Message> bdslMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=? order by create_time desc", Message.MESSAGE_TYPE_BDSL, user.getInt("id")).getList();
		//理赔受理通知
		List<Message> lpslMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=? order by create_time desc", Message.MESSAGE_TYPE_LPSL, user.getInt("id")).getList();
		//系统通知
		List<Message> sysMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=? order by create_time desc", Message.MESSAGE_TYPE_SYS, user.getInt("id")).getList();
		
		setAttr("bdslMsgs", bdslMsgs);
		setAttr("lpslMsgs",lpslMsgs);
		setAttr("sysMsgs", sysMsgs);
		
		setAttr("activeMenu", Constants.ACTIVE_MENU_MESSAGE);
		render("/user/message.html");
	}
	
	/** 消息列表 */
	public void messageList(){
		int type = Integer.parseInt(getPara("type", "1"));
		User user = this.getSessionAttr("user");
		int pageNumber = getParaToInt("pageSize", 1); 
		int pageSize = getParaToInt("pageSize", 10);
		//通知分页列表
		Page<Message> pageMsgs = Message.dao.paginate(pageNumber, pageSize, "select *", "from message where type=? and user_id=? order by create_time desc", type, user.getInt("id"));
		
		setAttr("pageMsgs", pageMsgs);
		setAttr("activeMenu", Constants.ACTIVE_MENU_MESSAGE);
		
		render("/user/message-list.html");
	}
	
	/** 消息详情 */
	public void messageDetail(){
		int msgId = getParaToInt("msgid");
		Message msg = Message.dao.findById(msgId);
		setAttr("msg", msg);
		setAttr("activeMenu", Constants.ACTIVE_MENU_MESSAGE);
		render("/user/message-detail.html");
	}
}


