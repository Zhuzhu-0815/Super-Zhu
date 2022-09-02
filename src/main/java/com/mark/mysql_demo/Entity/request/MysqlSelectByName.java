package com.mark.mysql_demo.Entity.request;

import lombok.Data;

@Data
public
class MysqlSelectByName {
	//根据用户昵称查询结果的请求实体
	public String name;//前后端保持一致，前端字段名与后端变量名相同，才能对应后端解析到前端发来的json数据。
}
