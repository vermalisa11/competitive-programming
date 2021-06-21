/*
Given two strings S and T, write a function to find if T is present as a substring inside S or not. If yes, return the starting index otherwise return -1.
Input format :

Line 1 : String S

Line 2 : String T

Sample Input 1:
WelcomeBack
come 
Sample Output 1:
3
Sample Input 2:
WelcomeBack
code
Sample Output 2:
-1
*/
public class Solution {
	public static int findString(String text, String pattern) {
		int n = text.length();
        int m = pattern.length();

        int[] lps = getLps(pattern);

        int i = 0;
        int j = 0;
        int ans = 0;
        while(i<n && j<m){
            if(text.charAt(i) == pattern.charAt(j)){
                ans = i-j;
                i++;
                j++;
            }else {
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
        if(j == m){
            return ans;
        }
        return -1;

	}
    static int[] getLps(String s){
        int n = s.length();
        int[] lps = new int[n];
        char[] arr = s.toCharArray();

        lps[0] = 0;
        int i = 1;
        int j = 0;

        while (i<n){
            if(arr[i] == arr[j]){
                lps[i] = j+1;
                j++;
                i++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
