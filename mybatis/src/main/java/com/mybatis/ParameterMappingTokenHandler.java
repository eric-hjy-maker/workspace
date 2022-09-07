package com.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hjy
 **/
public class ParameterMappingTokenHandler implements TokenHandler {

    List<String> list = Arrays.asList(new String[]{"name", "age"});

    @Override
    public String handleToken(String content) {
        return "?";
    }

    public List<String> getParameterMappings() {
        return list;
    }
}
