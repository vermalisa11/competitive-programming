/*
Miser Man
Send Feedback
Jack is a wise and miser man. Always tries to save his money.
One day, he wants to go from city A to city B. Between A and B, there are N number of cities(including B and excluding A) and in each city there are M buses numbered from 1 to M. And the fare of each bus is different. Means for all N*M busses, fare (K) may be different or same. Now Jack has to go from city A to city B following these conditions:
1. At every city, he has to change the bus.
2. And he can switch to only those buses which have number either equal or 1 less or 1 greater to the previous.
You are to help Jack to go from A to B by spending the minimum amount of money.
N, M, K <= 100.
Input
Line 1:    N M

Line 2:    NxM Grid

Each row lists the fares the M busses to go form the current city to the next city.
Output
Single Line containing the minimum amount of fare that Jack has to give.
Sample Input
5 5
1  3  1  2  6
10 2  5  4  15
10 9  6  7  1
2  7  1  5  3
8  2  6  1  9
Sample Output
10
Explanation:
1 -> 4 -> 1 -> 3 -> 1: 10
This is marked and shown in the following image: 
https://files.codingninjas.in/1716-image-8325.jpg
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		// Write your code here
		Scanner sc = new Scanner(System.in);
 	    int n=sc.nextInt();
        int m=sc.nextInt();
        
        int[][] dp = new int[n][m];
        
        int[][] arr = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                arr[i][j]=sc.nextInt();
            } 
        }
        
        for(int j=0; j<m; j++) {
            dp[0][j]=arr[0][j];
        }
        
        for (int i=1; i<n; i++) {
            for (int j=0; j<m; j++) {
                int a=Integer.MAX_VALUE,b=Integer.MAX_VALUE;
                if(j>0){
                	a =Math.min(dp[i-1][j-1],dp[i-1][j]);
                }
                if(j<m-1){
                    b =Math.min(dp[i-1][j+1],dp[i-1][j]);
                }
                dp[i][j]=arr[i][j]+Math.min(a,b);
            } 
        }
        
        // for (int i=0; i<n; i++) {
        //     for (int j=0; j<m; j++) {
        //         System.out.print(dp[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        
        int min=dp[n-1][0];
        for(int i=1; i<m; i++) {
            if(dp[n-1][i]<min){
                min=dp[n-1][i];
            }
        }
        System.out.println(min);
	}

}
