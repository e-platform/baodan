package com.gdbd;

import java.util.HashMap;

import org.eclipse.jetty.server.Authentication.SendSuccess;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * BlogInterceptor
 */
public class BDInterceptor implements Interceptor {
	
	static HashMap<String,Integer> activeMenuMap = new HashMap<String, Integer>();
	
	static{
		activeMenuMap.put("message", 0);
		activeMenuMap.put("messageDetail", 0);
		activeMenuMap.put("messageList", 0);
		activeMenuMap.put("bdQuery", 1);
		activeMenuMap.put("bdAdd", 2);
		activeMenuMap.put("bdSave", 2);
		activeMenuMap.put("lpAdd", 3);
		activeMenuMap.put("lpSave", 3);
	}
	public void intercept(ActionInvocation ai) {
		
		//登录身份验证
		if(ai.getController().getSessionAttr("user") == null)
			ai.getController().redirect("/login");
		else{
			System.out.println("Before invoking " + ai.getActionKey());
			ai.invoke();
			System.out.println(ai.getViewPath());
			System.out.println(ai.getActionKey());
			System.out.println(ai.getControllerKey());
			System.out.println(ai.getMethodName());
			ai.getController().setAttr("activeMenu", activeMenuMap.get(ai.getMethodName()));
			System.out.println("After invoking " + ai.getActionKey());
		}
		ai.getController().setAttr("basePath", ai.getController().getRequest().getContextPath());
	}
}
