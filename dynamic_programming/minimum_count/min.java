// Given an integer N, find and return the count of minimum numbers required to represent N as a sum of squares.
// That is, if N is 4, then we can represent it as : {1^2 + 1^2 + 1^2 + 1^2} and {2^2}. The output will be 1, as 1 is the minimum count of numbers required to represent N as sum of squares.
// Input format :
// The first and the only line of input contains an integer value, 'N'.
//  Output format :
// Print the minimum count of numbers required.
// Constraints :
// 0 <= n <= 10 ^ 4

// Time Limit: 1 sec
// Sample Input 1 :
// 12
// Sample Output 1 :
// 3
// Explanation of Sample Output 1 :
// 12 can be represented as : 
// A) (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1)

// B) (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1)  + (2 ^ 2)

// C) (1^1) + (1^1) + (1^1) + (1^1) + (2 ^ 2) + (2 ^ 2)

// D) (2 ^ 2) + (2 ^ 2) + (2 ^ 2)

// As we can see, the output should be 3.
// Sample Input 2 :
// 9
// Sample Output 2 :
// 1



public class Solution {

	public static int minCount(int n) {
		//Your code goes here
        //8
        //143
        // 1 4 9 16 25 36 49 64 81 100 121 144 169 196 225 256 
        //return 2;
        int[] dp = new int[n+1];
        
        if(n<4){
            return n;
        }
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        dp[3]=3;
        
        for(int i=4; i<=n; i++) {
            dp[i]=dp[i-1]+1;
            
            int k=1;
            while (k*k <=i) {
                if (dp[i] > dp[i - k * k] + 1) {
                    dp[i] = dp[i - k * k] + 1;
                }
                k++;
            }
        }
        
         return dp[n];   
	}
}    
