package com.gdbd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
//	public static HashMap<String,Integer> BAODAN_TYPE_MAP = new HashMap<String,Integer>();
//	static{
//		BAODAN_TYPE_MAP.put("zhx", 1);
//	}
	
	public static HashMap<String,Integer> stepMap = new HashMap<String,Integer>();
	static{
		stepMap.put("1", 3);//1综合险表单总填写数量
	}
	
	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
	
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
		User user = this.getSessionAttr("user");
		int pageNumber = getParaToInt("pageSize", 1); 
		int pageSize = getParaToInt("pageSize", 10);
		String select = "select *";
		String sqlExceptSelect = "from baodan where user_id=? order by create_time desc";
		Page<Baodan> baodans = Baodan.dao.paginate(pageNumber, pageSize,select,sqlExceptSelect,user.getInt("id"));
		setAttr("baodans", baodans);
		render("/user/bd-list.html");
	}
	
	public void bdView(){
		//find baodan by id
		Baodan baodan = Baodan.dao.findById(getParaToInt("baodanId"));
		Map map = baodan.genFormModelMap("view");
		setAttr("type",baodan.get("type"));
		setAttr("step",getPara("step","1"));
		setAttr("list",map.get(getPara("step","1")));
		setAttr("opt","view");
		//find form-item by baodanid
		//convert form-item to html  render form-baodantype-view-formtype.html
		//
		render("/user/bd-view.html");
	}
	
	public void bdEdit(){
		//find baodan by id
		Baodan baodan = Baodan.dao.findById(getParaToInt("baodanId"));
		Map map = baodan.genFormModelMap("edit");
		setAttr("list",map.get(getPara("step","1")));
		setAttr("type",baodan.get("type"));
		setAttr("step",getPara("step","1"));
		setAttr("opt","edit");
		setAttr("baodanId",baodan.get("id"));
		//find form-item by baodanid
		//convert form-item to html  render form-baodantype-edit-formtype.html
		//
		render("/user/bd-edit.html");
	}
	
	public void bdUpdate(){
		//判断是否所有表单都已填写
		// if baodan.id == null new Baodan().update();
		
		Baodan baodan = Baodan.dao.findById(getParaToInt("baodanId"));
//		baodan.set("name", name);
//		baodan.set("code", code);
//		baodan.set("status", value);
//		baodan.set("sbjine", sbjine);
		baodan.update();
		//保存保单表单项
		List<FormItem> formItems = genFormItems(getParaMap());
		String[] ids = getParaMap().get("id");
		for(int i = 0; i < formItems.size(); i++){
			formItems.get(i).put("id", ids[i])
			.update();
		}
		
		
		
		//判断是否所有表单都已填写
		if(getParaToInt("step") < stepMap.get(getPara("type")).intValue()){
			//否，跳转下一步 判断险种，加载需要填写的表单
			setAttr("type", getParaToInt("type")).setAttr("opt", getPara("opt")).setAttr("step", (Integer.parseInt(getPara("step")) + 1)+"");
			Map map = baodan.genFormModelMap("edit");//Map<"add",List<Map<${name},"<input/>">>>;
			setAttr("list",map.get(getPara("step","1")));
			setAttr("baodanId",baodan.get("id"));
			render("/user/bd-edit.html");
		}else{
			//是，提示已保单提交成功，跳转到保单列表页面
			baodan.set("status", 1);
			baodan.update();
			render("/user/bd-list.html");
		}
	}
	
	public void bdAdd(){
		//type: 1 code:0 (模板) step:填写步骤 opt:add,edit,view
		setAttr("type", getParaToInt("type",1)).setAttr("opt", getPara("opt","")).setAttr("step", getPara("step",""));
		//查询保单模板，生成表单页面显示项
		if(!getPara("type","").equals("")){
			Baodan baodan = Baodan.dao.getTemplate(getParaToInt("type"));
			Map map = baodan.genFormModelMap("add");//Map<"add",List<Map<${name},"<input/>">>>;
			setAttr("list",map.get(getPara("step","1")));
		}
		render("/user/bd-add.html");
	}
	
	public void bdSave(){
		//检查保单ID是否存在
		Baodan baodan = null;
		if(getPara("baodanId", "").equals("")){
			baodan = new Baodan();
			baodan.set("user_id", ((User)getSessionAttr("user")).getInt("id"));
			baodan.set("type", getParaToInt("type"));
			baodan.set("name", "");
			baodan.set("code", ((User)getSessionAttr("user")).getStr("name") + "-" + dateformat.format(new Date()));
			baodan.set("status", 0);
			baodan.set("sbjine", 0);
			baodan.save();
		}else{
			baodan = Baodan.dao.findById(getParaToInt("baodanId"));
		}
		
		//保存保单表单项
		List<FormItem> formItems = genFormItems(getParaMap());
		for(FormItem formItem : formItems){
			formItem.set("baodan_id", baodan.getInt("id"));
			formItem.set("form_type", getPara("step","1"));
			formItem.set("id", null);
//			formItem.set("is_mult", 0);
			
			formItem.save();
		}
		
		//判断是否所有表单都已填写
		if(getParaToInt("step") < stepMap.get(getPara("type")).intValue()){
			//否，跳转下一步 判断险种，加载需要填写的表单
			setAttr("type", getParaToInt("type")).setAttr("opt", getPara("opt")).setAttr("step", (Integer.parseInt(getPara("step")) + 1)+"");
			Baodan baodanTemplate = Baodan.dao.getTemplate(getParaToInt("type"));
			Map map = baodanTemplate.genFormModelMap("add");//Map<"add",List<Map<${name},"<input/>">>>;
			setAttr("list",map.get(getPara("step","1")));
			setAttr("baodanId",baodan.get("id"));
			render("/user/bd-add.html");
		}else{
			//是，提示已保单提交成功，跳转到保单列表页面
			baodan.set("status", 1);
			baodan.update();
			render("/user/bd-list.html");
		}
	}
	
	public void lpAdd(){
		render("/user/lp-add.html");
	}
	
	public void bdDelete(){
		//删除保单表单项
		//删除保单
		Baodan.dao.deleteById(getParaToInt("baodanId"));
		redirect("/user/bd-list.html");
	}
	/** form表单中提取FormItem实体 */
	private List<FormItem> genFormItems(Map<String,String[]> map) {
		List<FormItem> formItems = new ArrayList<FormItem>();
		int formItemsSize = 1;//多值表单项，实体的个数
		for(int i = 0 ; i < formItemsSize; i++){//单值表单只执行一次，多值执行多次
			FormItem formItem = new FormItem();
			formItem.set("is_mult", 0);
			for(Map.Entry<String, String[]> entry : map.entrySet()){
				String key = entry.getKey();
				if(!key.startsWith("field_")){
					continue;
				}
				String key1 = "field_" + key.substring(6, key.indexOf("_",6));
				String key2 = key.substring(key1.length()+1);
				String[] values = entry.getValue();
				if(values.length > formItemsSize){
					formItemsSize = values.length;
					formItem.set("is_mult", 1);
				}
				if(values.length == 1){
					formItem.set(key1, key2+values[0]);
					formItems.add(formItem);
				}else{
					for(int j = 0; j < values.length; j++){
						if(formItems.size() > j){
							formItem = formItems.get(j);
						}else{
							formItem = new FormItem();
							formItem.set("is_mult", 0);
							formItems.add(formItem);
						}
						
						formItem.set(key1, key2+values[j]);
					}
				}
			}
		}
		return formItems;
	}
}


