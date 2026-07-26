package com.URI.URL_Shortner.Service.Util;


import org.springframework.stereotype.Service;

@Service
public class Base62encode {

    public static  final  String Alphabet="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static  final int Base=62;
    public static  String encode(long number){
        if(number==0){
            return String.valueOf(Alphabet.charAt(0));
        }
        long n=number;
        StringBuilder sb=new StringBuilder();
        while(n>0){
            int reminder=(int)(n%Base);
            sb.append(Alphabet.charAt(reminder));
            n=n/Base;
        }
        return sb.reverse().toString();
    }

}
