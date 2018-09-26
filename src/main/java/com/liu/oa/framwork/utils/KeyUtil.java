package com.liu.oa.framwork.utils;

import java.util.Random;

public class KeyUtil {

    public static  synchronized String keyUnique(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;


        return  System.currentTimeMillis()+String .valueOf(number);
    }

    public static void main(String[] args) {
        for (int i = 0; i <200 ; i++) {
            String key = KeyUtil.keyUnique();
            System.out.println(key);
        }

    }


}
