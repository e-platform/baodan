package com.gdbd;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

/**
 * 
 * @author zenglongx
 * @date 2014-6-3 上午9:03:52
 */
@Before({BDInterceptor.class, SessionInViewInterceptor.class})
public class BaodanController extends BaseController{

	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static final String SESSION_CREATEBAODAN_assuranceClassCode = "assuranceClassCode";	//创建保单类型
	/** 附件上传 */
	public void attachmentAdd(){
		UploadFile uploadFile = getFile();
		Attachment attachment = new Attachment();
//		attachment.set("baodan_id", value);
		attachment.set("path", uploadFile.getSaveDirectory());
		attachment.set("size", uploadFile.getFile().length());
		attachment.set("name", uploadFile.getFileName());
		
		attachment.save();
	}
	
	/** 附件删除 */
	public void attachmentDelete(){
		
	}
	
	/** 保单创建 */
	public void create(){
		//查询保单类别
		if(getPara("assuranceClassCode","").equals("") || getPara("assuranceClassCode","").equals("0")){
			setAttr("activeMenu", Constants.ACTIVE_MENU_BDCREATE);
			render("/user/bd-add.html");
		}else if(getPara("assuranceClassCode").equals("3")){//综合险
			setSessionAttr(SESSION_CREATEBAODAN_assuranceClassCode, getPara("assuranceClassCode"));
			forwardAction("/zhxbaodan/majorFormAdd");
		}else if(getPara("assuranceClassCode").equals("4")){//责任险
			setSessionAttr(SESSION_CREATEBAODAN_assuranceClassCode, getPara("assuranceClassCode"));
			forwardAction("/zrxbaodan/majorFormAdd");
		}
	}
	
	/** 所有资料填写完成后保存保单 */
	public void bdSave(){
		//TODO:是：生成销售确认书，委托书，相关附件保存，保单创建完成
		Baodan baodan = new Baodan();
		baodan.set("user_id", ((User)getSessionAttr("user")).getInt("id"));
		baodan.set("type", Integer.parseInt((String)getSessionAttr(SESSION_CREATEBAODAN_assuranceClassCode)));
		baodan.set("name", "");
		String code = ((User)getSessionAttr("user")).getStr("name") + "-保单-"
			+ Integer.parseInt((String)getSessionAttr(SESSION_CREATEBAODAN_assuranceClassCode))+"-" 
			+ dateformat.format(new Date());
		baodan.set("code", code);
		baodan.set("status", Baodan.STATUS_SUBMIT);
		baodan.set("sbjine", 10000);
		baodan.set("record_status", Constants.RECORD_STATUS_COMMON);
		baodan.save();
		redirect("/baodan/bdQuery");
	}
	
	/** 保单查询 */
	public void bdQuery(){
		User user = this.getSessionAttr("user");
		int pageNumber = getParaToInt("pageSize", 1); 
		int pageSize = getParaToInt("pageSize", 10);
		String select = "select *";
		String sqlExceptSelect = "from baodan where user_id=? and record_status=? order by create_time desc";
		Page<Baodan> baodans = Baodan.dao.paginate(pageNumber, pageSize,select,sqlExceptSelect,
			user.getInt("id"),Constants.RECORD_STATUS_COMMON);
		setAttr("baodans", baodans);
		setAttr("activeMenu", Constants.ACTIVE_MENU_BDQUERY);
		render("/user/bd-list.html");
	}
	
	/** 保单明细查看 */
	public void bdView(){
		//TODO:forward到保单详情页面
		Baodan baodan = Baodan.dao.findById(getParaToInt("baodanId"));
		if(baodan.getInt("assurance_class_id") == AssuranceClass.CLASS_ZHX){//综合险
			forwardAction("/zhxbaodan/majorFormView");
		}else if(baodan.getInt("assurance_class_id") == AssuranceClass.CLASS_ZRX){//责任险
			forwardAction("/zrxbaodan/majorFormView");
		}
	}
	
	/** 保单删除 */
	public void bdDelete(){
		//置保单删除标记
		Baodan baodan = Baodan.dao.findById(getParaToInt("baodanId"));
		baodan.set("record_status", Constants.RECORD_STATUS_DEL);
		baodan.update();
		redirect("/baodan/bdQuery");
	}
	
	/** 保单审批 */
	public void bdApprove(){
		//TODO:更新保单审批状态
	}
}
