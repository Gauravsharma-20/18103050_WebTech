package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Question1 {

    public static class Pair {
        public int first;
        public Boolean second;
        Pair(int a,Boolean b){
            this.first = a;
            this.second = b;
        }
    }

    public static int[] X = {-1,0,1,0};
    public static int[] Y = {0,1,0,-1};

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
        /* 
        //Greedy Solution - Fails for sample case too
        System.out.println(Question_1_Greedy(arr,n,m));

        //Continuous Segment and Vertical Lines (Mathematical Approach) - Fails for special edge cases only
        System.out.println(Question_1(arr,n,m));
        */

        //Solve(DFS) - Handles all possible cases.
        System.out.println(solve(arr,n,m));

    }

    /*
    [Since only the number of changes are required we can just check that instead of actually replacing the letters.
     we have 22 probable replacement for a letter in even in the worst case, that ensures there will always be a solution.]
    */
    public static int solve(char[][] arr,int n,int m){
        if(m<2||n<1){
            return -1;
        }
        boolean[][] visited = new boolean[n][m];
        for(boolean[] row:visited) {
            Arrays.fill(row,false);
        }
       int finalAns = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if (!visited[i][j]) {
                    //first-> val, second->change_flag
                    visited[i][j] = true;
                    Pair Ans = new Pair(0, false);
                    for (int z = 0; z < 4; z++) {
                        int newX = i + X[z], newY = j + Y[z];
                        if (validPosition(newX,newY,n,m)&&(arr[newX][newY] == arr[i][j])) {
                                Pair smallAns = DFS(arr, newX, newY, n, m, visited);
                                Ans.first += smallAns.first;
                                Ans.second = Ans.second || smallAns.second;
                        }
                    }
                    finalAns += Ans.second ? Ans.first + 1 : Ans.first;
                }
            }
        }
        return finalAns;
    }

    public static boolean validPosition(int i,int j,int n,int m) {
        if (i < n && i > -1 && j > -1 && j < m) {
            return true;
        }
        return false;
    }

    public static Pair DFS(char[][] arr,int i,int j,int n,int m,boolean[][] visited) {
        //check for no neighbours
        boolean currNeighbourFlag = false;
        for (int z = 0; z < 4; z++) {
            int newX = i + X[z], newY = j + Y[z];
            if (validPosition(newX, newY, n, m)&&!visited[newX][newY]) {
                if (arr[newX][newY] == arr[i][j]) {
                    currNeighbourFlag = true;
                }
            }
        }
        visited[i][j] = true;
        //Leaf Node
        if(!currNeighbourFlag){
            return new Pair(0,true);
        }

        //first-> val, second->change_flag
        Pair Ans = new Pair(0, false);
        for (int z = 0; z < 4; z++) {
            int newX = i + X[z], newY = j + Y[z];
            if (validPosition(newX,newY,n,m)&&!visited[newX][newY]&&(arr[newX][newY] == arr[i][j])) {
                Pair smallAns = DFS(arr, newX, newY, n, m, visited);
                Ans.first += smallAns.first;
                Ans.second = Ans.second || smallAns.second;
            }
        }
        if(Ans.second){
            Ans.first += 1;
        }
        Ans.second = !Ans.second;
        return Ans;
    }

    /* Previous Methods
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
     */
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