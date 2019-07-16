package com.cheer.blogtest.util;

import org.apache.commons.codec.digest.DigestUtils;

public class StringUtils {
    // 使用md5加密
    public static String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }

}
