/*Gary is a teacher at XYZ school. To reward his N students he bought a packet of N candies all with different flavours. But the problem is some students like certain flavour while some doesn't. Now Gary wants to know the number of ways he can distribute these N candies to his N students such that every student gets exactly one candy he likes.
Input Format :
Line 1 : An integer N (1<= N <= 16) denoting number of students and candies.
Next N lines : N integers describing the preferences of one student. 1 at i'th (0 <= i < N) position denotes that this student likes i'th candy , 0 means he doesn't.
Assume input to be 0-indexed based.
Output Format :
Return the number of ways Gary can distribute these N candies to his N students such that every student gets exactly one candy he likes.``
Sample Input:
3
1 1 1
1 1 1
1 1 1
Sample Output:
6
*/
import java.util.*;

public class solution {
	
	static long solve(int[][] like,int N){
		
		//Write your code here.
        int[] dp = new int[1<<N];
        Arrays.fill(dp,0);
        
        int ans = candies(like,N,0,0,dp);        
        return ans;
		
	}
    static int candies(int[][] like,int n,int person,int mask,int[] dp){
        if(person>=n){
            return 1;
        }
        
        if(dp[mask] != 0){
            return dp[mask];
        }
        int ans = 0;
        for(int i = 0;i<n;i++){
            if (!((mask & (1 << i))!=0) && like[person][i]==1){
                ans += candies(like,n,person+1,(mask|(1<<i)),dp);
            }
        }
        dp[mask] = ans;
        return ans;
    }
	
}
