package com.gdbd;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class AssuranceClass extends Model<AssuranceClass> {
	
	/** 分类标识：综合险 */
	public static final int CLASS_ZHX = 3;
	/** 分类标识：责任险*/
	public static final int CLASS_ZRX = 4;

	public static final AssuranceClass dao = new AssuranceClass();
	
	public Page<AssuranceClass> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}
	
}
