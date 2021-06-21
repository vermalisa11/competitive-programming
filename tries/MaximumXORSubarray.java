/*Given an array of n integers, find subarray whose xor is maximum.
Input Format
First line contains single integer n (1<=n<=1000000).
Next line contains n space separated integers.
Output Format
Print xor of the subarray whose xor of all elements in subarray is maximum over all subarrays.
Constraints
1 <= n <= 1000000
1 <= A[i] <=100000 
Sample Input
4
1 2 3 4
Sample Output
7
*/
import java.util.*;

public class Main {
    static class TrieNode{
        int count;
        TrieNode[] children = new TrieNode[2];
        
        public TrieNode(){
            this.count = 0;
            this.children[0] = null;
            this.children[1] = null;
        }
    }
	
    static void fill_trie(int n , TrieNode root){
        TrieNode node = root;
        TrieNode newNode;
        
        for(int i = 31;i>=0;i--){
            int curr_bit = (n>>i)&1;
            if(node.children[curr_bit] == null){
                newNode = new TrieNode();
                newNode.count = 1;
                node.children[curr_bit] = newNode;
            }else{
                node.children[curr_bit].count++;
            }
            node = node.children[curr_bit];
        }
    }
    static int query(int n,TrieNode root){
        int ans = 0;
        TrieNode node = root;
        if(node == null){
            return 0;
        }
        
        for(int i = 31;i>=0;i--){
            int curr_bit = (n>>i)&1;
            
            if(node.children[1-curr_bit] != null){
                node = node.children[1-curr_bit];
                ans = ans*2+1;
            }else{
                node = node.children[curr_bit];
                ans = ans * 2;
            }
        }
        return ans;
    }
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// Write your code here
        int n = in.nextInt();
        // int[] arr = new int[n];
        
        int xor_till_i = 0;
        TrieNode root = new TrieNode();
        fill_trie(xor_till_i,root);
        int ans=  0;
        for(int i = 0;i<n;i++){
            // arr[i] = in.nextInt();
            int val = in.nextInt();
            xor_till_i ^= val;
            ans = Math.max(ans, query(xor_till_i,root));
            fill_trie(xor_till_i,root);
        }
        
        System.out.println(ans);
        

	}
}
