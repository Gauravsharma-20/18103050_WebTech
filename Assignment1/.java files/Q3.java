package com.company;
import java.io.*;
import java.util.*;

public class Q3 {
//Assumption : Unidirectional Weighted Graph .
//Both part of the Question are solved simultaneously since the program gets
// all the paths and then checks for the one that is minimum in weight.
// and a workaround for negative edges also included.

    public static void Solve(int [][] graph,int v,int [] visited,int source,int destination,ArrayList<ArrayList<Integer>> paths,ArrayList<Integer> curr) {
        if(source == destination) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int i=0;i<curr.size();i++) {
                temp.add(curr.get(i));
            }
            paths.add(temp);
            return;
        }
        visited[source] = 1;
        for(int i=0;i<v;i++) {
            if(graph[source][i]!=0&&visited[i]==0)
            {
                curr.add(i);
                Solve(graph,v,visited,i,destination,paths,curr);
                //System.out.println();
                curr.remove(curr.size()-1);
            }
        }
        visited[source] = 0;
    }


    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Number of Vertices and Edges :");
        int v = scan.nextInt(),e = scan.nextInt();

        int [] visited = new int[v];
        for(int i=0;i<v;i++) {
            visited[i] = 0;
        }
        int [][] graph = new int[v][v];
        for(int i=0;i<v;i++) {
            for(int j=0;j<v;j++) {
                graph[i][j] = 0;
            }
        }
        System.out.println("Enter Number of Source, Destination and Weight :");

        for(int i=0;i<e;i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            graph[a-1][b-1] = c;
        }


        System.out.println("Enter Source and Destination Vertex:");
        int source = scan.nextInt(),destination = scan.nextInt();
        source--;
        destination--;

        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> curr = new ArrayList<Integer>();
        curr.add(source);

        Solve(graph,v,visited,source,destination,paths,curr);

        if(paths.isEmpty()) {
            System.out.println("Negative Cycle Exists!");
        }
        else {
            int ans = Integer.MAX_VALUE;
            for(int i=0;i<paths.size();i++)
            {
                int count = 0;
                for(int j=0;j<paths.get(i).size();j++)
                {
                   count += graph[paths.get(i).get(j)][paths.get(i).get(j+1)];
                }
                if(count<ans) {
                    ans = count;
                }
            }
            System.out.println("Minimum Distance between Source and Distance is:"+ ans);
        }
    }
}