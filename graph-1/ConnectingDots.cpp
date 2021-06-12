/*Gary has a board of size NxM. Each cell in the board is a coloured dot. There exist only 26 colours denoted by uppercase Latin characters (i.e. A,B,...,Z). Now Gary is getting bored and wants to play a game. The key of this game is to find a cycle that contain dots of same colour. Formally, we call a sequence of dots d1, d2, ..., dk a cycle if and only if it meets the following condition:
1. These k dots are different: if i ≠ j then di is different from dj.
2. k is at least 4.
3. All dots belong to the same colour.
4. For all 1 ≤ i ≤ k - 1: di and di + 1 are adjacent. Also, dk and d1 should also be adjacent. Cells x and y are called adjacent if they share an edge.
Since Gary is colour blind, he wants your help. Your task is to determine if there exists a cycle on the board.
Input Format :
The first line of input contains two space separated integers N and M, where N is number of rows and M is the number of columns of the board. 
Each of the following N lines contain M characters. Please note that characters are not space separated. Each character is an uppercase Latin letter.
Output Format :
Print true if there is a cycle in the board, else print false.
Constraints :
2 <= N <= 1000
2 <= M <= 1000
Time Limit: 1 second
Sample Input 1:
3 4
AAAA
ABCA
AAAA
Sample Output 1:
true*/

#include <iostream>
#include <vector>
using namespace std;

#include "solution.h"


bool helper(vector<vector<char>> &board,char c,int sx,int sy,int ex,int ey,int n,int m,int **visited,int l){
        // cout<<sx<<" "<<sy<<endl;
    // cout<<sx<<" "<<sy<<" "<<ex<<" "<<ey<<endl;
    if(sx==ex and sy==ey and l>=2) { return true;}
    if(ex+1<n and board[ex+1][ey]==c and visited[ex+1][ey]==0){
        visited[ex+1][ey]=1;
        bool ans=helper(board,c,sx,sy,ex+1,ey,n,m,visited,l+1);
        if(ans==true) return ans;
        visited[ex+1][ey]=0;
    }
    if(ex>=1 and board[ex-1][ey]==c and visited[ex-1][ey]==0){
        visited[ex-1][ey]=1;
        bool ans=helper(board,c,sx,sy,ex-1,ey,n,m,visited,l+1);
        if(ans==true) return ans;
        visited[ex-1][ey]=0;
    }
    if(ey+1<m and board[ex][ey+1]==c and visited[ex][ey+1]==0){
        visited[ex][ey+1]=1;
        bool ans=helper(board,c,sx,sy,ex,ey+1,n,m,visited,l+1);
        if(ans==true) return ans;
        visited[ex][ey+1]=0;
    }
    if(ey>=1 and board[ex][ey-1]==c and visited[ex][ey-1]==0){
        visited[ex][ey-1]=1;
        bool ans=helper(board,c,sx,sy,ex,ey-1,n,m,visited,l+1);
        if(ans==true) return ans;
        visited[ex][ey-1]=0;
    }
    return false;
}
bool hasCycle(vector<vector<char>> &board, int n, int m) {
    int freq[26];
    for(int i=0;i<26;i++) freq[i]=0;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            freq[board[i][j]-'A']++;
        }
    }
    int**visited=new int*[n];
    for(int k=0;k<n;k++){
         visited[k]= new int[m];
         for(int p=0;p<m;p++) visited[k][p]=0;
    }
    for(int k=0;k<26;k++){
        if(freq[k]>=4){
            char c=k+'A';
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(board[i][j]==c){
                        // cout<<i<<" "<<j<<" "<<c<<endl;
                        bool ans=helper(board,c,i,j,i,j,n,m,visited,-1);
                        if(ans==true) return ans;
                        
                    }
                }
            }
        }
    }
    return false;
}
int main() {
    int n, m;
    cin >> n >> m;

    vector<vector<char>> board(n, vector<char>(m));

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> board[i][j];
        }
    }

    cout << (hasCycle(board, n, m) ? "true" : "false");
}
