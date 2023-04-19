package com.example.tasker.global.encryption;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHA256Util {

    public String encrypt(String str){
        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte[] byteData = sh.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();

        }catch(NoSuchAlgorithmException e){
            //e.printStackTrace();
            System.out.println("Encrypt Error - NoSuchAlgorithmException");
        }

        return null;
    }
}
