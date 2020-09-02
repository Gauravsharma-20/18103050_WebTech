package com.company;

public class Q4{

    public static void main(String[] args) {
        long i = 1, sum = 1;
        long maxVal = Long.MAX_VALUE;
        while(i<maxVal){
            if(sum==i*i){
                System.out.println("Ans: "+i);
            }
            i += 1;
            sum += i;
        }
        System.out.println("Final loop, sum: "+sum+" n: "+i);
    }
}
