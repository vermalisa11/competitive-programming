/*
Trader Profit
Send Feedback
Mike is a stock trader and makes a profit by buying and selling stocks. He buys a stock at a lower price and sells it at a higher price to book a profit. He has come to know the stock prices of a particular stock for n upcoming days in future and wants to calculate the maximum profit by doing the right transactions (single transaction = buying + selling). Can you help him maximize his profit?
Note: A transaction starts after the previous transaction has ended. Two transactions can't overlap or run in parallel.
The stock prices are given in the form of an array A for n days.
Given the stock prices and a positive integer k, find and print the maximum profit Mike can make in at most k transactions.
Input Format
The first line of input contains an integer q denoting the number of queries.

The first line of each test case contains a positive integer k, denoting the number of transactions. 

The second line of each test case contains a positive integer n, denoting the length of the array A.

The third line of each test case contains n space-separated positive integers, denoting the prices of each day in the array A.
Constraints
1<=q<=100

0<k<10

2<=n<=30

0<=elements of array A<=1000
Output Format
For each query print the maximum profit earned by Mike on a new line. 
Sample Input
3
2
6
10 22 5 75 65 80
3
4
20 580 420 900
1
5
100 90 80 50 25
Sample Output
87
1040
0
*/
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		// Write your code here
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0) {
            int k = sc.nextInt();
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i]=sc.nextInt();
            }
            
            int[][] dp = new int[k+1][n];            
      
            for (int i=1; i<=k; i++) {
                int maxSoFar=dp[i-1][0]-arr[0];
                for(int j=1; j<n; j++) {
                    int c= dp[i-1][j-1]-arr[j-1];
                    maxSoFar=Math.max(c,maxSoFar);
                    dp[i][j] = Math.max(dp[i][j-1], (arr[j]+maxSoFar) );
                }
            }
//             for(int i=0; i<=k; i++) {
//                 for(int j=0; j<n; j++) {
//                     System.out.print(dp[i][j]);
//                 }
//                 System.out.println();
//             }
            
            System.out.println(dp[k][n-1]);
        }       
	}
}
