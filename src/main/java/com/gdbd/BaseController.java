package com.gdbd;

import com.jfinal.core.Controller;

public class BaseController extends Controller {
	public void index() {
//		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 10));
//		render("blog.html");
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
	 * 		1、 init() userProfile  activeMenu
	 * 		2、
	 * 
	 * 
	 * 
	 * 
	 * */
	
}


