// Given an integer N, find and return the count of unique Binary search trees (BSTs) are possible with nodes valued from 1 to N.
// Output count can be very large, so return the count modulo 10^9+7.
// Input Format :
// Integer n 
// Output Format :
// Count of BSTs
// Contraints :
// 1<= N <=1000
// Sample Input 1:
// 8
// Sample Output 1:
// 1430
// Sample Input 2:
// 3
// Sample Output 2:
// 5


public class Solution{	
    
    static int M = 1000000007;
	public static int countTrees(int n) {
	/* Your class should be named Solution
		 * Don't write main().
		 * Don't read input, it is passed as function argument.
		 * Return output and don't print it.
	 	 * Taking input and printing output is handled automatically.
		*/
        long[] dp = new long[n+1];
    	return (int) fun(dp, n);
        
	}
    
    public static long fun(long[] dp, int n) {
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        if(dp[n]>0) {
            return dp[n];
        }
        for(int k=1; k<=n; k++) {
            dp[n]=(dp[n]%M + fun(dp, k-1)%M * fun(dp, n-k)%M)%M;
        }
        return dp[n];
    }
}