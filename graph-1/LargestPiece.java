/*It's Gary's birthday today and he has ordered his favourite square cake consisting of '0's and '1's . But Gary wants the biggest piece of '1's and no '0's . A piece of cake is defined as a part which consist of only '1's, and all '1's share an edge with each other on the cake. Given the size of cake N and the cake, can you find the count of '1's in the biggest piece of '1's for Gary ?
Input Format :
The first line of input contains an integer, that denotes the value of N. 
Each of the following N lines contain N space separated integers.
Output Format :
Print the count of '1's in the biggest piece of '1's, according to the description in the task.
Constraints :
1 <= N <= 1000
Time Limit: 1 sec
Sample Input 1:
2
1 1
0 1
Sample Output 1:
3
*/


class Solution {

    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static int dfs(String[] edge, int n) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        int[][] arr = new int[n][n];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                arr[i][j] = Integer.parseInt(edge[i].charAt(j)+"");
            }
        }
//        for(int i =0;i<n;i++){
//            for(int j = 0;j<n;j++){
//                System.out.print(arr[i][j]+" ");
//            }
//            System.out.println();
//        }
        boolean[][] visited = new boolean[n][n];
        int ans = Integer.MIN_VALUE;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                int currAns = Integer.MIN_VALUE;
                if(arr[i][j] == 1 && !visited[i][j]){
                    visited[i][j] = true;
                    currAns = totalSet(arr,visited,i,j,n)+1;
                }
                if(ans<currAns){
                    ans = currAns;
                }
            }
        }
        return ans;
    }
    public static int totalSet(int[][] arr,boolean[][] visited,int x,int y,int n){
        if(x < 0 || y < 0 || x>=n || y>=n){
            return 0;
        }
        int ans = 0;
        if(x+1<n && !visited[x+1][y] && arr[x+1][y] == 1 ){
            visited[x+1][y] = true;
            ans = ans + 1+ totalSet(arr,visited,x+1,y,n) ;
        }
        if(x>=1 && !visited[x-1][y] && arr[x-1][y] == 1){
            visited[x-1][y] = true;
            ans =ans+1+ totalSet(arr,visited,x-1,y,n);
        }
        if(y>=1 && !visited[x][y-1] && arr[x][y-1] == 1){
            visited[x][y-1] = true;
            ans = ans+ 1+ totalSet(arr,visited,x,y-1,n);
        }
        if(y+1<n && !visited[x][y+1] && arr[x][y+1] == 1){
            visited[x][y+1] = true;
            ans = ans+1+ totalSet(arr,visited,x,y+1,n);
        }
//        System.out.println(ans);
        return ans;
    }
}
