/*Monk visits the land of Islands. There are a total of N islands numbered from 1 to N. Some pairs of islands are connected to each other by Bidirectional bridges running over water.
Monk hates to cross these bridges as they require a lot of efforts. He is standing at Island #1 and wants to reach the Island #N. Find the minimum the number of bridges that he shall have to cross, if he takes the optimal route.
Input:
First line contains T. T testcases follow.
First line of each test case contains two space-separated integers N, M.
Each of the next M lines contains two space-separated integers X and Y , denoting that there is a bridge between Island X and Island Y.
Output:
Print the answer to each test case in a new line.
Constraints:
1 ≤ T ≤ 10
1 ≤ N ≤ 10000
1 ≤ M ≤ 100000
1 ≤ X, Y ≤ N
SAMPLE INPUT
2
3 2
1 2
2 3
4 4
1 2
2 3
3 4
4 2
SAMPLE OUTPUT
2
2
*/
import java.util.*;

public class Main{
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            int m = in.nextInt();

            Vector<Integer>[] graph = new Vector[n];
            for (int i =0;i<n;i++){
                graph[i] = new Vector<>();
            }

            for(int i = 0;i<m;i++){
                int a = in.nextInt()-1;
                int b = in.nextInt()-1;

                graph[a].add(b);
                graph[b].add(a);
            }
            System.out.println(bfs(graph,n));
        }
    }
    static int bfs(Vector<Integer>[] graph,int n){
        boolean[] visited = new boolean[n];

        int[] lvl = new int[n+1];
        Arrays.fill(lvl,0);

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()){
            int curr = q.peek();
            q.remove();
            for(int i = 0;i<graph[curr].size();i++){
                int next = graph[curr].get(i);
                if(!visited[next]){
                    q.add(next);
                    visited[next] = true;
                    lvl[next] = lvl[curr]+1;
                }
            }
        }
        return lvl[n-1];

    }
}
