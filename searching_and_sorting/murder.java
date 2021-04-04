/*Murder
Send Feedback
Once detective Saikat was solving a murder case. While going to the crime scene he took the stairs and saw that a number is written on every stair. He found it suspicious and decides to remember all the numbers that he has seen till now. While remembering the numbers he found that he can find some pattern in those numbers. So he decides that for each number on the stairs he will note down the sum of all the numbers previously seen on the stairs which are smaller than the present number. Calculate the sum of all the numbers written on his notes diary.
Answer may not fit in integer . You have to take long.
Input
First line gives T, number of test cases.

2T lines follow.

First line gives you the number of stairs N

Next line gives you N numbers written on the stairs.
Output
For each test case output one line giving the final sum for each test case.
Constraints
T<=10

1<=N<=10^5

All numbers will be between 0 and 10^6.
Sample Input:
1
5
1 5 3 6 4
Sample Output:
15
Explanation:
For the first number, the contribution to the sum is 0.
For the second number, the contribution to the sum is 1.
For the third number, the contribution to the sum is 1.
For the fourth number, the contribution to the sum is 9 (1+5+3).
For the fifth number, the contribution to the sum is 4 (1+3).
Hence the sum is 15 (0+1+1+9+4).*/
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n ; i++) {
            arr[i]=sc.nextLong();
        }
        System.out.println(mergeSort(arr,0,n));
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
        long count=0;
        int i = start;
        int j = mid;
        int tempIndex=0;
        long[] temp = new long[end - start];
        while(i<mid && j<end){
            if(a[i]<a[j]){
                count+=(a[i]*(end-j));
                temp[tempIndex++]=a[i++];
            }else{
                temp[tempIndex++]=a[j++];                
            }
            //temp[tempIndex++] = a[i]<=a[j] ? a[i++] : a[j++];
        }
        System.arraycopy(a, i ,a , start+tempIndex, mid-i );
        System.arraycopy(temp, 0, a, start, tempIndex);
        return count;
    }
}

