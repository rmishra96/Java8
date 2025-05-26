package com.educative.strings;

import java.util.Stack;

public class ReverseWords {
    public static void strRev( char[] str, int start , int end){
        if(str == null || str.length < 1){
            return;
        }

        while(start <end){
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end-- ;
        }
    }

    public static String reverseWords(char[] sentence){
        if(sentence == null || sentence.length == 0){
            return "string is empty";
        }
        int strLen = sentence.length ;
        strRev(sentence, 0, strLen -1);
        int start = 0 ;
        int end = 0;
        while(true){
            if(start >= strLen -1){
                break;
            }
        }
    }
}
