/*Inversion Count
Send Feedback
For a given integer array/list of size N, find the total number of 'Inversions' that may exist.
An inversion is defined for a pair of integers in the array/list when the following two conditions are met.
A pair (arr[i], arr[j]) is said to be an inversion when,
1. arr[i] > arr[j] 
2. i < j

Where 'arr' is the input array/list, 'i' and 'j' denote the indices ranging from [0, N).
Input format :
The first line of input contains an integer N, denoting the size of the array.

The second line of input contains N integers separated by a single space, denoting the elements of the array.
Output format :
The only line of output prints the total count of inversions in the array.
Note:
You are not required to print anything. Return the total number of inversion count.
Constraints :
1 <= N <= 10^5 
1 <= arr[i] <= 10^9

Time Limit: 1sec
Sample Input 1 :
3
3 2 1
Sample Output 1 :
3
Explanation of Sample Input 1:
We have a total of three pairs which satisfy the condition of inversion. (3, 2), (2, 1) and (3, 1).
Sample Input 2 :
5
2 5 1 3 4
Sample Output 2 :
4
*/


public class Solution {

    public static long getInversions(long arr[]) {
        //Your code goes here
        return mergeSort(arr,0,arr.length);
        
    }
    public static long mergeSort(long[] a, int start, int end) {
        if(end-start>=2){
            int mid=(start +end)/2;
            long l= mergeSort(a, start, mid);
            long r = mergeSort(a, mid , end);
            long k = merge(a,start,mid,end);
            return l+r+k;
        }
        return 0;
    }
    //35 20 -15 55 7 1 -22
    //55 35 20 7 1 -15
    public static long merge(long[] a, int start, int mid, int end) {
       if(a[mid-1]<=a[mid]){
           return 0;
       }
        long count=0;
        int i = start;
        int j = mid;
        int tempIndex=0;
        long[] temp = new long[end - start];
        while(i<mid && j<end){
            if(a[i]<=a[j]){
                temp[tempIndex++]=a[i++];
            }else{
                temp[tempIndex++]=a[j++];
                count+=mid-i;
            }
            //temp[tempIndex++] = a[i]<=a[j] ? a[i++] : a[j++];
        }
        System.arraycopy(a, i ,a , start+tempIndex, mid-i );
        System.arraycopy(temp, 0, a, start, tempIndex);
        return count;
    }
}
