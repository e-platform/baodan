package com.gdbd;

import java.util.Date;

import com.jfinal.core.Controller;

public class LoginController extends Controller {
	public void index() {
//		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 10));
//		render("blog.html");
		render("login.html");
	}
	
	public void add() {
	}
	
	public void save() {
//		getModel(Blog.class).save();
//		redirect("/blog");
	}
	
	public void edit() {
//		setAttr("blog", Blog.dao.findById(getParaToInt()));
	}
	
	public void update() {
//		getModel(Blog.class).update();
//		redirect("/blog");
	}
	
	public void delete() {
//		Blog.dao.deleteById(getParaToInt());
//		redirect("/blog");
	}
	
	/** method: 
	 * 		1、 login()
	 * 		2、
	 * 
	 * 
	 * 
	 * 
	 * */
	public void login(){
		String account = getPara("account");
		String passwd = getPara("passwd");
		System.out.println(account + ":" + passwd);
		User user = User.dao.login(account, passwd);
		//根据登录账号跳转页面
		if(user != null){
			user.set("login_time", new Date());
			user.update();
			setSessionAttr("user", user);
			redirect("/user/index");
		}else{
			render("/login.html");
		}
	}
}


