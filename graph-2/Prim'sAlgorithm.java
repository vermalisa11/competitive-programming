/*Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
Find and print the Minimum Spanning Tree (MST) using Prim's algorithm.
For printing MST follow the steps -
1. In one line, print an edge which is part of MST in the format - 
v1 v2 w
where, v1 and v2 are the vertices of the edge which is included in MST and whose weight is w. And v1  <= v2 i.e. print the smaller vertex first while printing an edge.
2. Print V-1 edges in above format in different lines.
Note : Order of different edges doesn't matter.
Input Format :
Line 1: Two Integers V and E (separated by space)
Next E lines : Three integers ei, ej and wi, denoting that there exists an edge between vertex ei and vertex ej with weight wi (separated by space)
Output Format :
Print the MST, as described in the task.
Constraints :
2 <= V, E <= 10^5
1 <= Wi <= 10^5
Time Limit: 1 sec
Sample Input 1 :
4 4
0 1 3
0 3 5
1 2 1
2 3 8
Sample Output 1 :
0 1 3
1 2 1
0 3 5
*/

import java.util.*;

public class Solution {
static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int v = in.nextInt();
        int e = in.nextInt();
        int[][] graph = new int[v][v];
        for(int i= 0;i<e;i++){
            int sv =in.nextInt();
            int ev =in.nextInt();
            int weight =in.nextInt();
            graph[sv][ev] = weight;
            graph[ev][sv] = weight;
        }
//        boolean[] visited = new boolean[v];
        boolean[] notVisited = new boolean[v];
        Arrays.fill(notVisited,true);

        int[] weight = new int[v];
        Arrays.fill(weight,Integer.MAX_VALUE);
        weight[0] = 0;                                     //let source vertex be 0

        int[] parent = new int[v];
        parent[0] = -1;                          // as 0 is taken source vertex

        ArrayList<edge> output = primsalgo(graph,parent,weight,notVisited,v);
//        for(int i = 0;i<v;i++){
//            System.out.println(parent[i]+" "+ weight[i]);
//        }
        for(edge edge : output){
            if(edge.sv <= edge.ev){
                System.out.println(edge.sv+" "+ edge.ev +" "+edge.weight);
            }else{
                System.out.println(edge.ev+" "+ edge.sv +" "+edge.weight);
            }
//            System.out.println();
        }

    }
    static ArrayList<edge> primsalgo(int[][] graph,int[] parent,int[] weight,boolean[] notVisited,int v){
        ArrayList<edge> output = new ArrayList<>();
        int count = 0;
        while(count<v){
            int currV = chosenVertex(notVisited,weight,v);
//            for(int i = 0;i<v;i++){
//                System.out.println(parent[i]+" "+ weight[i]);
//            }
//            System.out.println();

//            System.out.println(currV);
            for(int i = 0 ;i<v;i++ ){
                if(graph[currV][i] != 0 && notVisited[i]){
                    if(weight[i] > graph[currV][i]){
                        parent[i] = currV;
                        weight[i] = graph[currV][i];
                    }
                }
            }
            notVisited[currV] = false;
            count++;
        }
        for(int i = 1;i<v;i++){
            output.add(new edge(i,parent[i],weight[i]));
        }
        return output;
    }
    static int chosenVertex(boolean[] notVisited,int[] weight,int v){
        int w = Integer.MAX_VALUE;
        int index = -1;
        for(int i = 0;i<v;i++){
            if(notVisited[i]){
                if(w >  weight[i]){
                    w = weight[i];
                    index = i;
                }
            }
        }
        return index;
    }
    static class edge{
        int sv,ev,weight;

        public edge(int sv, int ev, int weight) {
            this.sv = sv;
            this.ev = ev;
            this.weight = weight;
        }
    }
}
