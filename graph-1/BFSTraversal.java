/*Given an undirected and disconnected graph G(V, E), print its BFS traversal.
Note:
1. Here you need to consider that you need to print BFS path starting from vertex 0 only. 
2. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1. 
3. E is the number of edges present in graph G.
4. Take graph input in the adjacency matrix.
5. Handle for Disconnected Graphs as well
Input Format :
The first line of input contains two integers, that denote the value of V and E.
Each of the following E lines contains space separated two integers, that denote that there exists an edge between vertex a and b.
Output Format :
Print the BFS Traversal, as described in the task.
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
0 1 3 2
*/

import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        if(e==0){
            for(int i = 0;i<v;i++){
                System.out.print(i+" ");
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
        printBFS(adjMet,0,visited);
        

    }
    static void printBFS(int[][] arr , int sv,boolean[] visited){
        
        Queue<Integer> q = new LinkedList<>();
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
                    visited[j] = true;
                }
            }
            System.out.print(q.remove()+" ");
        }
        for(int i= 0;i<visited.length;i++){
            if(!visited[i])
                printBFS(arr,i,visited);
        }
        
    }
}
