package com.mcnaughton.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UriUtil {

    public static String generateUri(String host, String path, String ... params){
        String paramaterString = "";
        if(params.length > 0){
            paramaterString = Arrays.stream(params).collect(Collectors.joining("&","?",""));
        }
        return host + path + paramaterString;
    }
}
