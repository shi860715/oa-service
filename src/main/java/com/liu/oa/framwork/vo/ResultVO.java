package com.liu.oa.framwork.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

   private String msg;
   private  Integer code;
   private T data;





}
