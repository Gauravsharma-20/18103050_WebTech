package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Assignment_4 {

    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        char[][] arr = new char[n][m];
        for(int i=0;i<n;i++){
            String data;
            if(scan.hasNext()) {
                data = scan.next();
            } else {
                break;
            }
            for(int j=0;j<m;j++){
                arr[i][j] = data.charAt(j);
            }
        }
        
        //Greedy Solution 
        System.out.println(Question_1_Greedy(arr,n,m));

        //Continuous Segment and Vertical Lines 
        System.out.println(Question_1(arr,n,m));
    }

    public static int[] X = { -1, 0, 1, 0 };
    public static int[] Y = { 0, 1, 0, -1 };

    public static int Question_1(char[][] arr,int n,int m){
        if(m<2||n<1){
            return 0;
        }
        int ans = 0;

        //Positions to Check

        boolean[][] out = new boolean[n][m];
        //Make bool true-false out  matrix
        for(int i=0;i<n;i++){
            for(int j=m-1;j>-1;j--){
                //flag->checks if state follows condition
                //changeFlag->if plot was changed
                boolean changeFlag = false;
                //check valid position
                for (int z = 0; z < 4; z++) {
                    int newX = i + X[z], newY = j + Y[z];
                    if (newX < n && newX > -1 && newY > -1 && newY < m) {
                        if (arr[newX][newY] == arr[i][j]) {
                             changeFlag = true;
                        }
                    }
                }
                out[i][j] = changeFlag;
            }
        }
        //Horizontal
        for(int i=0;i<n;i++) {//row
            //continuous true chain
            int s=0,e=0;
            while(e<m) {
                if(!out[i][e]) {
                    e--;
                    int smallAns = (e - s + 1) / 2;
                    if(smallAns>0){
                        ans+=smallAns;
                        for(int z=s;z<=e;z++){
                            out[i][z] = false;
                        }
                    }
                    e+=2;
                    s = e;
                } else {
                    e++;
                }
            }
            //after end - last check
            e--;
            int smallAns = (e - s + 1) / 2;
            if(smallAns>0) {
                ans+=smallAns;
                for(int z=s;z<=e;z++) {
                    out[i][z] = false;
                }
            }
        }
        //System.out.println(Arrays.deepToString(out));

        //vertical
        for(int i=0;i<m;i++) {//col
            //continuous true chain
            int s=0,e=0;
            while(e<n) {
                if(!out[e][i]) {
                    e--;
                    int smallAns = (e - s + 1) / 2;
                    if(smallAns>0){
                        ans+=smallAns;
                        for(int z=s;z<=e;z++){
                            out[z][i] = false;
                        }
                    }
                    e += 2;
                    s = e;
                } else{
                    e++;
                }
            }
            //Check after
            e--;
            int smallAns = (e - s + 1) / 2;
            if(smallAns>0){
                ans+=smallAns;
                for(int z=s;z<=e;z++){
                    out[z][i] = false;
                }
            }
        }

        return ans;
    }
    
    public static int Question_1_Greedy(char[][] arr,int n,int m){
        //cannot have two different types of crops or no element
        if(m<2||n<1){
            return 0;
        }
        int ans = 0;
        //Positions to Check
        int[] X = {-1,0,1,0};
        int[] Y = {0,1,0,-1};

        for(int i=0;i<n;i++){
            for(int j=m-1;j>-1;j--){
                //flag->checks if state follows condition
                //changeFlag->if plot was changed
                boolean flag = false, changeFlag = false;
                //check valid position
                while(!flag) {
                    for (int z = 0; z < 4; z++) {
                        int newX = i + X[z], newY = j + Y[z];
                        if (newX < n && newX > -1 && newY > -1 && newY < m) {
                            if (arr[newX][newY] == arr[i][j]) {
                                char temp = arr[i][j];
                                temp += ((temp - 'a') + 1) % 26;
                                arr[i][j] = temp;
                                changeFlag = true;
                                break;
                            }
                        }
                        if (z == 3) {
                            flag = true;
                        }
                    }
                }
                if(changeFlag){
                    ans++;
                }
            }
        }
        return ans;
    }
     
}
/*
4 4
acaa
dddd
bbbb
ccce

4 4
acaa
dddd
bbbd
cccd
*/