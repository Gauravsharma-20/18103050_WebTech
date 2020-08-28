package com.company;

import java.util.Scanner;
public class Q2{

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.println("Size of array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter array elements: ");
        for(int i=0;i<n;i++) {
            arr[i] = scan.nextInt();
        }
        int ans[] = new int[n];

        int count[] = new int[256];
        for (int i = 0; i < 256; ++i) {
            count[i] = 0;
        }

        for (int i = 0; i < n; ++i) {
            ++count[arr[i]];
        }

        for (int i = 1; i < 256; ++i) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            ans[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }
        for (int i = 0; i < n; ++i) {
            arr[i] = ans[i];
        }

        System.out.print("Elements after applying Counting Sort : ");
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
    }
}