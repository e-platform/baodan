package com.gdbd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.Page;


@Before({BDInterceptor.class, SessionInViewInterceptor.class})
public class UserController extends BaseController{
	
	public static final int MESSAGE_TYPE_BDSL = 1; //保单受理通知
	public static final int MESSAGE_TYPE_LPSL = 2; //理赔受理通知
	public static final int MESSAGE_TYPE_SYS = 3;  //系统通知
	
	public static HashMap<String,Integer> BAODAN_TYPE_MAP = new HashMap<String,Integer>();
	static{
		BAODAN_TYPE_MAP.put("zhx", 1);
	}
	
	public static HashMap<String,Integer> stepMap = new HashMap<String,Integer>();
	static{
		stepMap.put("zhx", 3);
	}
	
	@Override
	public void index() {
//		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 10));
//		render("/user/index.html");
		redirect("/user/message");
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
	
	/** method: 
	 * 		1、message() //我的消息首页
	 * 		2、messageList()	//消息列表
	 * 		3、messageDetail()	//消息详情
	 * 		4、bdQuery()	//保单查询
	 * 		5、bdView()	//保单预览
	 * 		6、bdEdit()	//保单编辑
	 * 		7、bdUpdate()	//保单更新
	 * 		8、bdAdd()	//保单新增
	 * 		9、bdSave()	//保单保存
	 * 		10、bdAttachmentUpload()	//保单附件上传
	 * 		11、bdAttachmentDel()	//保单附件删除
	 * 		12、lpAdd()	//申请理赔
	 * 		13、lpSave()	//理赔保存
	 * 		14、lpAttachmentUpload()	//理赔附件上传
	 * */
	
	public void message(){
		User user = this.getSessionAttr("user");
		int top = 5; //前多少条通知用于首页显示
		//保单受理通知
		List<Message> bdslMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=? order by create_time desc", MESSAGE_TYPE_BDSL, user.getInt("id")).getList();
		//理赔受理通知
		List<Message> lpslMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=? order by create_time desc", MESSAGE_TYPE_LPSL, user.getInt("id")).getList();
		//系统通知
		List<Message> sysMsgs = Message.dao.paginate(1, top, "select *", "from message where type=? and user_id=? order by create_time desc", MESSAGE_TYPE_SYS, user.getInt("id")).getList();
		
		setAttr("bdslMsgs", bdslMsgs);
		setAttr("lpslMsgs",lpslMsgs);
		setAttr("sysMsgs", sysMsgs);
		
		render("/user/message.html");
	}
	
	public void messageList(){
		int type = Integer.parseInt(getPara("type", "1"));
		User user = this.getSessionAttr("user");
		int pageNumber = getParaToInt("pageSize", 1); 
		int pageSize = getParaToInt("pageSize", 10);
		//通知分页列表
		Page<Message> pageMsgs = Message.dao.paginate(pageNumber, pageSize, "select *", "from message where type=? and user_id=? order by create_time desc", type, user.getInt("id"));
		
		setAttr("pageMsgs", pageMsgs);
		
		render("/user/message-list.html");
	}
	
	public void messageDetail(){
		int msgId = getParaToInt("msgid");
		Message msg = Message.dao.findById(msgId);
		setAttr("msg", msg);
		render("/user/message-detail.html");
	}
	
	public void bdQuery(){
		render("/user/bd-list.html");
	}
	
	public void bdView(){
		//find baodan by id
		//find form-item by baodanid
		//convert form-item to html  render form-baodantype-view-formtype.html
		//
		render("/user/bd-list.html");
	}
	
	public void bdEdit(){
		//find baodan by id
		//find form-item by baodanid
		//convert form-item to html  render form-baodantype-edit-formtype.html
		//
		render("/user/bd-list.html");
	}
	
	public void bdUpdate(){
		//判断是否所有表单都已填写
		// if baodan.id == null new Baodan().update();
		//get FormItem().setbaodanid().setvalue().update();
		render("/user/bd-list.html");
	}
	
	public void bdAdd(){
		setAttr("type", getPara("type","")).setAttr("opt", getPara("opt","")).setAttr("step", getPara("step",""));
		//查询保单模板，生成表单页面显示项
		if(!getPara("type","").equals("")){
			Baodan baodan = Baodan.dao.getTemplate(BAODAN_TYPE_MAP.get(getPara("type")));
			Map map = baodan.genFormMap("add");//Map<"add",List<Map<${name},"<input/>">>>;
			setAttr("list",map.get(getPara("type")));
		}
		render("/user/bd-add.html");
	}
	
	public void bdSave(){
		
		if(getPara("baodanId", "").equals("")){
			Baodan baodan = new Baodan();
			baodan.set("type", type);
			baodan.set("name", name);
			baodan.set("code", code);
			baodan.set("status", value);
			baodan.set("sbjine", sbjine);
			baodan.save();
		}else{
			
		}
		
		
		//判断是否所有表单都已填写
		// if baodan.id == null new Baodan().save();
		//new FormItem().setbaodanid().setvalue().save();
		//paraMap  formItems for each save or update
		if(getParaToInt("step") < stepMap.get(getPara("type")).intValue()){
			//否，跳转下一步 判断险种，加载需要填写的表单
			setAttr("type", getPara("type")).setAttr("opt", getPara("opt")).setAttr("step", (Integer.parseInt(getPara("step")) + 1)+"");
			render("/user/bd-add.html");
		}else{
			//是，提示已保单提交成功，跳转到保单列表页面
			
		}
		
	}
	
	public void lpAdd(){
		render("/user/lp-add.html");
	}
}


