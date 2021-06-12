/*Weighted Job Scheduling
Send Feedback
You are given N jobs where every job is represented as:
1.Start Time
2.Finish Time
3.Profit Associated
Find the maximum profit subset of jobs such that no two jobs in the subset overlap.
Input
The first line of input contains one integer denoting N.
Next N lines contains three space separated integers denoting the start time, finish time and the profit associated with the ith job. 
Output
Output one integer, the maximum profit that can be achieved.
Constraints
1 ≤ N ≤ 10^6
1 ≤ ai, di, p ≤ 10^6
Sample Input
4
3 10 20
1 2 50
6 19 100
2 100 200
Sample Output
250*/

import java.util.*;
import java.io.*;

public class Main {
static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        for(int i = 0;i<n;i++){
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]);

            arr[i] = new Pair(a,b,c);
        }

        Arrays.sort(arr,new JobComparator());

        int[] dp = new int[n];
        dp[0] = arr[0].z;
        for(int i = 1;i<n;i++){
            int include = arr[i].z;
            int lastInc = binarySearch(arr,i);
			
            if(lastInc != -1){
                include += dp[lastInc];
            }
            dp[i] = Math.max(include,dp[i-1]);
        }
        System.out.println(dp[n-1]);


    }
    static class JobComparator implements Comparator<Pair> { 
		public int compare(Pair a, Pair b) { 
        	return a.y < b.y ? -1 : a.y == b.y ? 0 : 1; 
    	} 
	}
    static public int binarySearch(Pair[] jobs, int index) { 
        int lo = 0, hi = index - 1; 
        while (lo <= hi) { 
            int mid = (lo + hi) / 2; 
            if (jobs[mid].y <= jobs[index].x) { 
                if (jobs[mid + 1].y <= jobs[index].x) 
                    lo = mid + 1; 
                else
                    return mid; 
            } 
            else
                hi = mid - 1; 
        } 
        return -1; 
    }

    static class Pair{
        int x;
        int y;
        int z;

        Pair(int a,int b,int c){
            this.x = a;
            this.y = b;
            this.z = c;
        }
    }
}
