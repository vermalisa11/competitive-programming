/*Harshit knows by his resources that this time the winning lottery number is the smallest number whose sum of the digits is S and the number of digits is D. You have to help Harshit and print the winning lottery number.
Input Format
The Input line contains two space-separated integers: S,D
Output Format
The output contains a single integer denoting the winning lottery number
Constraints
1 <= D <= 1000
1 <= S <= 9*D
Time Limit: 1 second
Sample Input1:
9 2
Sample Output1:
18
Explanation
There are many other possible numbers like 45, 54, 90, etc with the sum of digits as 9 and number of digits as 2. The smallest of them is 18.*/

import java.util.*;

public class Main {

	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		/* Your class should be named Main.
 			* Read input as specified in the question.
 			* Print output as specified in the question.
		*/

		// Write your code here
        int s = in.nextInt();
        int d = in.nextInt();
        
        String str = "1";
        int nines = s/9;
        int remSum = s%9;
        remSum--;
        
        for(int i = 1;i<(d-nines)-1;i++){
            str+="0";
        }
        str+=remSum+"";
        for(int i = 0;i<nines;i++){
            str+="9";
        }
        System.out.println(str);

	}

}
