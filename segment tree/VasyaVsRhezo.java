/*Queen Vasya is preparing for a war against Rhezo. She has N warriors in total arranged in a line. Let us number the warriors by numbers from 1 to N. She will fight Rhezo's army for Q days, and each day she can choose only one warrior.
For each warrior, we know 2 values associated with him, let us call these A and B. Each day Vasya can choose her warrior from a range Li to Ri, she must choose the warrior with maximum A value. If there is more than 1 warrior having the same maximum A value, she chooses the warrior with minimum B value. If still there is more than 1 warrior with same maximum A value and same minimum B value, she chooses the one with lower index in line.
You being the hand of Queen Vasya, need to help her in choosing the warrior for each day.
Input:
First line contains a single integer N, denoting the number of warriors Queen Vasya has. 
Second line contains N space separated integers Ai. Third line contains N space separated integers Bi.
Next line contains a single integer Q, denoting the number of days Queen Vasya chooses a warrior. 
Each of the next Q lines contains 2 integers Li and Ri.
Output:
For each Li and Ri, print the index of the warrior that Queen Vasya should choose.
Constraints:
1≤ N,Q ≤10^6
1≤ Ai,Bi ≤10^9
1≤Li≤Ri
Sample Input
5
1 8 4 6 8
4 8 6 3 7
4
1 4
2 4
3 4
1 5
Sample Output
2
2
4
5
*/

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static pair[] arr=new pair[1000004];
    static pair[] tree = new pair[4*1000004];
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        // Write your code here
        int n = in.nextInt();
        int[] a= new int[n];
        int[] b = new int[n];
        for(int i=0;i<n;i++){
            a[i] = in.nextInt();
        }
        for(int i = 0;i<n;i++){
            b[i] = in.nextInt();
        }
        for(int i = 0;i<n;i++){
            arr[i] = new pair(a[i],b[i],i);
        }
        Arrays.fill(tree,new pair(0,0,0));
        buildTree(0,n-1,1);
//        for(int i = 0;i<4*n;i++){
//            System.out.println(tree[i].x+" "+tree[i].y+" "+tree[i].z);
//        }

        int q = in.nextInt();
        while(q-->0){
            int l = in.nextInt();
            int r = in.nextInt();

            pair ans = query(0,n-1,1,l-1,r-1);
            System.out.println(ans.z+1);
        }


    }
    static class pair{
        int x,y,z;
        pair(int x,int y,int z){
            this.x = x;
            this.y = y;
            this.z = z;
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

        pair left = tree[2*treeNode];
        pair right = tree[2*treeNode+1];

//        if(left.x>=right.x){
//            tree[treeNode] = left;
//        }else{
//            tree[treeNode] = right;
//        }

        if(left.x > right.x){
            tree[treeNode] = left;
        }else if(left.x == right.x){
            if(left.y<right.y){
                tree[treeNode] = left;
            }else if(left.y==right.y){
                tree[treeNode] = left;
            }else{
                tree[treeNode] = right;
            }
        }else{
            tree[treeNode] = right;
        }
    }

    static pair query(int start,int end,int treeNode,int left,int right){
        if(start > right || end < left){
            return new pair(Integer.MIN_VALUE,Integer.MAX_VALUE,-1);
        }
        if(start>=left && end <= right){
//            System.out.println(start+" "+end+" "+treeNode);
            return tree[treeNode];
        }

        int mid  = (start+end)/2;
        pair l = query(start,mid,2*treeNode,left,right);
        pair r = query(mid+1,end,2*treeNode+1,left,right);

        if(l.x > r.x){
            return l;
        }else if(l.x == r.x){
            if(l.y <= r.y){
                return l;
            }else{
                return r;
            }
        }else{
            return r;
        }

    }

}
