package com.mark.mysql_demo.Entity.response;

import lombok.Data;

@Data
public class BaseResponse {
    public String message;
    public Integer status;
    public Object data;
}
