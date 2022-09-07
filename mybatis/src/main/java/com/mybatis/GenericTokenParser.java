package com.mybatis;

import java.util.List;

/**
 * @author hjy
 **/
public class GenericTokenParser {



    private String openToken;
    private String closeToken;
    private TokenHandler handler;

    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.handler = handler;
    }

    public String parse(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
//        String regexp
        return "select * from user where name = ? and age = ?";
    }

}
