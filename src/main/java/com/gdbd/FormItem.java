package com.gdbd;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class FormItem extends Model<FormItem> {
	
	public static final FormItem dao = new FormItem();
	
	public Page<FormItem> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from form_item order by id asc");
	}
	
}
