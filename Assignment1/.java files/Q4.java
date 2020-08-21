package com.company;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Arrays;
import java.util.Scanner;

public class Q4 {

    public static void main(String [] args) throws ParseException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the First String:");
        String a = scan.nextLine();
        System.out.println("Enter the Second String:");
        String b = scan.nextLine();

        if(a.length()!=b.length()) {
            System.out.println("Not Anagrams!");
        }
        int arr1 [] = new int[256], arr2[] = new int[256];
        Arrays.fill(arr1,0);
        Arrays.fill(arr2,0);
        for(int i=0;i<a.length();i++) {
            arr1[(int)a.charAt(i)]++;
        }
        for(int i=0;i<b.length();i++) {
            arr2[(int)b.charAt(i)]++;
        }
        boolean flag = false;
        for(int i=0;i<256;i++)
        {
            if(arr1[i]!=arr2[i]) {
                flag = true;
                break;
            }
        }
        if(flag) {
            System.out.println("Not Anagrams!");
        }
        else {
            System.out.println("Entered String are Anagrams of Each Other.");
        }
    }
}