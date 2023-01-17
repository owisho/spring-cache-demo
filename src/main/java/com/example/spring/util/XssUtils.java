package com.example.spring.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

import java.util.regex.Pattern;

public class XssUtils {

    public static String escape(String input){
        AggregateTranslator translator = new AggregateTranslator(
                new LookupTranslator(EntityArrays.BASIC_ESCAPE),
                new LookupTranslator(EntityArrays.APOS_ESCAPE));
        return translator.translate(input);
    }

    public static String unescape(String input){
        AggregateTranslator translator = new AggregateTranslator(
                new LookupTranslator(EntityArrays.BASIC_UNESCAPE),
                new LookupTranslator(EntityArrays.APOS_UNESCAPE));
        return translator.translate(input);
    }

    /**
     * content中包含<script> </script> eval() e-xpression() onload() onerror() alert() 等内容时返回false 标识内容不合法
     */
    public static boolean checkContent(String content) {
        if (StringUtils.isBlank(content))
            return true;
        Pattern pattern  = Pattern.compile("script\\s", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(content).find())
            return false;
        pattern = Pattern.compile("eval", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(content).find())
            return false;
        pattern = Pattern.compile("e-xpression", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(content).find())
            return false;
        pattern = Pattern.compile("onload", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(content).find())
            return false;
        pattern = Pattern.compile("onerror", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(content).find())
            return false;
        pattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(content).find())
            return false;
        pattern = Pattern.compile("iframe", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(content).find())
            return false;
        return true;
    }

    public static void main(String[] args) {
        String str = "Les préinscriptions en première année BTS dans les établissements publics ont débuté";
        System.out.println(checkContent(str));
    }
}
