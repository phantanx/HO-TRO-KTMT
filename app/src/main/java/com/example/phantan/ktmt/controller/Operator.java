package com.example.phantan.ktmt.controller;

import android.util.Log;

/**
 * Created by phantan on 14/10/2015.
 */
public class Operator {
    private static final String[] digit={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    public static String decimalToAnother(String decimal,int type){
        String rs ="";
        long value = Long.parseLong(decimal);
        while(value>0){
            int pos = (int) (value%type);
            if(pos<0){
                Log.d("Current Bug",String.valueOf(value));
                Log.d("Current Bug",String.valueOf(type));
                Log.d("Current Bug",String.valueOf(value%type));
                Log.d("Current Bug",String.valueOf(pos));
            }
            rs+=digit[pos];
            value/=type;
        }

        return new StringBuilder(rs).reverse().toString();
    }
    public static String anotherToDecimal(String input,int type){
        long rs=0;
        char element;
        int valueOfElement;
        for(int i=input.length()-1;i>=0;i--){
            element=input.charAt(i);
            valueOfElement=valueOfDigit(digit, element);
            //System.out.println(i+"-----"+valueOfElement);
            rs+=valueOfElement*Math.pow(type, input.length()-i-1);

        }
        return String.valueOf(rs);
    }
    private static int valueOfDigit(String[] data,char c){
        int rs=-1;

            for (int i = 0; i < data.length; i++) {
                if (String.valueOf(c).toUpperCase().equals(data[i])) {
                    rs = i;
                    break;
                }
            }
        if (rs==-1){
            Log.d("Current Bug",String.valueOf(c)+"");
        }
        return rs;

    }

    public static String numberConverter(int typeInput,int typeOutput, String input){
        String rs="";
            String tmp = anotherToDecimal(input,typeInput);
            rs = decimalToAnother(tmp,typeOutput);


        return rs;

    }
    public static boolean checkBinaryPattern(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!((int) input.charAt(i) <= 49 && (int) input.charAt(i) >= 48)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDecimalPattern(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!((int) input.charAt(i) <= 57 && (int) input.charAt(i) >= 48)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkOctalPattern(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!((int) input.charAt(i) <= 55 && (int) input.charAt(i) >= 48)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkHexPattern(String input) {
        input = input.toUpperCase();
        for (int i = 0; i < input.length(); i++) {
            if (!((((int) input.charAt(i) <= 57 && (int) input.charAt(i) >= 48))
                    || ((int) input.charAt(i) <= 70 && (int) input.charAt(i) >= 65))) {
                return false;
            }
        }
        return true;
    }

}
