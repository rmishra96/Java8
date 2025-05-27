package com.educative.strings;

public class LongestValidParentheses {
    public static int longestValidParentheses(String s){
        int left =0 , right = 0, maxLength =0 ;

        for(int i=0; i < s.length();i++){
            if(s.charAt(i) == '('){
                left++;
            }
            else {
                right++;
            }

            if(left == right){
                maxLength = Math.max(maxLength, 2* right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;

        for(int i =s.length() -1 ; i >=0; i--){
            if(s.charAt(i) == '('){
                left++;
            }else {
                right++;
            }

            if(left == right){
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left >= right) {
                left = right =0;
            }
        }
        return  maxLength;
    }

    public static void main(String[] args) {
        String[] inputs = {"(()", ")()())", "", "(", ")()()(", ")())((())())("};
        int index = 0;
        for (String input : inputs) {
            int result = longestValidParentheses(input);
            System.out
                    .println((++index) + ".  longestValidParentheses(\"" + input + "\"): " + result);
            System.out.println(
                    "--------------------------------------------------------------------------------------\n");
        }
    }
}
