/*Some of the more elite (and not-so-elite) coders around take part in a certain unnamed programming contest. In said contest, there are multiple types of competitions. Here, we consider the Open and High School competition types. For each type, each competitor receives a rating, an integer between 1 and 100000, inclusive. A coder's rating is based upon his or her level of performance in matches and is calculated using a complicated formula which, thankfully, you will not be asked to implement.
Although the Open and High School ratings for a coder who has participated in both competition types lately are usually close, this is not always the case. In particular, High School matches are more about speed, since many coders are able to solve all the problems, whereas Open matches require more thinking and there is a steeper curve in terms of problem difficulty.
Problem Statement
You are given N coders (1 ≤ N ≤ 300000), conveniently numbered from 1 to N. Each of these coders participates in both High School and Open matches. For each coder, you are also given an Open rating Ai and a High School rating Hi. Coder i is said to be better than coder j if and only if both of coder i's ratings are greater than or equal to coder j's corresponding ratings, with at least one being greater. For each coder i, determine how many coders coder i is better than.
Input Format
On the first line of input is a single integer N, as described above. N lines then follow. Line i+1 contains two space-separated integers, Ai and Hi.
Output Format
 Line i should contain the number of coders that coder i is better than.
Sample Input
8
1798 1832
862 700
1075 1089
1568 1557
2575 1984
1033 950
1656 1649
1014 1473
Sample Output
6
0
2
4
7
1
5
1
*/
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static class Coder{
        int x,y,index;
        public Coder(){
            this.x = 0;
            this.y = 0;
            this.index = -1;
        }
    }
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        // Write your code here
        int n = in.nextInt();

        Coder[] arr = new Coder[n];

        for(int i = 0;i<n;i++){
            arr[i] = new Coder();
            arr[i].x = in.nextInt();
            arr[i].y = in.nextInt();
            arr[i].index = i;
        }
        Arrays.sort(arr, (a, b) -> {
            if (a.x == b.x) {
                return a.y - b.y;
            }
            return a.x - b.x;
        });

        int[] BIT = new int[100001];
        int[] ans = new int[n];
        for(int i = 0;i<n;){

            int end = i;
            while(end<n && arr[i].x == arr[end].x && arr[i].y==arr[end].y){
                end++;
            }

            //query
            for(int j = i;j<end;j++){
                ans[arr[j].index] = query(arr[j].y,BIT);
            }
            //update
            for(int j = i;j<end;j++){
                update(arr[j].y,BIT);
            }
            i = end;
        }
        for(int i = 0;i<n;i++){
            System.out.println(ans[i]);
        }
    }
    static void update(int y,int[] BIT){
        for(;y<=100000;y+= (y & ((-1)*y))){
            BIT[y]++;
        }
    }
    static int query(int y,int[] BIT){
        int count = 0;
        for(;y>0;y-= (y & ((-1)*y))){
            count += BIT[y];
        }
        return count;
    }

}
