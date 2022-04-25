package com.huchuchu.paper.springboot.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

    private String result;

    public MD5Generator(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException{

        MessageDigest mdMd5 = MessageDigest.getInstance("Md5");
        mdMd5.update(input.getBytes("UTF-8"));
        byte[] md5Hash = mdMd5.digest();
        StringBuilder hexMd5hash = new StringBuilder();
        for (byte b : md5Hash){
            String hexString = String.format("%2x", b);
            hexMd5hash.append(hexString);
        }

        result = hexMd5hash.toString();
    }
    public String toString(){
        return result;
    }

}
