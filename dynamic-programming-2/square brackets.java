/*
Square Brackets
Send Feedback
You are given:
a positive integer n,
an integer k, 1<=k<=n,
an increasing sequence of k integers 0 < s1 < s2 < ... < sk <= 2n.
What is the number of proper bracket expressions of length 2n with opening brackets appearing in positions s1, s2,...,sk?
Illustration
Several proper bracket expressions:
[[]][[[]][]] 
[[[][]]][][[]]
An improper bracket expression:
[[[][]]][]][[]]
There is exactly one proper expression of length 8 with opening brackets in positions 2, 5 and 7.
Task
Write a program which for each data set from a sequence of several data sets:
1. reads integers n, k and an increasing sequence of k integers from input,
2. computes the number of proper bracket expressions of length 2n with opening brackets appearing at positions s1,s2,...,sk,
3. writes the result to output.
Input
The first line of the input file contains one integer d, 1 <= d <= 10, which is the number of data sets. The data sets follow. Each data set occupies two lines of the input file. The first line contains two integers n and k separated by single space, 1 <= n <= 19, 1 <= k <= n. The second line contains an increasing sequence of k integers from the interval [1;2n] separated by single spaces.
Output
The i-th line of output should contain one integer - the number of proper bracket expressions of length 2n with opening brackets appearing at positions s1, s2,...,sk.
Sample Input
5 
1 1 
1 
1 1 
2 
2 1 
1 
3 1 
2 
4 2 
5 7 
Sample Output
1 
0 
2 
3 
2 
*/
import java.util.Scanner;

public class Main {
	public final static int N = 1000;
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// Write your code here
		int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            int k = in.nextInt();
            
            int[] pos = new int[k];
            for(int i =0;i<k;i++){
				pos[i] = in.nextInt();
            } 
            boolean h[] = new boolean[N]; 

            int dp[][] = new int[N][N]; 

            for (int i = 0; i < k; i++) { 
                h[pos[i]] = true; 
            } 

            dp[0][0] = 1; 

            for (int i = 1; i <= 2 * n; i++) { 
                for (int j = 0; j <= 2 * n; j++) { 

                    if (h[i]) { 
                        if (j != 0) { 
                            dp[i][j] = dp[i - 1][j - 1]; 
                        } else { 
                            dp[i][j] = 0; 
                        } 
                    } else if (j != 0) { 
                        dp[i][j] = dp[i - 1][j - 1] 
                                + dp[i - 1][j + 1]; 
                    } else { 
                        dp[i][j] = dp[i - 1][j + 1]; 
                    } 
                } 
            } 

            System.out.println(dp[2*n][0]);
		}
    }

}
