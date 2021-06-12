/*World is getting more evil and it's getting tougher to get into the Evil League of Evil. Since the legendary Bad Horse has retired, now you have to correctly answer the evil questions of Dr. Horrible, who has a PhD in horribleness (but not in Computer Science). You are given an array of N elements, which are initially all 0. After that you will be given C commands. They are -
0 p q v - you have to add v to all numbers in the range of p to q (inclusive), where p and q are two indexes of the array.

1 p q - output a line containing a single integer which is the sum of all the array elements between p and q (inclusive)
Input
In the first line you'll be given T, number of test cases.

Each test case will start with N (N <= 100 000) and C (C <= 100 000). After that you'll be given C commands in the format as mentioned above. 1 <= p, q <= N and 1 <= v <= 10^7.
Output
Print the answers of the queries.
Input:
1
8 6
0 2 4 26
0 4 8 80
0 4 5 20
1 8 8 
0 5 7 14
1 4 8
Output:
80  
508
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static long query(long[] tree,long[] lazy, int start, int end,int treeNode, int l, int r){
        if(start>end){
            return 0;
        }
        if(lazy[treeNode] !=0){
            long prevChange = lazy[treeNode];
            tree[treeNode] +=  (end-start+1)*prevChange;
            if(start!=end){
                lazy[2*treeNode]+=prevChange;
                lazy[2*treeNode+1]+=prevChange;
            }
            lazy[treeNode] = 0;
        }
        if(start>r || end<l){
            return 0;
        }
        if(start>=l && end<=r){
            return tree[treeNode];
        }else{
            int mid =(start + end)/2;
            long p1 = query(tree, lazy, start, mid, 2*treeNode, l, r);
            long p2 = query(tree, lazy, mid+1, end, 2*treeNode+1, l, r);
            return p1+p2;
        }

    }
   public static void update(long[] tree,long[] lazy, int start, int end,int treeNode, int l, int r, int value){
       if(start>end){
           return;
       }
       if(start>r || end<l){
           return;
       }
       if(start>=l && end<=r){
           tree[treeNode]+=(end-start+1)*(value+lazy[treeNode]);
           if(start!=end){
               lazy[2*treeNode]+=(value+lazy[treeNode]);
               lazy[2*treeNode+1]+=(value+lazy[treeNode]);
           }
           lazy[treeNode]=0;
           return;
       }
       int mid=(start+end)/2;
       update(tree, lazy, start, mid, 2*treeNode, l,r, value);
       update(tree, lazy, mid+1, end,2*treeNode+1, l,r, value);
       tree[treeNode]=tree[2*treeNode]+(mid-start+1)*lazy[2*treeNode] + tree[2*treeNode+1]+(end-mid)*lazy[2*treeNode+1];

   }



    public static void main(String[] args) throws IOException {
        // Write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            long[] tree = new long[4*n];
            long[] lazy = new long[4*n];
            int Q = Integer.parseInt(s[1]);
            while (Q-->0){
                String[] s1 = br.readLine().split(" ");
                int q= Integer.parseInt(s1[0]);
                if(q==0){
                    int l= Integer.parseInt(s1[1]);
                    int r= Integer.parseInt(s1[2]);
                    int x= Integer.parseInt(s1[3]);
                    update(tree, lazy, 0, n-1, 1, l-1, r-1, x);
                }else{
                    int l= Integer.parseInt(s1[1]);
                    int r= Integer.parseInt(s1[2]);
                    System.out.println(query(tree, lazy, 0, n-1, 1, l-1, r-1));

                }
            }
        }
    }
}


