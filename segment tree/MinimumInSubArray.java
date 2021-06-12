/*You are given a sequence A[1], A[2], ..., A[N] . ( |A[i]| ≤ 15007 , 1 ≤ N ≤ 50000 ). A query is defined as follows:
Query(x,y) = Max { a[i]+a[i+1]+...+a[j] ; x ≤ i ≤ j ≤ y }.
Given M queries, your program must output the results of these queries.
Input
The first line of the input file contains the integer N.
In the second line, N numbers follow.
The third line contains the integer M.
M lines follow, where line i contains 2 numbers xi and yi.
Output
Your program should output the results of the M queries, one 
query per line.
Sample Input:
3 
-1 2 3 
1
1 2
Sample Output:
2
*/

import java.io.*;
import java.util.Scanner;

class Main {
    static Scanner in = new Scanner(System.in);
    static long[] arr = new long[50004];
    static Node[] tree = new Node[4*50004];
    static long min = Integer.MIN_VALUE ;

    public static void main(String[] args) throws IOException{

        int n = in.nextInt();

        for(int i = 0;i<n;i++){
            arr[i] = in.nextInt();
        
        }
        for(int i = 0;i<tree.length;i++){
            tree[i] = new Node(min,min,min,min);
    	}
        buildTree(0,n-1,1);
       
        int q = in.nextInt();
        while (q-->0){

            int x = in.nextInt();
            int y = in.nextInt();
            Node ans =  query(0,n-1,1,x-1,y-1);
            System.out.println(ans.maxSum);
        }
    }
    static class Node{
        long maxSum,sum,bps,bss;
        public Node(long maxSum, long sum, long bps, long bss) {
            this.maxSum = maxSum;
            this.sum = sum;
            this.bps = bps;
            this.bss = bss;
        }
    }
    static void buildTree(int start,int end,int treeNode){
        if(start == end){
            tree[treeNode] = new Node(arr[start],arr[start],arr[start],arr[start]);
            return;
        }
        int mid = (start+end)/2;
        buildTree(start,mid,2*treeNode);
        buildTree(mid+1,end,2*treeNode+1);

        Node left = tree[2*treeNode];
        Node right = tree[2*treeNode+1];
        long mSum = Math.max(Math.max(left.maxSum,right.maxSum),Math.max(Math.max(left.sum+right.bps, right.sum+left.bss),right.bps+left.bss));
        long Sum = left.sum+right.sum;
        long BPS = Math.max(left.bps,left.sum+right.bps);
        long BSS = Math.max(right.bss,left.bss+right.sum);
        tree[treeNode] = new Node(mSum,Sum,BPS,BSS);
        return;
    }
    static Node query(int start,int end,int treeNode, int l,int r) {
        if (start > r || end < l) {
            return new Node(min,min,min,min);
        }
        if (start >= l && end <= r) {
            return tree[treeNode];
        }
        int mid = (start + end) / 2;
        Node left = query(start, mid, 2 * treeNode, l, r);
        Node right = query(mid + 1, end, 2 * treeNode + 1, l, r);

        long mSum = Math.max(Math.max(left.maxSum, right.maxSum), Math.max(Math.max(left.sum + right.bps, right.sum + left.bss), right.bps + left.bss));
        long Sum = left.sum + right.sum;
        long BPS = Math.max(left.bps, left.sum + right.bps);
        long BSS = Math.max(right.bss, left.bss + right.sum);
        return new Node(mSum, Sum, BPS, BSS);
    }
} 
