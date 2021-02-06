// Alice and Bob need to send secret messages to each other and are discussing ways to encode their messages:
// Alice: “Let’s just use a very simple code: We’ll assign ‘A’ the code word 1, ‘B’ will be 2, and so on down to ‘Z’ being assigned 26.”

// Bob: “That’s a stupid code, Alice. Suppose I send you the word ‘BEAN’ encoded as 25114. You could decode that in many different ways!”

// Alice: “Sure you could, but what words would you get? Other than ‘BEAN’, you’d get ‘BEAAD’, ‘YAAD’, ‘YAN’, ‘YKD’ and ‘BEKD’. I think you would be able to figure out the correct decoding. And why would you send me the word ‘BEAN’ anyway?”

// Bob: “OK, maybe that’s a bad example, but I bet you that if you got a string of length 5000 there would be tons of different decodings and with that many you would find at least two different ones that would make sense.”

// Alice: “How many different decodings?”

// Bob: “Jillions!”
// For some reason, Alice is still unconvinced by Bob’s argument, so she requires a program that will determine how many decodings there can be for a given string using her code.
// Input
// Input will consist of multiple input sets. Each set will consist of a single line of at most 5000 digits representing a valid encryption (for example, no line will begin with a 0). There will be no spaces between the digits. An input line of ‘0’ will terminate the input and should not be processed.
// Output
// For each input set, output the number of possible decodings for the input string. Print your answer taking modulo "10^9+7"
// Sample Input:
// 25114
// 1111111111
// 3333333333
// 0
// Sample Output:
// 6
// 89
// 1



import java.util.Scanner;

public class Main {
    public static final int mod = 1000000007;
	
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// Write your code here
		while(in.hasNext()){
			String s = in.next();
            if(s.equals("0")){
				break;
            }
            int[] dp = new int[s.length()+1];
            int[] input  = new int[s.length()];
            for(int i = 0;i<s.length();i++){
				input[i] = Integer.parseInt(s.charAt(i)+"");
                dp[i]=-1;
            }
            dp[s.length()]=-1;
            int ans = decodeCount(input,s.length(),dp);
            System.out.println(ans);
            
         }
	}
    
    public static int decodeCount(int[] input, int n,int[] dp){
        if(n==1){
			return 1;
        }
        if(n==0){
			return 1;
        }
        if(dp[n]>-1){
			return dp[n];
        }
        int output = 0;
        if(input[n-1] != 0){
            output = decodeCount(input,n-1,dp) % mod;
        }
        //output = decodeCount(input,n-1,dp) % mod;
        if(input[n-2]*10+input[n-1] <=26 && input[n-2] != 0){
			output += decodeCount(input,n-2,dp) % mod;
        }
        dp[n] = output % mod;
        return dp[n];
        
    }

}