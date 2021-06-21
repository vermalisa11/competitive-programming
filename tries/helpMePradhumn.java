/*Pradyumn is tired of using auto - correct feature in his smartphone. He needs to correct his auto - correct more times than the auto - correct corrects him. Pradyumn is thinking to make his own app for mobile which will restrict auto - correct feature, instead it will provide auto - completion. Whenever Pradyumn types factorial, auto - correct changes it to fact. Pradyumn's App will show options such as fact, factorial, factory. Now, he can chose from any of these options. As Pradyumn is busy with his front - end work of his App. He asks you to help him. He said "You will be given set of words(words may repeat too but counted as one only). Now, as user starts the app, he will make queries(in lowercase letters only). So, you have to print all the words of dictionary which have the prefix same as given by user as input. And if no such words are available, add this word to your dictionary." As you know, Pradyumn want this app to be as smart as him :P So, implement a program for him such that he can release it on Fun Store.
INPUT CONSTRAINTS
1≤N≤30000
sum(len(string[i]))≤2∗10^5
1≤Q≤10^3
INPUT FORMAT
Single integer N which denotes the size of words which are input as dictionary
N lines of input, where each line is a string of consisting lowercase letter
Single integer Q which denotes the number of queries.
Q number of lines describing the query string on each line given by user
OUTPUT FORMAT
If auto - completions exists, output all of them in lexicographical order else output "No suggestions" without quotes

NOTE: All strings are lowercase
SAMPLE INPUT
3
fact
factorial
factory
2
fact
pradyumn
SAMPLE OUTPUT
fact
factorial
factory
No suggestions
*/
import java.util.Scanner;

public class Main {

	static class TrieNode{
        boolean isLeaf;
        TrieNode[] children;
        
        public TrieNode(){
            isLeaf = false;
            children = new TrieNode[26];
            for(int i = 0;i<26;i++){
                children[i] = null;
            }
        }
    }
    static void insert(String s,TrieNode root){
        if(s.length() == 0){
            root.isLeaf = true;
            return;
        }
        int index = s.charAt(0)-'a';
        TrieNode child;
        if(root.children[index] != null){
            child = root.children[index];
        }else{
            child = new TrieNode();
            root.children[index] = child;
        }
    
        insert(s.substring(1),child);
    }
    
    static void helper(TrieNode root, String prefix){
        boolean checker = true;
        
        if(root.isLeaf){
            System.out.println(prefix);
        }
        for (int i = 0; i < 26; i++){
            TrieNode temp = root;
            if (temp.children[i] != null){
                char suffix = (char)(i + (int)'a');
                helper(root.children[i],prefix + suffix);
            }
    	}
    }
    
    static void printer(TrieNode root,String s){
        TrieNode curr = root;
        
        for (int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            if (curr.children[index] != null){
                curr = curr.children[index];
            }else{
                System.out.println("No suggestions");
                insert(s, root);
                return;
            }
    	}

    	helper(curr, s);
    }
    
    static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// Write your code here
		int n = in.nextInt();
        TrieNode root = new TrieNode();
        for(int i = 0;i<n;i++){
            String s = in.next();
            insert(s,root);
        }
        
        int q = in.nextInt();
        for(int i = 0 ;i<q;i++){
            String s = in.next();
            printer(root,s);
        }
	}

}
