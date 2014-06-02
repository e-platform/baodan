package com.gdbd;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(Tx.class)
public class FormItem extends Model<FormItem> {
	
	public static final FormItem dao = new FormItem();
	
	public Page<FormItem> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from form_item order by id asc");
	}
	
	public void saveBeans(List<FormItem> beans){
		for(FormItem formItem : beans){
			formItem.save();
		}
	}
}
