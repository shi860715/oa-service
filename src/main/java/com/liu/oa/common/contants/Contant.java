package com.liu.oa.common.contants;

import com.liu.oa.common.enums.CodeEmnu;

import lombok.Getter;

@Getter
public enum Contant implements CodeEmnu{
	
    ZORO(0,"零"),
    SUCCESS(1,"成功"),
    FAILED(2,"失败"),
    
    ;

    private Integer code;

    private String  msg;



    Contant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
