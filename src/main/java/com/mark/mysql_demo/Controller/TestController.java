package com.mark.mysql_demo.Controller;

import com.mark.mysql_demo.Entity.request.ExecuteSql;
import com.mark.mysql_demo.Entity.request.MysqlSelectByName;
import com.mark.mysql_demo.Entity.response.BaseResponse;
import com.mark.mysql_demo.Service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("111")
public class TestController {

    @Autowired
    MysqlService mysqlService;

    @RequestMapping(value = "/333")
    public BaseResponse selectUser(@RequestBody MysqlSelectByName mysqlSelectByName) {
        return mysqlService.getUser(mysqlSelectByName);
    }

    @RequestMapping(value = "/444")
    public BaseResponse executeSql(@RequestBody ExecuteSql executeSql) {
        return mysqlService.executeSql(executeSql);
    }
}
