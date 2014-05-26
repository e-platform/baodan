package com.gdbd;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Message extends Model<Message> {
	
	public static int RECORD_STATUS_COMMON = 0;			//记录状态 正常
	public static int RECORD_STATUS_DEL = 1;			//记录状态 已删除

	public static final Message dao = new Message();
	
	public Page<Message> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from message order by id asc");
	}
	
}
