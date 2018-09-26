package com.liu.oa.framwork.utils;

public class MathUtil {

    private static final  Double MONEY_RANG=0.01;

    public static  Boolean equals(double d1, double d2){
         double result=Math.abs(d1-d2);
         if(result<MONEY_RANG ){
             return  true;
         }else{
             return  false;
         }


    }
}
