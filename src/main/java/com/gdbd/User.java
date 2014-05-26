package com.gdbd;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class User extends Model<User> {
	
	public static int RECORD_STATUS_COMMON = 0;			//记录状态 正常
	public static int RECORD_STATUS_DEL = 1;			//记录状态 已删除

	public static final User dao = new User();
	
	public Page<User> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}
	
	/** 登录 */
	public User login(String account, String passwd){
		return findFirst("select * from user where account=? and passwd=? and record_status=?", account,passwd,RECORD_STATUS_COMMON);
	}
}
