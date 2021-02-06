// Given a 2D array, find the maximum sum rectangle in it. In other words find maximum sum over all rectangles in the matrix.
// Input
// First line contains 2 numbers n and m denoting number of rows and number of columns. Next n lines contain m space separated integers denoting elements of matrix nxm.
// Output
// Output a single integer, maximum sum rectangle.
// Constraints
// 1<=n,m<=100
// Sample Input
// 4 5
// 1 2 -1 -4 -20
// -8 -3 4 2 1
// 3 8 10 1 3
// -4 -1 1 7 -6
// Sample Output
// 29


import java.util.*;

public class Main {

	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// Write your code here
		int n = in.nextInt(),m = in.nextInt();
        int[][] arr =new int[n][m];
        for(int i = 0;i<n;i++){
			for(int j = 0;j<m;j++){
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(maximumSumRectangle(arr,n,m));
	}
    public static int maximumSumRectangle(int[][] arr,int n,int m){
        int maxArea = Integer.MIN_VALUE;
        
        for(int i=0;i<m;i++){
            int[] dp = new int[n];
        	Arrays.fill(dp,0);
            for(int j=i;j<m;j++){
                sum(dp,arr,j,n);
            	int currSum = maxSubArraySum(dp);
                if(maxArea<currSum){
					maxArea = currSum;
                }
            }
        }
        return maxArea;
    }
    static void sum(int[] a,int[][] arr,int j,int rows){
        for(int i = 0;i<rows;i++){
			a[i] += arr[i][j];
        }
    }
    static int maxSubArraySum(int a[]) { 
        int size = a.length; 
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0; 
  
        for (int i = 0; i < size; i++) { 
            max_ending_here = max_ending_here + a[i]; 
            if (max_so_far < max_ending_here) 
                max_so_far = max_ending_here; 
            if (max_ending_here < 0) 
                max_ending_here = 0; 
        } 
        return max_so_far; 
    } 

}