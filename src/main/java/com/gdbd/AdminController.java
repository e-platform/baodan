package com.gdbd;

import com.jfinal.aop.Before;


@Before(BDInterceptor.class)
public class AdminController extends BaseController {
	@Override
	public void index() {
//		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 10));
//		render("blog.html");
	}
	
	@Override
	public void add() {
	}
	
	@Override
	public void save() {
//		getModel(Blog.class).save();
//		redirect("/blog");
	}
	
	@Override
	public void edit() {
//		setAttr("blog", Blog.dao.findById(getParaToInt()));
	}
	
	@Override
	public void update() {
//		getModel(Blog.class).update();
//		redirect("/blog");
	}
	
	@Override
	public void delete() {
//		Blog.dao.deleteById(getParaToInt());
//		redirect("/blog");
	}
	
	/**
	 * method:
	 * 		1、message()		//首页消息
	 * 		2、messageList()		//所有消息列表
	 * 		3、messageDetail()	//消息详情
	 * 		4、acceptBd()		//保单受理
	 * 		5、acceptBdSave()	//保单受理结果保存
	 * 		6、bdQuery()			//保单查询
	 * 		7、statisticalChart()	//统计图表
	 * 		8、setting()			//设置管理
	 * 		9、settingSave()		//设置保存
	 * 		10、settingAddUser()	//设置添加一个用户
	 * 		11、settingSaveUser()	//设置保存一个用户
	 * 		12、settingRemoveUser()	//设置删除一个用户
	 * 		13、settingEditUser()	//设置编辑一个用户
	 * 		14、settingUpdateUser()	//设置更新一个用户
	 * 
	 */
}


