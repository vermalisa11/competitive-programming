/*Given an undirected graph G(V,E), find and print all the connected components of the given graph G.
Note:
1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1. 
2. E is the number of edges present in graph G.
3. You need to take input in main and create a function which should return all the connected components. And then print them in the main, not inside function.
Print different components in new line. And each component should be printed in increasing order (separated by space). Order of different components doesn't matter.
Input Format :
The first line of input contains two integers, that denote the value of V and E.
Each of the following E lines contains two space separated integers, that denote that there exists an edge between vertex a and b.
Output Format :
Print different components in new line. And each component should be printed in increasing order of vertices (separated by space). Order of different components doesn't matter.
Constraints :
0 <= V <= 1000
0 <= E <= (V * (V - 1)) / 2
0 <= a <= V - 1
0 <= b <= V - 1
Sample Input 1:
4 2
0 1
2 3
Sample Output 1:
0 1 
2 3 
Sample Input 2:
4 3
0 1
1 3 
0 3
Sample Output 2:
0 1 3 
2
*/

import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    public static void main(String[] args) throws NumberFormatException, IOException {

        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        String[] eV = br.readLine().split(" ");
        int v = Integer.parseInt(eV[0]);
        int e = Integer.parseInt(eV[1]);

        if(v == 0){
            System.out.print(" ");
            return;
        }
        if(e == 0){
            for(int i = 0;i<v;i++){
                System.out.println(i);
            }
            return;
        }

        int[][] adjMet = new int[v][v];
        for(int i = 0;i<v;i++){
            Arrays.fill(adjMet[i],0);
        }
        for(int i = 0;i<e;i++){
            String[] s = br.readLine().split(" ");
            int e1 = Integer.parseInt(s[0]);
            int e2 = Integer.parseInt(s[1]);

            adjMet[e1][e2] = 1;
            adjMet[e2][e1] = 1;
        }

        boolean[] visited = new boolean[v];
        Arrays.fill(visited,false);
        ArrayList<Integer> temp = new ArrayList<>();
        allConnectedComponents(adjMet,0,visited,v,temp);

        for(ArrayList<Integer> ls : ans){
            for(Integer i : ls){
                System.out.print(i+" ");
            }
            System.out.println();
        }



    }
    static void allConnectedComponents(int[][] arr,int sv,boolean[] visited,int v,ArrayList<Integer> ls){

        Queue<Integer> q = new LinkedList<>();
        ls.add(sv);
        q.add(sv);
        visited[sv] = true;
        while (!q.isEmpty()){
            int cv = q.peek();
            for(int j = 0 ; j<v;j++){
                if(cv==j)continue;
                if(visited[j]){
                    continue;
                }
                if(arr[cv][j]!=0){
                    q.add(j);
                    ls.add(j);
                    visited[j] = true;
                }
            }
            q.remove();
        }
        
        Collections.sort(ls);
        
        ans.add(ls);
        ls=new ArrayList<>();
        for(int i= 0;i<visited.length;i++){
            if(!visited[i])
                allConnectedComponents(arr,i,visited,v,ls);
        }
    }
}
