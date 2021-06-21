/*Aahad gives an array of integers and asks Harshit to find which three elements form a triangle (non-degenerate). The task seems easy to Harshit.
So, Aahad adds some conditions to this task -
1. Find the triangle with maximum perimeter
2. If there are two or more combinations with same value of maximum perimeter, then find the one with the longest side.
3.If there are more than one combinations which satisfy all the above conditions the find with maximum longest minimum side.
Input Format
The First line contains no of elements of array: N
Each T lines contains N space-separated integers: A [i]
Output Format
The output contains three space-separated elements that denote the length of the sides of triangle. If no such triangle is possible, then print -1.
Constraints
1 =< N <= 10^5 
1 <= A[i] <= 10^9
Time Limit: 1 second
Sample Input1:
5
1 1 1 3 3
Sample Output1:
1 3 3 
Sample Input2:
3
2 2 4
Sample Output3:
-1 
Explaination
In the First Sample case, the elements that form a triangle with maximum perimeter is 1,3,3.
In the Second Sample case, the elements that can form a triangle are degenerate, so, we printed -1.*/

import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        /* Your class should be named Main.
         * Read input as specified in the question.
         * Print output as specified in the question.
         */
        
        // Write your code here
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i =0;i<n;i++){
			arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        boolean flag=false;
        for(int i = n-1 ;i>=2;i--){
			if(isTriangle(arr[i],arr[i-1],arr[i-2])){
                System.out.println(arr[i-2]+" "+arr[i-1]+" "+ arr[i]);
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println(-1);
        }
    }
    
    static boolean isTriangle(int a,int b,int c){
        int l1 = a+b;
        int l2 = c+b;
        int l3 = a+c;
        
        if( l1 > c && l2 > a && l3 > b){
          	return true;   
        }
        return false;
    }
    
}
