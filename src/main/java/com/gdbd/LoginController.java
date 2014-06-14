package com.gdbd;

import java.util.Date;

import com.jfinal.core.Controller;

public class LoginController extends Controller {
	public void index() {
		render("login.html");
	}
	
	/** 登录 */
	public void login(){
		String account = getPara("account");
		String passwd = getPara("passwd");
		System.out.println(account + ":" + passwd);
		User user = User.dao.login(account, passwd);
		//根据登录账号跳转页面
		if(user != null &&
			(user.getInt("role") == User.ROLE_ADMIN || user.getInt("role") == User.ROLE_SUPPER_ADMIN)){
			user.set("login_time", new Date());
			user.update();
			setSessionAttr("user", user);
			redirect("/admin/index");
		}else if(user != null && user.getInt("role") == User.ROLE_USER){
			user.set("login_time", new Date());
			user.update();
			setSessionAttr("user", user);
			redirect("/user/index");
		}else{
			render("/login.html");
		}
	}
}


