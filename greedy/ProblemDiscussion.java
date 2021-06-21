/*Harshit gave Aahad an array of size N and asked to minimize the difference between the maximum value and minimum value by modifying the array under the condition that each array element either increase or decrease by k(only once).
It seems difficult for Aahad so he asked for your help
Input Format
The First line contains two space-separated integers: N,K
Next lines contain N space-separated integers denoting elements of the array
Output Format
The output contains a single integer denoting the minimum difference between maximum value and the minimum value in the array
Constraints
1 =< N <= 10^5 
1 <= Ai,K <= 10^9
Sample Input1:
3 6
1 15 10
Sample Output1:
5
Explaination
We change from 1 to 6, 15 to  9 and 10 to 4. Maximum difference is 5 (between 4 and 9). We can't get a lower difference.*/

import java.util.*;

public class Main {

	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// Write your code here
		int n = in.nextInt();
        long k = in.nextInt();
        
        long[] arr = new long[n];
        for(int i = 0;i<n;i++){
            arr[i]=in.nextLong();
        }
		long[] a1 = new long[n];
        long[] a2 = new long[n];
        for(int i = 0;i<n;i++){
            a1[i] = arr[i]+k;
            a2[i] = arr[i]-k;
        }
        Arrays.sort(a1);
        Arrays.sort(a2);
        long ans1 = a1[n-1]-a2[0];
        long ans2 = a2[n-1]-a2[0];
        long ans3 = a2[n-1]-a1[0];
        
        long a = Math.min(ans3,Math.min(ans1,ans1));
        
        System.out.println(a);
	}

}
