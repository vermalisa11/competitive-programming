/*Given an undirected graph G(V, E) and two vertices v1 and v2(as integers), find and print the path from v1 to v2 (if exists). Print nothing if there is no path between v1 and v2.
Find the path using DFS and print the first path that you encountered.
Note:
1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1. 
2. E is the number of edges present in graph G.
3. Print the path in reverse order. That is, print v2 first, then intermediate vertices and v1 at last.
4. Save the input graph in Adjacency Matrix.
Input Format :
The first line of input contains two integers, that denote the value of V and E.
Each of the following E lines contains two integers, that denote that there exists an edge between vertex 'a' and 'b'.
The following line contain two integers, that denote the value of v1 and v2.
Output Format :
Print the path from v1 to v2 in reverse order.
Constraints :
2 <= V <= 1000
1 <= E <= (V * (V - 1)) / 2
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
3 0 1
*/

import java.util.ArrayList;
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
        String[] ev = br.readLine().split(" ");
        int v = Integer.parseInt(ev[0]);
        int e = Integer.parseInt(ev[1]);

        int[][] arr = new int[v][v];
        for(int i = 0;i<v;i++){
            Arrays.fill(arr[i],0);
        }

        for(int i=0;i<e;i++){
            String[] s = br.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);

            arr[start][end] = 1;
            arr[end][start] = 1;
        }
        ev = br.readLine().split(" ");
        int a = Integer.parseInt(ev[0]);
        int b = Integer.parseInt(ev[1]);


        boolean[] visited = new boolean[v];
        Arrays.fill(visited,false);
        visited[a] = true;

        ArrayList<Integer> ls = printDFSPath(arr,a,b,visited,v);
//        System.out.println();
        for(Integer i : ls){
            System.out.print(i+" ");
        }
    }
    static ArrayList<Integer> printDFSPath(int[][] arr,int sv,int ev,boolean[] visited,int v){

        if(sv==ev){
            ArrayList<Integer> currAns = new ArrayList<>();
            currAns.add(sv);
            return currAns;
        }
        for(int j = 0;j<v;j++){
            if(arr[sv][j] == 1 && sv!=j && !visited[j]){
                ArrayList<Integer> currAns;
                visited[j] = true;
                currAns = printDFSPath(arr,j,ev,visited,v);
                if(!currAns.isEmpty()){
                    currAns.add(sv);
                    return currAns;
                }

            }
        }
        ArrayList<Integer> currAns = new ArrayList<>();
        return currAns;

    }
}
