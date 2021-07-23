/*Given a number N, find number of primes in the range [1,N].
Input:
The only line of input consists of a number N
Output:
Print the number of primes in the range [1,N].`
Constraints:
1≤N≤1000000
Sample Input :
3 
Sample Output -
2
*/
import java.util.Scanner;

public class Main {

	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// Write your code here
        int n = in.nextInt();
        int count = 0;
        if(n==1){
            System.out.println(count);
            return;
        }
        for(int i = 2;i<=n;i++){
            if(isPrime(i)){
                count++;
            }
        }
        System.out.println(count);

	}
    static boolean isPrime(int n){
        for(int i = 2;i<=Math.sqrt(n);i++){
			if(n%i==0){
                return false;
            }
        }
        return true;
    }

}
