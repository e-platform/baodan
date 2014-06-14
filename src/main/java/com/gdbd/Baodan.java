package com.gdbd;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Baodan extends Model<Baodan> {
	
	public static final Baodan dao = new Baodan();
	
	public static final int STATUS_SUBMIT = 1;			//已提交待审批
	public static final int STATUS_APPROVE_OK = 2;		//审批通过
	public static final int STATUS_APPROVE_GOBACK = 3;	//审批不通过
	
	public Page<Baodan> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from baodan order by id asc");
	}
	
	/** 获取保单模板 *//*
	public Baodan getTemplate(int type){
		return findFirst("select * from baodan where code=? and type=?", "0",type);
	}
	
	*//** 获取所有表单数据 *//*
	public List<FormItem> getFormItems(){
		return  FormItem.dao.find("select * from form_item where baodan_id=?",get("id"));
	}
	
	*//** 生成保单的表单项，生成结构为按表单类型组成的Map <form-type,List<Map<elemName,elemValue>>>
	 * 	单值表单list中只有一个元素，多值时有多个元素
	 * 
	 * *//*
	public Map<String,List<Map<String,String>>> genFormModelMap(String viewType){
		Map<String,List<Map<String,String>>> result = new HashMap<String, List<Map<String,String>>>();
		List<FormItem> formItems = getFormItems();
		for(FormItem formItem : formItems){
			if(formItem.getInt("is_mult") == 0){
				//单值
				String type = formItem.getStr("form_type");
				ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
				HashMap<String,String> map = new HashMap<String, String>();
				list.add(map);
				result.put(type, list);
				for(Entry<String, Object> entry : formItem.getAttrsEntrySet()){
					String fieldName = entry.getKey();
					if(!fieldName.startsWith("field_") && entry.getValue() != null){
						map.put(fieldName, genFormElement("hidden","input",fieldName,entry.getValue().toString(),"",fieldName));
						continue;
					}
					if(entry.getValue() != null && !entry.getValue().equals("")){
						fieldName += "_" + entry.getValue().toString().substring(0, entry.getValue().toString().lastIndexOf("_")+1);
						String[] values = new String[]{"","","",""};
						String[] vals = entry.getValue().toString().split("_");
						System.arraycopy(vals, 0, values, 0, vals.length);
						String formItemType = values[0];//元素类型
						String formItemName = values[1];//元素名称
						String formItemValid = values[2];//正则表达式
						String formItemValue = values[3];//值
						map.put(formItemName, genFormElement(viewType,formItemType,fieldName,formItemValue,formItemValid,formItemName));
					}
				}
			}else{
				//多值
				String type = formItem.getStr("form_type");
				if(result.get(type) == null){
					ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
					result.put(type, list);
				}
				HashMap<String,String> map = new HashMap<String, String>();
				result.get(type).add(map);
				for(Entry<String, Object> entry : formItem.getAttrsEntrySet()){
					String fieldName = entry.getKey();
					if(!fieldName.startsWith("field_")){
						map.put(fieldName, genFormElement("hidden","input",fieldName,entry.getValue().toString(),"",fieldName));
						continue;
					}
					if(entry.getValue() != null && !entry.getValue().equals("")){
						fieldName += "_" + entry.getValue().toString().substring(0, entry.getValue().toString().lastIndexOf("_")+1);
						String[] values = new String[]{"","","",""};
						String[] vals = entry.getValue().toString().split("_");
						System.arraycopy(vals, 0, values, 0, vals.length);
						String formItemType = values[0];
						String formItemName = values[1];
						String formItemValid = values[2];
						String formItemValue = values[3];
						map.put(formItemName, genFormElement(viewType,formItemType,fieldName,formItemValue,formItemValid,formItemName));
					}
				}
			}
		}
		return result;
	}
	
	protected String genFormElement(String viewType, String elemType, String name, String value, String validateStr, String formItemName) {
		StringBuffer sb = new StringBuffer();
		if(elemType.equals("input")){
			if(viewType.equals("add")){
				sb.append("<input type='text' name='").append(name)
				.append("' value='' regexp='").append(validateStr).append("' attr_name='").append(formItemName).append("'/>");
			}else if(viewType.equals("edit")){
				sb.append("<input type='text' name='").append(name)
				.append("' value='").append(value).append("' regexp='").append(validateStr).append("' attr_name='").append(formItemName).append("'/>");
			}else if(viewType.equals("view")){
				sb.append("<input type='text' disabled name='").append(name)
				.append("' value='").append(value).append("' regexp='").append(validateStr).append("' attr_name='").append(formItemName).append("'/>");
			}else if(viewType.equals("hidden")){
				sb.append("<input type='hidden' name='").append(name)
				.append("' value='").append(value).append("' regexp='").append(validateStr).append("' attr_name='").append(formItemName).append("'/>");
			}
		}else if(elemType.equals("radio")){
			
		}else if(elemType.equals("checkbox")){
			
		}else if(elemType.equals("date")){
			
		}
		
		return sb.toString();
	}*/
}
