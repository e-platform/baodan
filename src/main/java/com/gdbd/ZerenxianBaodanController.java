package com.gdbd;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;

/**
 * 
 * @author zenglongx
 * @date 2014-6-3 上午9:03:52
 */
@Before({BDInterceptor.class, SessionInViewInterceptor.class})
public class ZerenxianBaodanController extends BaseController{

	/** 主保单新增 */
	public void majorFormAdd(){
		render("/user/zerenxian/major-form-add.html");
	}
	
	/** 主保单保存 */
	public void majorFormSave(){
		forwardAction("/baodan/bdSave");
	}
	
	/** 主保单修改 */
	public void majorFormEdit(){
		
	}
	
	/** 主保单查看 */
	public void majorFormView(){
		setAttr("activeMenu", Constants.ACTIVE_MENU_BDQUERY);
		render("/user/zerenxian/major-form-view.html");
	}
	
	/** 主保单更新 */
	public void majorFormUpdate(){
		
	}
	
	/** 附加险新增 */
	public void attachAssuranceAdd(){
		
	}
	/** 附加险保存 */
	public void attachAssuranceSave(){
		
	}
	/** 附加险修改 */
	public void attachAssuranceEdit(){
		
	}
	/** 附加险更新 */
	public void attachAssuranceUpdate(){
		
	}
	
	/** 保单受理 */
	public void approve(){
		setAttr("baodanId", getPara("baodanId"));
		render("/admin/zerenxian/approve_major_form.html");
	}
}
