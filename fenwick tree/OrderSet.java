/*In this problem, you have to maintain a dynamic set of numbers which support the two fundamental operations
INSERT(S,x): if x is not in S, insert x into S
DELETE(S,x): if x is in S, delete x from S
and the two type of queries
K-TH(S) : return the k-th smallest element of S
COUNT(S,x): return the number of elements of S smaller than x
Input Format
Line 1: Q (1 ≤ Q ≤ 200000), the number of operations
In the next Q lines, the first token of each line is a character I, D, K or C meaning that the corresponding operation is INSERT, DELETE, K-TH or COUNT, respectively, following by a whitespace and an integer which is the parameter for that operation.

If the parameter is a value x, it is guaranteed that ≤ |x| ≤ 10^9. If the parameter is an index k, it is guaranteed that 1 ≤ k ≤ 10^9.`
Output
For each query, print the corresponding result in a single line. In particular, for the queries K-TH, if k is larger than the number of elements in S, print the word 'invalid'.
Input Format
8
I -1
I -1
I 2
C 0
K 2
D -1
K 1
K 2
Output Format
1
2
2
invalid
*/


import java.util.*;

class Main {
    static int MAX = 200005;
    static class Pair{
        int x,y;
        public Pair(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    static Pair[] M = new Pair[MAX];
    static Pair[] T = new Pair[MAX];
    static int[] BIT = new int[MAX];
    static int[] A = new int[MAX];
    static int n;
    static char[] C = new char[MAX];
    static boolean b;

    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int k = 0;
        int q = in.nextInt();
        for(int i= 0;i<q;i++){
            C[i] = in.next().charAt(0);
            A[i] = in.nextInt();
            if(C[i] == 'I'){
                T[k++] = new Pair(A[i],0);
            }
        }
        Arrays.sort(T,0,k, (pair, t1) -> {
            if (pair.x == t1.x) {
                return pair.y - t1.y;
            }
            return pair.x - t1.x;
        });

        M[0] = T[0];
        n = 1;
        for(int i = 1;i<k;i++){
            if(T[i].x != T[i-1].x){
                M[n++] = T[i];

            }
        }
        for (int i = 0; i < q; i++)
        {
            int x;
            if (C[i] == 'I'){

                x = binarySearch(A[i]);
                if (M[x].y == 0) {
                    M[x].y = 1;
                    updateBIT(x + 1, 1);
                }
            } else if (C[i] == 'D') {
                x = binarySearch(A[i]);
                if (x != -1 && M[x].y == 1 && b) {
                    updateBIT(x + 1, -1);
                    M[x].y = 0;
                }
            } else if (C[i] == 'C') {
                x = binarySearch(A[i]);
                if (b)
                    System.out.println(getCount(x+1-1));
                else if (x != -1)
                    System.out.println(getCount(x+1));
                else
                    System.out.println(0);
            }
            else if (C[i] == 'K') {
                k = A[i];
                x = -1;
                boolean mno = false;
                int lo = 1;
                int hi = MAX - 1;
                while (lo <= hi)
                {
                    int mid = (lo + hi) >> 1;
                    if (getCount(mid) == k && getCount(mid - 1) != k)
                    {
                        mno = true;
                        x = mid;
                        break;
                    }
                    else if (getCount(mid) < k)
                    {
                        x = mid;
                        lo = mid + 1;
                    }
                    else
                        hi = mid - 1;
                }
                if (!mno)
                    System.out.println("invalid");
                else
                    System.out.println(M[x-1].x);
            }
        }
    }
    static int binarySearch(int val){
        int start = 0,end = n-1,mid,ans = -1;
        while (start<=end){

            mid = (start+end)/2;

            if(M[mid].x == val){
                b = true;
                return mid;
            }else if(M[mid].x > val){
                end = mid - 1;
            }else{
                ans = mid;
                start = mid+1;
            }
        }
        b = false;
        return ans;
    }
    static void updateBIT(int index,int value){
        for(;index<MAX;index += (index & ((-1)*index))){
            BIT[index] += value;
        }
    }
    static int getCount(int index){
        int count = 0;
        for(;index>0;index -= (index & ((-1)*index))){
            count += BIT[index];
        }
        return count;
    }

}
