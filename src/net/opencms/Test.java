package net.opencms;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Wayne on 2014/8/14.
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(new String(Base64.decodeBase64("UG93ZXJlZEJ5"), "utf-8"));
        System.out.println(new String(Base64.decodeBase64("U2hvcHh4Lm5ldA=="), "utf-8"));

//        byte[] opencms = new byte[]{Byte.valueOf("OPENCMS")}
//        System.out.println(new String(Base64.encodeBase64(opencms)));;
    }
}

