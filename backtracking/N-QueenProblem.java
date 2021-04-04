/*N-Queen Problem
Send Feedback
You are given N, and for a given N x N chessboard, find a way to place N queens such that no queen can attack any other queen on the chess board. A queen can be killed when it lies in the same row, or same column, or the same diagonal of any of the other queens. You have to print all such configurations.
Input Format :
Line 1 : Integer N
Output Format :
One Line for every board configuration. 
Every line will have N*N board elements printed row wise and are separated by space
Note : Don't print anything if there isn't any valid configuration.
Constraints :
1<=N<=10
Sample Input 1:
4
Sample Output 1 :
0 1 0 0 0 0 0 1 1 0 0 0 0 0 1 0 
0 0 1 0 1 0 0 0 0 0 0 1 0 1 0 0 */
 class Solution {
	
		
	public static void placeNQueens(int n){
		
	/* Your class should be named Solution.
	 * Don't write main() function.
	 * Don't read input, it is passed as function argument.
	 * Print output as specified in the question
	 */
	int[][] board = new int[n][n];
    nqueen(board, n,0);
	
	}
    
    
    private static void nqueen(int[][] arr, int n,int i) {
        if (n==i){
        //Successfully place the queens in n row position
        //Print the rows
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.print(arr[j][k]+" ");
                }
            }
            System.out.println();
        }
        //Recursive Case
        //Try to place the Queen in the Front row only rest will be handeled by the recursive leap of faith
        for (int j = 0; j < n; j++) {
            //check if i,j position is safe to place the Queen or not
            if (isPossible(arr,n,i,j)) {
                // System.out.println("***");
                // Placing the Queen Assuming it is the Right Position
                arr[i][j] = 1;
                nqueen(arr,n, i + 1);
                //If we came here it means that the next position is not filled,
                //Our assumption is Wrong.
                arr[i][j] = 0;
            }
        }
        //You have tried for all position in the current row but couldn't place a queen
        return ;
    }

    
    public static boolean isPossible(int[][] board, int n,int row,int col){
    // Same Column
      for(int i=row-1;i>=0;i--){
        if(board[i][col] == 1){
          return false;
        }
      }
    //Upper Left Diagonal
      for(int i=row-1,j=col-1;i>=0 && j>=0 ; i--,j--){
        if(board[i][j] ==1){
          return false;
        }
      }

      // Upper Right Diagonal

      for(int i=row-1,j=col+1;i>=0 && j<n ; i--,j++){
        if(board[i][j] == 1){
          return false;
        }
      }

      return true;
    }
}


