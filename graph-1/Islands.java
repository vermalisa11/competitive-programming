/*An island is a small piece of land surrounded by water . A group of islands is said to be connected if we can reach from any given island to any other island in the same group . Given V islands (numbered from 1 to V) and E connections or edges between islands. Can you count the number of connected groups of islands.
Input Format :
The first line of input contains two integers, that denote the value of V and E.
Each of the following E lines contains two integers, that denote that there exists an edge between vertex a and b. 
Output Format :
Print the count the number of connected groups of islands
Constraints :
0 <= V <= 1000
0 <= E <= (V * (V-1)) / 2
0 <= a <= V - 1
0 <= b <= V - 1
Time Limit: 1 second
Sample Input 1:
5 8
0 1
0 4
1 2
2 0
2 4
3 0
3 2
4 3
Sample Output 1:
1 
*/
import java.util.*;
public class Solution {
    static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
	public static int numConnected(int[][] edges, int n) {
        
		/* Your class should be named Solution
		 * Don't write main().
		 * Don't read input, it is passed as function argument.
		 * Return output and don't print it.
	 	 * Taking input and printing output is handled automatically.
        */
        if(n==0){
            return 0;
        }
        boolean[] visited = new boolean[n];
    	Arrays.fill(visited,false);
        ArrayList<Integer> ls = new ArrayList<>();
        allConnectedComponents(edges,0,visited,n,ls);
        return ans.size();
	}
    static void allConnectedComponents(int[][] arr,int sv,boolean[] visited,int v,ArrayList<Integer> ls){

        Queue<Integer> q = new LinkedList<>();
        ls.add(sv);
        q.add(sv);
        visited[sv] = true;
        while (!q.isEmpty()){
            int cv = q.peek();
            for(int j = 0 ; j<v;j++){
                if(cv==j)continue;
                if(visited[j]){
                    continue;
                }
                if(arr[cv][j]!=0){
                    q.add(j);
                    ls.add(j);
                    visited[j] = true;
                }
            }
            q.remove();
        }
        // if(ls.size() == v){
        //     Collections.sort(ls);
        // }
        ans.add(ls);
        ls=new ArrayList<>();
        for(int i= 0;i<visited.length;i++){
            if(!visited[i])
                allConnectedComponents(arr,i,visited,v,ls);
        }
    }


}
