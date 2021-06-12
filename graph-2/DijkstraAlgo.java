/*Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
Find and print the shortest distance from the source vertex (i.e. Vertex 0) to all other vertices (including source vertex also) using Dijkstra's Algorithm.
Input Format :
Line 1: Two Integers V and E (separated by space)
Next E lines : Three integers ei, ej and wi, denoting that there exists an edge between vertex ei and vertex ej with weight wi (separated by space)
Output Format :
For each vertex, print its vertex number and its distance from source, in a separate line. The vertex number and its distance needs to be separated by a single space.
Note : Order of vertices in output doesn't matter.
Constraints :
2 <= V, E <= 10^5
Time Limit: 1 sec
Sample Input 1 :
4 4
0 1 3
0 3 5
1 2 1
2 3 8
Sample Output 1 :
0 0
1 3
2 4
3 5
*/

import java.util.*;

public class Solution {
static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int v = in.nextInt();
        int e = in.nextInt();

        int[][] graph = new int[v][v];
        for(int i= 0;i<e;i++){
            int sv = in.nextInt();
            int ev = in.nextInt();
            int weight = in.nextInt();

            graph[sv][ev] = weight;
            graph[ev][sv] = weight;
        }
        dijkstra(graph,v);
    }

    static void dijkstra(int[][] graph,int v){
        boolean[] visited = new boolean[v];
        int[] distance = new int[v];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0] = 0;  // as source vertex is 0
//        visited[0] = true;

        int count = 0;
        while (count<v){
            int currV = getMinVertex(visited,distance,v);
            visited[currV] = true;
//            System.out.println(currV);
            for(int j = 0;j<v;j++){
                if(!visited[j] && graph[currV][j] !=0){
                    if(distance[currV] + graph[currV][j] < distance[j]){
                        distance[j] = distance[currV] + graph[currV][j];
                    }
                }
            }

            count++;
        }
        for(int i =0;i<v;i++){
            System.out.println(i+" "+ distance[i]);
        }
    }
    static int getMinVertex(boolean[] visited,int[] distance,int v){
        int min = -1;
        int minDis = Integer.MAX_VALUE;
        for(int i = 0;i<v;i++){
            if(!visited[i]){
                if(minDis> distance[i]){
                    minDis = distance[i];
                    min = i;
                }
            }
        }
        return min;
    }
}
