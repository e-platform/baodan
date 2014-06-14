package com.gdbd;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class User extends Model<User> {
	
	/** 普通用户角色 */
	public static final int ROLE_USER = 0;
	/** 管理员角色*/
	public static final int ROLE_ADMIN = 1;
	/** 超级管理员*/
	public static final int ROLE_SUPPER_ADMIN = 2;

	public static final User dao = new User();
	
	public Page<User> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}
	
	/** 登录 */
	public User login(String account, String passwd){
		return findFirst("select * from user where account=? and passwd=? and record_status=?", account,passwd,Constants.RECORD_STATUS_COMMON);
	}
}
