package com.better.eat.utils;
public class StringUtil {

    public static final String QUALI_FIED = "* . ? + $ ^ [ ] ( ) { } | \\ /" ;

    public static String qualifiedString(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<str.length();i++){
            char item = str.charAt(i);
            if (QUALI_FIED.indexOf(item)>-1){
                sb.append("\\").append(item);
            }else{
                sb.append(item);
            }
        }
        return sb.toString();
    }
}
