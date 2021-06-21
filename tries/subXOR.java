/*A straightforward question. Given an array of positive integers you have to print the number of subarrays whose XOR is less than K. Subarrays are defined as a sequence of continuous elements Ai, Ai+1, ..., Aj . XOR of a subarray is defined as Ai ^ Ai+1 ^ ... ^ Aj. Symbol ^ is Exclusive Or.
Input Format
First line contains T, the number of test cases. 
Each of the test case consists of N and K in one line, followed by N space separated integers in next line.
Output Format
For each test case, print the required answer.
Constraints:
1 ≤ T ≤ 5
1 ≤ N ≤ 10^5
1 ≤ A[i] ≤ 10^5
1 ≤ K ≤ 10^6
Sample Input
1
5 2
4 1 3 2 7   
Sample Output
3
*/
import java.util.*;

public class Main {
    static class TrieNode{
        int left,right;
        TrieNode[] children = new TrieNode[2];
        
        public TrieNode(){
            this.left = 0;
            this.right = 0;
            this.children[0] = null;
            this.children[1] = null;
        }
    }
	
    static void insert(TrieNode root,int n){
        TrieNode node = root;
        TrieNode newNode;
        
        for(int i = 31;i>=0;i--){
            int curr_bit = (n>>i)&1;
            if(curr_bit == 1){
                node.right++;
            }else{
                node.left++;
            }
            if(node.children[curr_bit] == null){
				node.children[curr_bit] = new TrieNode();
            }
            node = node.children[curr_bit];
        }
    }
    static long query(TrieNode root,int val,int k){
        TrieNode node = root;
        
        if(node == null){
			return 0;
        }
        long ans = 0;
        
        for(int i = 31;i>=0;i--){
            int currBitOfK = (k>>i)&1;
            int currBitOfVal = (val>>i)&1;
            if(currBitOfK == 1){
                if(currBitOfVal == 1){
                    ans += node.right;
                    if(node.children[1-currBitOfVal] == null){
                        return ans;
                    }
                    node = node.children[1- currBitOfVal];  
                }else{
                    ans+= node.left;
                    if(node.children[1-currBitOfVal] == null){
                        return ans;
                    }
                    node = node.children[1-currBitOfVal];
                }
            }else{
                if(currBitOfVal == 1){
                    // ans += node.right;
                    if(node.children[currBitOfVal] == null){
                        return ans;
                    }
                    node = node.children[currBitOfVal];  
                }else{
                    // ans+= node.left;
                    if(node.children[currBitOfVal] == null){
                        return ans;
                    }
                    node = node.children[currBitOfVal];
                }
            }
        }
        return ans;
       
    }
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
        
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            int k = in.nextInt();
			
            int temp1,temp2 = 0;
            TrieNode root = new TrieNode();
            
            insert(root,0);
            long ans = 0;
            
            for(int i = 0;i<n;i++){
                int val = in.nextInt();
                temp1 = temp2 ^ val;
                
                ans += (query(root,temp1,k));
                insert(root,temp1);
                
                temp2 = temp1;
            }
            System.out.println(ans);
            
        }
	}
}
