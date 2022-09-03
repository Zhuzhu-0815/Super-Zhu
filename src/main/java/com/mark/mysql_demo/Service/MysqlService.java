package com.mark.mysql_demo.Service;

import com.mark.mysql_demo.Entity.request.ExecuteSql;
import com.mark.mysql_demo.Entity.request.MysqlSelectByName;
import com.mark.mysql_demo.Entity.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MysqlService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public BaseResponse getUser(MysqlSelectByName mysqlSelectByName) {

        /*--------------------------------------------查询所有记录---------------------------------------------------*/
        String sql = "select * from haizhu";

        //使用JDBC的一个工具JdbcTemplate进行查询操作
        //使用方法：首先编写Sql语句，如上所示，随即使用query操作，执行Sql语句，本次操作使用的是以列表形式返回数据的方法
        //声明一个List对象来承接查询结果，列表项通常为Map类，因为数据库也是以键值的形式存储数据，一行数据便是一组键值对

        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);//查询结果以list类型返回，jdbcTemplate是一个工具类

        //List的增强for循环，也叫for each操作，用于遍历列表中的项
        System.out.println("查询所有：");
        res.forEach((e) -> {
            //e是该List中的一个项，每次循环都对应一个项，如本次循环中，对应着List中的Map类型的项
            System.out.println(e);
        });

        /*------------------------------------------------条件查询---------------------------------------------------*/
        //string.format是占位符操作（相当于填空题里面由下划线代替答案）
        String sql_by_name = String.format("select * from haizhu where nickname = '%s'", mysqlSelectByName.getName());

        List<Map<String, Object>> res_by_name = jdbcTemplate.queryForList(sql_by_name);
        List<Object> nicknamelist = new ArrayList<>();
        System.out.println("条件查询：");
        res_by_name.forEach((e) -> {
            nicknamelist.add(e.get("nickname"));
            System.out.println(e.get("nickname"));
        });
        Map<String, Object> one = new HashMap<String, Object>();
        one.put("one", nicknamelist);
        one.put("two", res_by_name);


        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(one);
        return baseResponse;
    }

    public BaseResponse executeSql(ExecuteSql executeSql) {
        //对库表进行增、删、改操作
        //使用JdbcTemplate.execute()方法，此方法可以执行任何操作的语句
        BaseResponse baseResponse = new BaseResponse();
        //看具体操作是什么
        String sql = "";
        if (executeSql.getType().equals("update")) {
            sql += "update haizhu set nickname='chi1' where nickname='chi'";
        } else if (executeSql.getType().equals("insert")) {
            sql += "insert into haizhu (name,nickname,age,weight,hobby) values ('liu', 'feitian', 18,53,'睡觉')";
        } else if (executeSql.getType().equals("delete")) {
            sql += "delete from haizhu where nickname = 'feitian'";
        } else {
            baseResponse.setData(sql);
        }
        //try-catch写法，预防出现错误时直接报错，加入对错误的处理
        try {
            jdbcTemplate.execute(sql);
            String sql1 = "select * from haizhu";
            List<Map<String, Object>> res = jdbcTemplate.queryForList(sql1);//查询结果以list类型返回，jdbcTemplate是一个工具类

            //List的增强for循环，也叫for each操作，用于遍历列表中的项
            System.out.println("修改后数据：");
            res.forEach((e) -> {
                //e是该List中的一个项，每次循环都对应一个项，如本次循环中，对应着List中的Map类型的项
                System.out.println(e);
            });
            baseResponse.setData(res);
        } catch (Exception e) {
            System.out.println(11);
            System.out.println(e);
        }
        return baseResponse;
    }
}
