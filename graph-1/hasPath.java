/*Given an undirected graph G(V, E) and two vertices v1 and v2 (as integers), check if there exists any path between them or not. Print true if the path exists and false otherwise.
Note:
1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1. 
2. E is the number of edges present in graph G.
Input Format :
The first line of input contains two integers, that denote the value of V and E.
Each of the following E lines contains two integers, that denote that there exists an edge between vertex 'a' and 'b'.
The following line contain two integers, that denote the value of v1 and v2.
Output Format :
The first and only line of output contains true, if there is a path between v1 and v2 and false otherwise.
Constraints :
0 <= V <= 1000
0 <= E <= 1000
0 <= a <= V - 1
0 <= b <= V - 1
0 <= v1 <= 2^31 - 1
0 <= v2 <= 2^31 - 1
Time Limit: 1 second
Sample Input 1 :
4 4
0 1
0 3
1 2
2 3
1 3
Sample Output 1 :
true
Sample Input 2 :
6 3
5 3
0 1
3 4
0 3
Sample Output 2 :
false
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
        

        if(v == 0){
            System.out.print("false");
            return;
        }
        if(e == 0){
            String[] eV1 = br.readLine().split(" ");
        	int a = Integer.parseInt(eV1[0]);
        	int b = Integer.parseInt(eV1[1]);
            if(a==b){
                System.out.println("true");
            }else{
                System.out.println("false");
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
        String[] eV1 = br.readLine().split(" ");
        int a = Integer.parseInt(eV1[0]);
        int b = Integer.parseInt(eV1[1]);
        
        boolean[] visited = new boolean[v];
        boolean hasPath = hasPath(adjMet,a,b,visited);
        System.out.print(hasPath);

    }
    static boolean hasPath(int[][] arr,int sv,int ev,boolean[] visited){
        if(sv < 0 || ev >= visited.length)return false;
        if(arr[sv][ev] == 1){
            return true;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(sv);
        visited[sv] = true;
        while (!q.isEmpty()){
            int cv = q.peek();
            if(cv == ev){
                return true;
            }
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
            q.remove();
        }
        return false;


    }
}
