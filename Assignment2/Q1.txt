package com.company;
import java.lang.*;
import java.util.*;

public class Q1 {
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Two Strings:");
        String a = scan.nextLine(), b = scan.nextLine();
        boolean flag = false;
        for(int i=0;i<a.length()&&i<b.length();i++) {
            if((int)a.charAt(i)==(int)b.charAt(i)) {
                continue;
            }
            else {
                System.out.println((int)a.charAt(i)-(int)b.charAt(i));
                flag = true;
                break;
            }
        }

        if(a.length()>b.length()&&flag==false) {
            System.out.println(a.charAt(b.length()-1));
        }
        else if(a.length()<b.length()&&flag==false) {
            System.out.println(b.charAt(a.length()-1));
        }
        else if(flag==false){
            System.out.println("0 - Strings are Lexicographically Equal");
        }
    }
}
