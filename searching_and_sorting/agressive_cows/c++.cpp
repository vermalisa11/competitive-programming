// Aggressive Cows Problem
// Send Feedback
// Farmer John has built a new long barn, with N (2 <= N <= 100,000) stalls. The stalls are located along a straight line at positions x1,...,xN (0 <= xi <= 1,000,000,000).
// His C (2 <= C <= N) cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, FJ wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?
// Input
// t – the number of test cases, then t test cases follows. 
// * Line 1: Two space-separated integers: N and C
// * Lines 2..N+1: Line i+1 contains an integer stall location, xi
// Output
// For each test case output one integer: the largest minimum distance.
// Sample Input :
// 1
// 5 3
// 1
// 2
// 8
// 4
// 9
// Sample Output:
// 3 
// Output details:
// FJ can put his 3 cows in the stalls at positions 1, 4 and 8, 
// resulting in a minimum distance of 3.
//solution----------------------------------------------------------------------------------------------------
#include <algorithm>
#include <iostream>
using namespace std;
bool check(int cows,long long pos[],int n,long long distance){
		int count=1;
		long long last_pos=pos[0];
		for(int i=1;i<n;i++){
			if(pos[i]-last_pos>=distance){
				last_pos=pos[i];
				count++;
			}
			if(count==cows){
				return true;
			}
		}
		return false;
}

int main() {
	int t;
    cin>>t;
	while(t--){
		int n,c;
		cin>>n>>c;
		
		long long pos[n];
		for(int i=0;i<n;i++){
			cin>>pos[i];
		}
		sort(pos,pos+n);
		long long start=0;
		long long end = pos[n-1] -pos[0];
		
		long ans=-1;
		while(start<=end){
			long long mid=start + (end-start)/2;
			if(check(c,pos,n,mid)){
				ans=mid;
				start=mid+1;
			}else{
				end=end-1;
			}
		}cout<<ans<<endl;
	}
}
