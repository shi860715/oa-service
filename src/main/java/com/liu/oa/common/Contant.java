package com.liu.oa.common;

import lombok.Getter;

@Getter
public enum Contant implements CodeEmnu{
	
    ZORO(0,"零"),
    
    
    ;

    private Integer code;

    private String  msg;



    Contant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
