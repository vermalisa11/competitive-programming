/*Given an undirected graph G(V, E) and two vertices v1 and v2 (as integers), find and print the path from v1 to v2 (if exists). Print nothing if there is no path between v1 and v2.
Find the path using BFS and print the shortest path available.
Note:
1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1. 
2. E is the number of edges present in graph G.
3. Print the path in reverse order. That is, print v2 first, then intermediate vertices and v1 at last.
4. Save the input graph in Adjacency Matrix.
Input Format :
The first line of input contains two integers, that denote the value of V and E.
Each of the following E lines contains two integers, that denote that there exists an edge between vertex a and b.
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
        eV = br.readLine().split(" ");
        int a = Integer.parseInt(eV[0]);
        int b = Integer.parseInt(eV[1]);
        boolean[] visited = new boolean[v];
        ArrayList<Integer> ans = getBFSPath(adjMet,a,b,visited,v);
        for(Integer i : ans){
            System.out.print(i+" ");
        }

//        boolean isConnected= isConnected(adjMet,0,visited);
//        System.out.print(isConnected);

    }
    static ArrayList<Integer> getBFSPath(int[][] arr,int sv,int ev,boolean[] visited,int v){
        Queue<Integer> q = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();
        q.add(sv);
        visited[sv] = true;
        boolean flag = true;
        while (!q.isEmpty() && flag){
            int cv = q.peek();
            for(int j = 0 ; j<visited.length;j++){
                if(cv==j)continue;
                if(visited[j]){
                    continue;
                }
                if(arr[cv][j]!=0){
                    q.add(j);
                    map.put(j,cv);
                    visited[j] = true;
                    if(j==ev) {
                        flag = false;
                    }
                }
            }
            q.remove();
        }
//        for(Integer i : map.keySet()){
//            System.out.println(i +" "+ map.get(i));
//        }
        ArrayList<Integer> ls = new ArrayList<>();
        int start = ev;
        while (true){
            if(!map.containsKey(start)){
                break;
            }
            ls.add(start);
            start = map.get(start);
        }
        if(!ls.isEmpty()){
            ls.add(sv);
        }
        return ls;

    }
}
