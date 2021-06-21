/*Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
Find and print the Minimum Spanning Tree (MST) using Kruskal's algorithm.
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
Time Limit: 1 sec
Sample Input 1 :
4 4
0 1 3
0 3 5
1 2 1
2 3 8
Sample Output 1 :
1 2 1
0 1 3
0 3 5
*/

import java.util.*;

public class Solution {
 	static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int v = in.nextInt();
        int e = in.nextInt();
        edge[] edges = new edge[e];

        for(int i = 0;i<e;i++){
            edges[i] = new edge(in.nextInt(),in.nextInt(),in.nextInt());
        }
        Arrays.sort(edges, (edge, t1) -> {
            if(edge.weight == t1.weight){
                if(edge.sv==t1.sv){
                    return edge.ev-t1.ev;
                }
                return edge.sv-t1.sv;
            }
            return edge.weight-t1.weight;
        });
//        for(edge edge : edges){
//            System.out.println(edge.sv+" "+ edge.ev + " "+ edge.weight);
//        }
        ArrayList<edge> output = new ArrayList<>();
        int[] allV = new int[v];
        for(int i = 0;i<v;i++){
            allV[i] = i;
        }
//        for(int j = 0; j<v;j++){
//            System.out.print(allV[j]+" ");
//        }
        output = solve(edges,allV,v,e);
        for(edge edge : output){
            if(edge.sv <= edge.ev){
                System.out.println(edge.sv+" "+edge.ev+" "+edge.weight);
            }else{
                System.out.println(edge.ev+" "+edge.sv+" "+ edge.weight);
            }
        }
    }
    
   static ArrayList<edge> solve(edge[] edges, int[] allV, int v, int e){
        ArrayList<edge> output = new ArrayList<>();
        int i = 0,count=0;
        while (count < v-1 && i<e){
            if(isCycleForm(allV,edges[i].sv,edges[i].ev)){
//                System.out.println(edges[i].sv+" "+ edges[i].ev);
                i++;
                continue;
            }
            allV[topMostParent(allV, edges[i].sv)] = topMostParent(allV, edges[i].ev);
            // allV[edges[i].ev] = allV[edges[i].sv];
//            for(int j = 0; j<v;j++){
//                System.out.print(allV[j]+" ");
//            }
            output.add(edges[i++]);
            count++;
        }
        return output;
   }
    
   static boolean isCycleForm(int[] allV,int sv,int ev){
       return topMostParent(allV, sv) == topMostParent(allV, ev);
   }
    
   static int topMostParent(int[] allV,int vertex){
        if(allV[vertex] == vertex){
           return vertex;
        }
       return topMostParent(allV,allV[vertex]);
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
