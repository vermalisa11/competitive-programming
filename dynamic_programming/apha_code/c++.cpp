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

//solution-----------------------------------------------------------------------------------------------------------------
#include <bits/stdc++.h>
#include <sstream>
using namespace std;
int count(int arr[],int size){
	int mod=(int)pow(10,9)+7;
	long long* output = new long long[size];
    for(int i=0;i<size;i++){
        output[i]=0;
    }
	output[0]=1;
	
	for(int i=1;i<size;i++){
        int x=arr[i-1]*10+arr[i];
		if(arr[i]!=0){
            output[i]=output[i-1];
        }
        if(x>=10 && x<=26 &&i>1){
            output[i]+=output[i-2];
        }
        else if(x>=10 && x<=26){
            output[i]++;
        }
        output[i]=output[i]%mod;
	}
	long long ans=output[size-1]%mod;
	delete[] output;
	return ans;
}




int main()
{ 	
	bool flag = true;
	while(flag){
		string s;
		cin>>s;
        if(s=="0"){
			flag=false;
            break;
		}
		int arr[s.length()];
		for(int i=0;i<s.length();i++){
			arr[i]=(int)s[i]-48;
			//cout<<arr[i];
		}
		cout<<count(arr,s.length())<<endl;
		
	}
}