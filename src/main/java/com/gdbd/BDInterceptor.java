package com.gdbd;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * BDInterceptor
 */
public class BDInterceptor implements Interceptor {
	
	public void intercept(ActionInvocation ai) {
		
		//登录身份验证
		if(ai.getController().getSessionAttr("user") == null)
			ai.getController().redirect("/login");
		else{
			System.out.println("Before invoking " + ai.getActionKey());
			ai.invoke();
//			System.out.println(ai.getViewPath());
//			System.out.println(ai.getActionKey());
//			System.out.println(ai.getControllerKey());
//			System.out.println(ai.getMethodName());
//			ai.getController().setAttr("activeMenu", activeMenuMap.get(ai.getMethodName()));
//			System.out.println("After invoking " + ai.getActionKey());
		}
		ai.getController().setAttr("basePath", ai.getController().getRequest().getContextPath());
	}
}
