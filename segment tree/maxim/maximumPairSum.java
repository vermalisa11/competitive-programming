/* You are given a sequence A[1], A[2], ..., A[N], ( 0 ≤ A[i] ≤ 10^8 , 2 ≤ N ≤ 10^5 ). There are two types of operations and they are defined as follows:
Update:
This will be indicated in the input by a 'U' followed by space and then two integers i and x.
U i x, 1 ≤ i ≤ N, and x, 0 ≤ x ≤ 10^8.
This operation sets the value of A[i] to x.
Query:
This will be indicated in the input by a 'Q' followed by a single space and then two integers i and j.
Q x y, 1 ≤ x < y ≤ N.
You must find i and j such that x ≤ i, j ≤ y and i != j, such that the sum A[i]+A[j] is maximized. Print the sum A[i]+A[j].
Input
The first line of input consists of an integer N representing the length of the sequence. 
Next line consists of N space separated integers A[i]. Next line contains an integer Q, Q ≤ 10^5, representing the number of operations. Next Q lines contain the operations.
Output
 Output the maximum sum mentioned above, in a separate line, for each Query.
Input:
5
1 2 3 4 5
6
Q 2 4
Q 2 5
U 1 6
Q 1 5
U 1 7
Q 1 5
Output:
7
9
11
12*/

#include<bits/stdc++.h>

using namespace std;
#define ll long long
#define mod 1000000007
struct t {
    ll val = 0;
    ll idx = 0;
}
tree[300005], x, y, p, q, z, w;
ll n, a[100005], i, l, d, r, sum;
char c;
void build(ll node, ll lo, ll hi) {
    if (lo == hi) {
        tree[node].val = a[lo];
        tree[node].idx = lo;
    } else {
        ll mid = (lo + hi) / 2;
        build(2 * node, lo, mid);
        build(2 * node + 1, mid + 1, hi);
        t x = tree[2 * node];
        t y = tree[2 * node + 1];
        if (x.val > y.val) {
            tree[node].val = x.val;
            tree[node].idx = x.idx;
        } else {
            tree[node].val = y.val;
            tree[node].idx = y.idx;
        }
    }
}
t query(ll node, ll lo, ll hi, ll l, ll r) {
    if (l > hi || r < lo || lo > hi || l > r) return z;
    if (l <= lo && hi <= r) return tree[node];
    ll mid = (lo + hi) / 2;
    t x = query(2 * node, lo, mid, l, r);
    t y = query(2 * node + 1, mid + 1, hi, l, r);
    if (x.val > y.val) return x;
    else return y;
}
void update(ll node, ll lo, ll hi, ll id, ll amt) {
    if (lo == hi) {
        a[id] = amt;
        tree[node].val = amt;
    } else {
        ll mid = (lo + hi) / 2;
        if (lo <= id && id <= mid) update(2 * node, lo, mid, id, amt);
        else update(2 * node + 1, mid + 1, hi, id, amt);
        if (tree[2 * node].val > tree[2 * node + 1].val) {
            tree[node].val = tree[2 * node].val;
            tree[node].idx = tree[2 * node].idx;
        } else {
            tree[node].val = tree[2 * node + 1].val;
            tree[node].idx = tree[2 * node + 1].idx;
        }
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cin >> n;
    for (i = 1; i <= n; i++) cin >> a[i];
    build(1, 1, n);
    cin >> d;
    while (d--) {
        cin >> c >> l >> r;
        if (l > r) {
            cout << "0" << endl;
            continue;
        }
        if (c == 'Q') {
            p = query(1, 1, n, l, r);
            q = query(1, 1, n, l, p.idx - 1);
            w = query(1, 1, n, p.idx + 1, r);
            sum = p.val + max(q.val, w.val);
            cout << sum << endl;
        } else {
            update(1, 1, n, l, r);
        }
    }
    return 0;
}
