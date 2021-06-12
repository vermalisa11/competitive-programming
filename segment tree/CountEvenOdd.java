/*Ashu and Shanu are best buddies. One day Shanu gives Ashu a problem to test his intelligence.He gives him an array of N natural numbers and asks him to solve the following queries:-
Query 0 :- modify the element present at index i to x.
Query 1:- count the number of even numbers in range l to r inclusive.
Query 2:- count the number of odd numbers in range l to r inclusive.
Input:
First line of the input contains the number N. Next line contains N natural numbers. 
Next line contains an integer Q followed by Q queries.

0 x y - modify the number at index x to y. 

1 x y - count the number of even numbers in range l to r inclusive.

2 x y - count the number of odd numbers in range l to r inclusive.
Constraints:
1<=N,Q<=10^5

1<=l<=r<=N 

0<=Ai<=10^9

1<=x<=N

0<=y<=10^9
Note:-
indexing starts from 1.
Sample Input
6
1 2 3 4 5 6
4
1 2 5
2 1 4
0 5 4
1 1 6
Sample Output
2
2
4
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static int[] arr = new int[100004];
    static Node[] tree = new Node[4*100004];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        for(int i = 0;i<n;i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        for(int i = 0;i<tree.length;i++){
            tree[i] = new Node(0,0);
        }
        buildTree(0,n-1,1);
        int q = Integer.parseInt(br.readLine());
        while (q-->0){
            String[] str = br.readLine().split(" ");
            int ch = Integer.parseInt(str[0]);
            int x = Integer.parseInt(str[1]);
            int y = Integer.parseInt(str[2]);
            switch (ch){
                case 0:{
                    update(0,n-1,1,x-1,y);
                    break;
                }
                case 1:{
                    Node ans = count(0,n-1,1,x-1,y-1);
                    System.out.println(ans.even);
                    break;
                }
                case 2:{
                    Node ans = count(0,n-1,1,x-1,y-1);
                    System.out.println(ans.odd);
                    break;
                }
            }
        }
    }
    static class Node{
        int even,odd;
        public Node(int even, int odd) {
            this.even = even;
            this.odd = odd;
        }
    }
    static void buildTree(int start, int end,int treeNode){
        if(start==end){
            if(arr[start]%2==0){
                tree[treeNode] = new Node(1,0);
            }else{
                tree[treeNode] = new Node(0,1);
            }
            return;
        }
        int mid = (start+end)/2;
        buildTree(start,mid,2*treeNode);
        buildTree(mid+1,end,2*treeNode+1);

        Node left = tree[treeNode*2];
        Node right = tree[2*treeNode+1];

        tree[treeNode] = new Node(left.even+right.even,left.odd+right.odd);
        return;
    }
    static void update(int start,int end,int treeNode,int index,int value){
        if(start == end){
            arr[index] = value;
            if(value%2==0){
                tree[treeNode] = new Node(1,0);
            }else{
                tree[treeNode] = new Node(0,1);
            }
            return;
        }
        int mid = (start+end)/2;
        if(index>mid){
            update(mid+1,end,2*treeNode+1,index,value);
        }else{
            update(start,mid,2*treeNode,index,value);
        }
        Node left = tree[treeNode*2];
        Node right = tree[2*treeNode+1];
        tree[treeNode] = new Node(left.even+right.even,left.odd+right.odd);
        return;
    }
    static Node count(int start,int end,int treeNode,int left, int right){
        if(start>right || end <left){
            return new Node(0,0);
        }
        if(start>=left && end<=right){
            return tree[treeNode];
        }
        int mid = (start+end)/2;
        Node ans1 = count(start,mid,2*treeNode,left,right);
        Node ans2 = count(mid+1,end,2*treeNode+1,left,right);

        return new Node(ans1.even+ans2.even,ans1.odd+ans2.odd);
    }
}
