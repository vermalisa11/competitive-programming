/*You are given n activities with their start and finish times. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.
Input
The first line of input contains one integer denoting N.
Next N lines contains two space separated integers denoting the start time and finish time for the ith activity.

Output
Output one integer, the maximum number of activities that can be performed
Constraints
1 ≤ N ≤ 10^6
1 ≤ ai, di ≤ 10^9
Sample Input
6
1 2
3 4
0 6
5 7
8 9
5 9
Sample Output
4*/
import java.util.*;

public class Main {
static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        // Write your code here
        int n = in.nextInt();

        Pair[] arr = new Pair[n];
        for(int i = 0;i<n;i++){
            int a = in.nextInt();
            int b = in.nextInt();

            arr[i] = new Pair(a,b);
        }

        Arrays.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair pair, Pair t1) {
                if(t1.y == pair.y){
                    return pair.x - t1.x;
                }
                return pair.y-t1.y;
            }
        });

        // for(int i = 0;i<n;i++){
        //     System.out.println(arr[i].x+" "+ arr[i].y);
        // }
        int count = 1;
        int prev = arr[0].y;
        for(int i = 1;i<n;i++){
            if(arr[i].x >= prev){
                prev = arr[i].y;
                count++;
            }
        }
        System.out.println(count);


    }

    static class Pair{
        int x;
        int y;

        Pair(int a,int b){
            this.x = a;
            this.y = b;
        }
    }
}
