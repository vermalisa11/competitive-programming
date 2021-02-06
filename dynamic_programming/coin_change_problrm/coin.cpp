// For the given infinite supply of coins of each of denominations, D = {D0, D1, D2, D3, ...... Dn-1}. You need to figure out the total number of ways W, in which you can make the change for Value V using coins of denominations D.
// Return 0 if the change isn't possible.
// Input Format
// The first line of the input contains an integer value N, which denotes the total number of denominations.

// The second line of input contains N values, separated by a single space. These values denote the value of denomination.

// The third line of the input contains an integer value, that denotes the value of V.
// Output Format
// Print the total total number of ways i.e. W.
// Constraints :
// 1 <= n <= 10
// 1 <= V <= 1000

// Time Limit: 1sec
// Sample Input 1 :
// 3
// 1 2 3
// 4
// Sample Output 1 :
// 4
// Explanation to Sample Input 1 :
// Number of ways are - 4 total i.e. (1,1,1,1), (1,1, 2), (1, 3) and (2, 2).
// Sample Input 2 :
// 6
// 1 2 3 4 5 6
// 250
// Sample Output 2 :
// 13868903

#include <iostream>
using namespace std;

int coin_change(int n, int* d, int numD, int** output) {
	if (n == 0) {
		return 1;
	}
	if (n < 0) {
		return 0;
	}
	if (numD == 0) {
		return 0;
	}
	if (output[n][numD] > -1) {
		return output[n][numD];
	}
	int first = coin_change(n - d[0], d, numD);
	int second = coin_change(n, d + 1, numD - 1);
	output[n][numD] = first + second;
	return first + second;
}

int main() {
	int d[2] = {1,2};
	// allocate 2d array here, n + 1 * numD + 1
	// put all -1
	cout << coin_change(4, d, 2) << endl;
}
