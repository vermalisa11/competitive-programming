// You are given an array of positive integers as input. Write a code to return the length of the largest such subsequence in which the values are arranged first in strictly ascending order and then in strictly descending order.
// Such a subsequence is known as bitonic subsequence. A purely increasing or purely decreasing subsequence will also be considered as a bitonic sequence with the other part empty.
// Note that the elements in bitonic subsequence need not be consecutive in the given array but the order should remain same.
// Input Format:
// Line 1 : A positive Integer N, i.e., the size of array
// Line 2 : N space-separated integers as elements of the array 
// Output Format:
// Length of Largest Bitonic subsequence
// Input Constraints:
// 1<= N <= 100000
// Sample Input 1:
// 6
// 15 20 20 6 4 2
// Sample Output 1:
// 5
// Sample Output 1 Explanation:
// Here, longest Bitonic subsequence is {15, 20, 6, 4, 2} which has length = 5.
// Sample Input 2:
// 2
// 1 5
// Sample Output 2:
// 2
// Sample Input 3:
// 2
// 5 1
// Sample Output 3:
// 2


public class Solution {
	
	
	
	public static int longestBitonicSubarray(int[] arr){

	/* Your class should be named Solution.
	 * Don't write main() function.
	 * Don't read input, it is passed as function argument.
	 * Return output and don't print it.
	 * Taking input and printing output is handled automatically.
	 */
        int n = arr.length;
        int[] inc = new int[n];
        int[] dec = new int[n];
        int ans=0;
        inc[0]=1;
        dec[n-1]=1;
        for(int i=1;i<n;i++){
            inc[i]=1;
            for( int j= i-1; j>=0; j--){
                if(arr[j]<arr[i]){
                    int pos=inc[j]+1;
                    if(pos>inc[i]){
                        inc[i]=pos;
                    }
                }
            }
        }
        for (int i=n-2; i>=0; i--) {
            dec[i]=1;
            for (int j=i+1; j<n; j++) {
                if(arr[j]<arr[i]){
                    int pos=dec[j]+1;
                    if(pos>dec[i]){
                        dec[i]=pos;
                    }
                }
            }
        }
        

        for(int i=0; i<n;i++) {
            int count = inc[i] + dec[i] - 1;
            if(count >ans) {
                ans= count;
            }
        }
        return ans;

    	
	}
	
}

    
