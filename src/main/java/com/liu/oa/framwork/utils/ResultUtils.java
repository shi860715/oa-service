package com.liu.oa.framwork.utils;



import com.liu.oa.common.contants.Contant;
import com.liu.oa.framwork.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultUtils {


    public static ResultVO success(Object o){
         return ResultVO.builder().
                            data(o)
                  .code(Contant.ZORO.getCode())
                           .msg("成功").build();

    }

    public static  ResultVO success(){
         return  success(null);
    }

    public static  ResultVO success(String message){
         return  ResultVO.builder().code(Contant.ZORO.getCode()).msg(message).build();

    }

    public static ResultVO success(String message ,Object o){
        return  ResultVO.builder().data(o).msg(message).code(Contant.ZORO.getCode()).build();
    }



   public static  ResultVO error(Integer code,String msg){
        return  ResultVO.builder().code(code).msg(msg).build();
   }



}
