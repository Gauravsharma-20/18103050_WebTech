package com.company;
import java.util.*;

public class Q1 {

    static int solution_1(String pat, String txt) {
        int ans = 0;
        int n = txt.length();
        int m = pat.length();
        for(int i=0;i<=n-m;i++)
        {
            int j;
            for (j = 0; j<m; j++)
            {
                if(txt.charAt(i + j) != pat.charAt(j))
                { //Doesn't match
                    break;
                }
            }
            if(j==m)
            {
                //incremented result- pattern matched
                ans++;
                j = 0;
            }
        }
        return ans;
    }

    //Main
    static public void main(String[] args) {
        //Input
        Scanner scan=new Scanner(System.in);
        //The above statement creates a constructor of the Scanner
        // class having System.in as an argument.
        String txt = scan.nextLine();              //reads string
        String pat = scan.nextLine();
        //Output
        System.out.println(solution_1(pat, txt));
    }
}