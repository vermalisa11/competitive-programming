/*Dominos are lots of fun. Children like to stand the tiles on their side in long lines. When one domino falls, it knocks down the next one, which knocks down the one after that, all the way down the line.
However, sometimes a domino fails to knock the next one down. In that case, we have to knock it down by hand to get the dominos falling again.
Your task is to determine, given the layout of some domino tiles, the minimum number of dominos that must be knocked down by hand in order for all of the dominos to fall.
Input
The first line of input contains one integer specifying the number of test cases to follow.Each test case begins with a line containing two integers,each no larger than 100 000. The first integer n is the number of domino tiles and the second integer m is the number of lines to follow in the test case. The domino tiles are numbered from 1 to n.
Each of the following lines contains two integers x and y indicating that if domino number x falls, it will cause domino number y to fall as well.
Output
For each test case, output a line containing one integer, the minimum number of dominos that must be knocked over by hand in order for all the dominos to fall.
Sample Input
1
3 2
1 2
2 3
Sample Output
1
*/
import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-->0){
            int v = in.nextInt();
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
            int output = getSSC(edges,edgesT,v);
            System.out.println(output);

        }

    }

    static int getSSC(Vector<Integer>[] edges,Vector<Integer>[] edgesT,int v){
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
                dfs2(edges,top,visited,component);
                output.add(component);
            }

        }
        return count;
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
