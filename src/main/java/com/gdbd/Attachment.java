package com.gdbd;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Attachment extends Model<Attachment> {
	
	public static int RECORD_STATUS_COMMON = 0;			//记录状态 正常
	public static int RECORD_STATUS_DEL = 1;			//记录状态 已删除

	public static final Attachment dao = new Attachment();
	
	public Page<Attachment> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}
	
}
