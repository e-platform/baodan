package com.gdbd;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;

/**
 * 
 * @author zenglongx
 * @date 2014-6-3 上午9:03:52
 */
@Before({BDInterceptor.class, SessionInViewInterceptor.class})
public class ZonghexianBaodanController extends BaseController{

	/** 主保单新增 */
	public void majorFormAdd(){
		setAttr("activeMenu", Constants.ACTIVE_MENU_BDCREATE);
		render("/user/zonghexian/major-form-add.html");
	}
	
	/** 主保单保存 */
	public void majorFormSave(){
		setAttr("activeMenu", Constants.ACTIVE_MENU_BDCREATE);
		redirect("/zhxbaodan/assetsAdd");
	}
	
	/** 主保单修改 */
	public void majorFormEdit(){
		
	}
	
	/** 主保单更新 */
	public void majorFormUpdate(){
		
	}
	
	/** 资产明细新增 */
	public void assetsAdd(){
		render("/user/zonghexian/assets-add.html");
	}
	/** 资产明细保存 */
	public void assetsSave(){
		forwardAction("/baodan/bdSave");
	}
	/** 资产明细修改 */
	public void assetsEdit(){
		
	}
	/** 资产明细更新 */
	public void assetsUpdate(){
		
	}
	/** 资产明细删除 */
	public void assetsDelete(){
		
	}
	
	/** 附加条款新增 */
	public void attachClauseAdd(){
		
	}
	/** 附加条款保存 */
	public void attachClauseSave(){
		
	}
	/** 附加条款修改 */
	public void attachClauseEdit(){
		
	}
	/** 附加条款更新 */
	public void attachClauseUpdate(){
		
	}
}
