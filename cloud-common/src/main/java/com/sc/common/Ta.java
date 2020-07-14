package com.sc.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author yuantongqin
 * desc:
 * 2020-05-24
 */
public class Ta {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.out.println(UUID.randomUUID().toString().length());


        String key = "123";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.digest(key.getBytes());

    }
}
