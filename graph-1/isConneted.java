/*Given an undirected graph G(V,E), check if the graph G is connected graph or not.
Note:
1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1. 
2. E is the number of edges present in graph G.
Input Format :
The first line of input contains two integers, that denote the value of V and E.
Each of the following E lines contains two integers, that denote that there exists an edge between vertex a and b.
Output Format :
The first and only line of output contains "true" if the given graph is connected or "false", otherwise.
Constraints :
0 <= V <= 1000
0 <= E <= (V * (V - 1)) / 2
0 <= a <= V - 1
0 <= b <= V - 1
Time Limit: 1 second
Sample Input 1:
4 4
0 1
0 3
1 2
2 3
Sample Output 1:
true
Sample Input 2:
4 3
0 1
1 3 
0 3
Sample Output 2:
false 
Sample Output 2 Explanation
The graph is not connected, even though vertices 0,1 and 3 are connected to each other but there isn’t any path from vertices 0,1,3 to vertex 2. 
*/

import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws NumberFormatException, IOException {

        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        String[] eV = br.readLine().split(" ");
        int v = Integer.parseInt(eV[0]);
        int e = Integer.parseInt(eV[1]);

        if(v == 0 || v == 1){
            System.out.print("true");
            return;
        }
        if(e == 0){
            System.out.println("false");
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
        boolean isConnected= isConnected(adjMet,0,visited);
        System.out.print(isConnected);

    }
    static boolean isConnected(int[][] arr , int sv,boolean[] visited){

        Queue<Integer> q = new LinkedList<>();
        List<Integer> ls = new ArrayList<>();
        ls.add(sv);
        q.add(sv);
        visited[sv] = true;
        while (!q.isEmpty()){
            int cv = q.peek();
            for(int j = 0 ; j<visited.length;j++){
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
//        for(Integer i : ls ){
//            System.out.print(i+" ");
//        }
        if(ls.size() == visited.length){
            return true;
        }else{
            return false;
        }


    }
}
