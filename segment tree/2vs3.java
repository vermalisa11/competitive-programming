/*The fight for the best number in the globe is going to finally come to an end.The top two contenders for the best number are number 2 and number 3.It's the final the entire world was waiting for. Expectorates from all across the globe came to witness the breath taking finals.
The finals began in an astonishing way.A common problem was set for both of them which included both these number.The problem goes like this.
Given a binary string (that is a string consisting of only 0 and 1). They were supposed to perform two types of query on the string.
Type 0: Given two indices l and r.Print the value of the binary string from l to r modulo 3.

Type 1: Given an index l flip the value of that index if and only if the value at that index is 0.
The problem proved to be a really tough one for both of them.Hours passed by but neither of them could solve the problem.So both of them wants you to solve this problem and then you get the right to choose the best number in the globe.
Input:
The first line contains N denoting the length of the binary string .
The second line contains the N length binary string.Third line contains the integer Q indicating the number of queries to perform.
This is followed up by Q lines where each line contains a query.
Output:
For each query of Type 0 print the value modulo 3.
Constraints:
1<= N <=10^5

1<= Q <= 10^5

0 <= l <= r < N
Sample Input
5
10010
6
0 2 4
0 2 3
1 1
0 0 4
1 1
0 0 3
Sample Output
2
1
2
1
*/

import java.util.Arrays;
import java.util.Scanner;
class Main {
    static Scanner in = new Scanner(System.in);
    static int[] arr = new int[100004];
    static int[] tree = new int[4*100004];
    static int[] power = new int[100001];
    static void buildPower(){
        power[0] = 1;
        for(int i = 1; i < 100001; i++) power[i] = (power[i-1]*2)%3;
    }
    public static void main(String[] args) {
        // Write your code here
        int n = in.nextInt();
        String s = in.next();

        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)=='0'){
                arr[i] = 0;
            }else{
                arr[i] = 1;
            }
        }
        buildPower();
        Arrays.fill(tree,0);
        buildTree(0,n-1,1);
//        for(int i = 1;i<2*n;i++){
//            System.out.println(tree[i]);
//        }


        int q = in.nextInt();
        while(q-->0){
            int ch = in.nextInt();
            switch(ch){
                case 0:{
                    int left = in.nextInt();
                    int right = in.nextInt();
                    int ans = query(0,n-1,1,left,right);
                    System.out.println(ans);
                    System.out.println();
                    break;
                }
                case 1:{
                    int index = in.nextInt();
                    if(arr[index] == 0){
                        update(0,n-1,1,index);
                    }
                    break;
                }
            }
        }
    }
    static void buildTree(int start,int end,int treeNode){
        if(start == end){
            tree[treeNode] = arr[start];
            return;
        }
        int mid = (start+end)/2;
        buildTree(start,mid,2*treeNode);
        buildTree(mid+1,end,2*treeNode+1);

        tree[treeNode] = (tree[2*treeNode] * power[end-mid] + tree[2*treeNode+1] )%3;
    }
    static void update(int start,int end,int treeNode,int index){
        if(start == end){
            arr[start] = 1;
            tree[treeNode] = 1;
            return;
        }
        int mid = (start+end)/2;
        if(index > mid){
            update(mid+1,end,2*treeNode+1,index);
        }else{
            update(start,mid,2*treeNode,index);
        }
        tree[treeNode] = (tree[2*treeNode] * power[end-mid] + tree[treeNode*2 +1])%3;

    }
    static int query(int start,int end,int treeNode,int left,int right){
        if(start>right || end<left){
            return 0;
        }
        if(start >=left && end <= right){
//            System.out.println(tree[treeNode]+" "+ start+" " +end);
            return (tree[treeNode]*power[right-end])%3;
        }
        int mid = (start+end)/2;
        int l = query(start,mid,2*treeNode,left,right);
        int r = query(mid+1,end,2*treeNode+1,left,right);
//        System.out.println(l+" "+r +" "+ start+" "+ end +" "+treeNode);
//        System.out.println(((l * (int)Math.pow(2,end-mid)%3)%3 + r )%3);
        return (l + r)%3;
    }
}
