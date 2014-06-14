package com.gdbd;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;

/**
 * 
 * @author zenglongx
 * @date 2014-6-3 上午9:03:52
 */
@Before({BDInterceptor.class, SessionInViewInterceptor.class})
public class AssuranceClassController extends BaseController{

	/** 查询所有保险分类，页面为级联下拉框 */
	public void queryAll(){
		
	}
}
