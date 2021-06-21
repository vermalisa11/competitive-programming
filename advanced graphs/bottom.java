/*We will use the following (standard) definitions from graph theory. Let V be a non-empty and finite set, its elements being called vertices (or nodes). Let E be a subset of the Cartesian product V×V, its elements being called edges. Then G=(V,E) is called a directed graph.

Let n be a positive integer, and let p=(e1,…,en) be a sequence of length n of edges ei∈E such that ei=(vi,vi+1)for a sequence of vertices (v1,…,vn+1). Then p is called a path from vertex v1 to vertex vn+1 in G and we say that vn+1 is reachable from v1, writing (v1→vn+1).

Here are some new definitions. A node v in a graph G=(V,E) is called a sink, if for every node w in G that is reachable from v, v is also reachable from w. The bottom of a graph is the subset of all nodes that are sinks, i.e., bottom(G)={v∈V∣∀w∈V:(v→w)⇒(w→v)}. You have to calculate the bottom of certain graphs.
Input Specification
The input contains several test cases, each of which corresponds to a directed graph G. Each test case starts with an integer number v, denoting the number of vertices of G=(V,E) where the vertices will be identified by the integer numbers in the set V={1,…,v}. You may assume that 1≤v≤5000. That is followed by a non-negative integer e and, thereafter, e pairs of vertex identifiers v1,w1,…,ve,we with the meaning that (vi,wi)∈E. There are no edges other than specified by these pairs. The last test case is followed by a zero.
Output Specification
For each test case output the bottom of the specified graph on a single line. To this end, print the numbers of all nodes that are sinks in sorted order separated by a single space character. If the bottom is empty, print an empty line.
Sample Input
3 3
1 3 2 3 3 1
2 1
1 2
0
Sample Output
1 3
2
*/
import javax.swing.plaf.IconUIResource;
import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
//        int t = in.nextInt();
        while (true){

            int v = in.nextInt();
            if(v == 0){
                return;
            }
            int e = in.nextInt();

            Vector<Integer>[] edges = new Vector[v];
            Vector<Integer>[] edgesT = new Vector[v];

            for(int i = 0;i<v;i++){
                edges[i] = new Vector<>();
                edgesT[i] = new Vector<>();
            }

            for(int i = 0;i<e;i++){
                int a = in.nextInt()-1;
                int b = in.nextInt()-1;

                edges[a].add(b);
                edgesT[b].add(a);
            }

            Set<Set<Integer>> ans = new HashSet<>();
            ans = getSSC(edges,edgesT,v);
            ArrayList<Integer> output = new ArrayList<>();
            for(Set<Integer> ls : ans){
                boolean flag = true;
                ArrayList<Integer> temp = new ArrayList<>();
                for(Integer i : ls){
                    temp.add(i);
                    int count = 0;
                    for(Integer j : edges[i]){
                        if(ls.contains(j)){
                            count++;
                        }
                    }
                    if(count != edges[i].size()){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    for(Integer j : temp){
                        output.add(j+1);
                        // System.out.print((j+1)+" ");
                    }
                    // System.out.println();
                }
                temp.clear();

            }
            Collections.sort(output);
            for(Integer i : output){
                System.out.print(i+" ");
            }
            System.out.println();


        }

    }

    static Set<Set<Integer>> getSSC(Vector<Integer>[] edges,Vector<Integer>[] edgesT,int v){
        Set<Integer> visited = new HashSet<>();
        Set<Set<Integer>> output = new HashSet<>();
        Stack<Integer> stk = new Stack<>();
        for(int i = 0;i<v;i++){
            if(!visited.contains(i)){
                dfs(edges,i,visited,stk);
            }

        }
        visited.clear();
        int count = 0;
        while (!stk.isEmpty()){
            int top = stk.peek();
            stk.pop();
            if(!visited.contains(top)){
                count++;
                Set<Integer> component = new HashSet<>();
                dfs2(edgesT,top,visited,component);
                output.add(component);
            }

        }
        return output;
    }
    static void dfs(Vector<Integer>[] edges,int start,Set<Integer> visited,Stack<Integer> stk){
        visited.add(start);
        for(int i = 0;i<edges[start].size();i++){
            int adj = edges[start].get(i);
            if (!visited.contains(adj)) {
                dfs(edges,adj,visited,stk);
            }
        }
        stk.push(start);
    }
    static void dfs2(Vector<Integer>[] edges,int start,Set<Integer> visited,Set<Integer> component){
        visited.add(start);
        component.add(start);

        for(int i = 0;i<edges[start].size();i++){
            int adj = edges[start].get(i);
            if (!visited.contains(adj)) {
                dfs2(edges,adj,visited,component);
            }
        }

    }
}
