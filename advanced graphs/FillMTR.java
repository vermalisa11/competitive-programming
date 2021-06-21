/*
A matrix B (consisting of integers) of dimension N × N is said to be good if there exists an array A (consisting of integers) such that B[i][j] = |A[i] - A[j]|, where |x| denotes absolute value of integer x.

You are given a partially filled matrix B of dimension N × N. Q of the entries of this matrix are filled by either 0 or 1. You have to identify whether it is possible to fill the remaining entries of matrix B (the entries can be filled by any integer, not necessarily by 0 or 1) such that the resulting fully filled matrix B is good.
Input
The first line of the input contains an integer T denoting the number of test cases.

The first line of each test case contains two space separated integers N, Q.

Each of the next Q lines contain three space separated integers i, j, val, which means that B[i][j] is filled with value val.
Output
For each test case, output "yes" or "no" (without quotes) in a single line corresponding to the answer of the problem.
Constraints
1 ≤ T ≤ 10^6
2 ≤ N ≤ 10^5
1 ≤ Q ≤ 10^6
1 ≤ i, j ≤ N
0 ≤ val ≤ 1
Sum of each of N, Q over all test cases doesn't exceed 106
Input
4
2 2
1 1 0
1 2 1
2 3
1 1 0
1 2 1
2 1 0
3 2
2 2 0
2 3 1
3 3
1 2 1
2 3 1
1 3 1
Output
yes
no
yes
no
*/


import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    static Vector<Pair>[] edges = new Vector[100004];
    static int[] col = new int[100004];
    static boolean error;
    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-->0){
            int n = in.nextInt();
            int q = in.nextInt();
            error = false;
            for(int i = 1;i<=n;i++){
//                edges[i].clear();
                edges[i] = new Vector<>();
                col[i] = -1;
            }
            for(int i = 0;i<q;i++){
                int sv = in.nextInt();
                int ev = in.nextInt();
                int val = in.nextInt();

                edges[sv].add(new Pair(ev,val));
                edges[ev].add(new Pair(sv,val));
            }

            for(int i = 1;i<=n;i++){
                if(col[i]==-1){
                    col[i] = 0;
                    dfs(i);
                }
            }
            System.out.println(error?"no":"yes");

        }
    }
    
    static void dfs(int start ){
        for(Pair edge : edges[start]){
            int next = edge.x;
            if(col[next]==-1){
                col[next] = col[start] ^ edge.y;
                dfs(next);
            }else if(col[next] != (col[start] ^ edge.y)){
                error = true;
            }
        }
    }
    
    static class Pair{
        int x,y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
