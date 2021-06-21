/*Segment trees are extremely useful. In particular "Lazy Propagation" (i.e. see here, for example) allows one to compute sums over a range in O(lg(n)), and update ranges in O(lg(n)) as well. In this problem you will compute something much harder:
The sum of squares over a range with range updates of 2 types:
1) increment in a range
2) set all numbers the same in a range.
Input
There will be T (T <= 25) test cases in the input file. First line of the input contains two positive integers, N (N <= 100,000) and Q (Q <= 100,000). 
The next line contains N integers, each at most 1000. Each of the next Q lines starts with a number, which indicates the type of operation:

2 st nd -- return the sum of the squares of the numbers with indices in [st, nd] {i.e., from st to nd inclusive} (1 <= st <= nd <= N).

1 st nd x -- add "x" to all numbers with indices in [st, nd] (1 <= st <= nd <= N, and -1,000 <= x <= 1,000).

0 st nd x -- set all numbers with indices in [st, nd] to "x" (1 <= st <= nd <= N, and -1,000 <= x <= 1,000).
Output
For each test case output the “Case <caseno>:” in the first line and from the second line output the sum of squares for each operation of type 2. Intermediate overflow will not occur with proper use of 64-bit signed integer.
Input:
2
4 5
1 2 3 4
2 1 4
0 3 4 1
2 1 4
1 3 4 1
2 1 4
1 1
1
2 1 1
Output:
Case 1:
30
7
13
Case 2:
1
  */

#include<iostream>
using namespace std;
struct tree_attrs
{
	int sum_of_squares;
	int sum;
};
struct lazy_attrs
{
	int setter = 0;
	int incermentor = 0;
};
void build_tree(int* arr, tree_attrs* tree, int start, int end, int treenode)
{
	if (start == end)
	{
		tree[treenode].sum_of_squares = arr[start]*arr[start];
		tree[treenode].sum = arr[start];
		return;
	}
	int mid = (start + end) / 2;
	build_tree(arr, tree, start, mid, 2 * treenode);
	build_tree(arr, tree, mid + 1, end, 2 * treenode + 1);
	tree[treenode].sum = tree[2 * treenode].sum + tree[2 * treenode + 1].sum;
	tree[treenode].sum_of_squares = tree[2 * treenode].sum_of_squares + tree[2 * treenode + 1].sum_of_squares;
}
void lazy_update_setter(tree_attrs* tree, lazy_attrs* lazy, int start, int end, int left, int right, int current_position, int x)
{
	if (start > end)
	{
		return;
	}
	if (lazy[current_position].setter != 0)
	{
		tree[current_position].sum = (right-left + 1) * (lazy[current_position].setter);
		tree[current_position].sum_of_squares = (right-left + 1) * (lazy[current_position].setter * lazy[current_position].setter);
		if (start != end)
		{
			lazy[2 * current_position].setter = lazy[current_position].setter;
			lazy[2 * current_position+1].setter = lazy[current_position].setter;
		}
		lazy[current_position].setter = 0;
	}

	//completely outside
	if (start > right || end < left)
	{
		return;
	}
	//complete overlap
	if (start >= left && end <= right)
	{
		tree[current_position].sum_of_squares = (right-left + 1) * (x * x);
		tree[current_position].sum = (right-left + 1) * x;
		if (start != end)
		{
			lazy[2 * current_position].setter = x;
			lazy[2 * current_position + 1].setter = x;
		}
		return;
	}
	//partial overlap
	int mid = (start + end) / 2;
	lazy_update_setter(tree, lazy, start, mid, left, right, 2 * current_position, x);
	lazy_update_setter(tree, lazy, mid+1, end, left, right, 2 * current_position+1, x);
	tree[current_position].sum = tree[2 * current_position].sum + tree[2 * current_position + 1].sum;
	tree[current_position].sum_of_squares = tree[2 * current_position].sum_of_squares + tree[2 * current_position + 1].sum_of_squares;
	return;
}
void lazy_update_incrementor(tree_attrs* tree, lazy_attrs* lazy, int start, int end, int left, int right, int current_position, int increment)
{
	if (start > end)
	{
		return;
	}
	if (lazy[current_position].incermentor != 0)
	{
		tree[current_position].sum_of_squares += (right-left + 1) * (lazy[current_position].incermentor * lazy[current_position].incermentor) + 2 * lazy[current_position].incermentor * tree[current_position].sum;
		tree[current_position].sum += (right-left + 1) * lazy[current_position].incermentor;
		if (start != end)
		{
			lazy[2 * current_position].incermentor += lazy[current_position].incermentor;
			lazy[2 * current_position + 1].incermentor += lazy[current_position].incermentor;
		}
		lazy[current_position].incermentor = 0;
	}
	if (lazy[current_position].setter != 0)
	{
		tree[current_position].sum = (right - left + 1) * (lazy[current_position].setter);
		tree[current_position].sum_of_squares = (right - left + 1) * (lazy[current_position].setter * lazy[current_position].setter);
		if (start != end)
		{
			lazy[2 * current_position].setter = lazy[current_position].setter;
			lazy[2 * current_position + 1].setter = lazy[current_position].setter;
		}
		lazy[current_position].setter = 0;
	}
	//completely outside
	if (start > right || end < left)
	{
		return;
	}
	//complete overlap
	if(start>=left&&end<=right)
	{
		tree[current_position].sum_of_squares += (right-left + 1) * (increment * increment) + 2 * increment * tree[current_position].sum;
		tree[current_position].sum += (right-left + 1) * increment;
		if (start != end)
		{
			lazy[2 * current_position].incermentor += increment;
			lazy[2 * current_position + 1].incermentor += increment;
		}
		return;
	}
	//partial overlap
	int mid = (start + end) / 2;
	lazy_update_incrementor(tree, lazy, start, mid, left, right, 2 * current_position, increment);
	lazy_update_incrementor(tree, lazy, mid+1, end, left, right, 2 * current_position+1, increment);
	tree[current_position].sum = tree[2 * current_position].sum + tree[2 * current_position + 1].sum;
	tree[current_position].sum_of_squares = tree[2 * current_position].sum_of_squares + tree[2 * current_position + 1].sum_of_squares;
	return;
}

tree_attrs query(tree_attrs* tree, lazy_attrs *lazy, int start, int end, int left, int right, int treenode)
{
	if (start > end)
	{
		return { 0, 0 };
	}
	if (lazy[treenode].incermentor != 0)
	{
		tree[treenode].sum_of_squares += 2 * lazy[treenode].incermentor * tree[treenode].sum + lazy[treenode].incermentor * lazy[treenode].incermentor * (right - left + 1);
		tree[treenode].sum += (right - left + 1) * lazy[treenode].incermentor;
		if (start != end)
		{
			lazy[2 * treenode].incermentor = lazy[treenode].incermentor;
			lazy[2 * treenode + 1].incermentor = lazy[treenode].incermentor;
		}
		lazy[treenode].incermentor = 0;
	}
	if (lazy[treenode].setter != 0)
	{
		tree[treenode].sum = (right - left + 1) * lazy[treenode].setter;
		tree[treenode].sum_of_squares = (right - left + 1) * lazy[treenode].setter;
		if (start != end)
		{
			lazy[2 * treenode].setter = lazy[treenode].setter;
			lazy[2 * treenode + 1].setter = lazy[treenode].setter;
		}
		lazy[treenode].setter = 0;
	}
	//completely outside
	if (start > right || end < left)
	{
		return { 0, 0 };
	}
	//fully inside
	if (start >= left && end <= right)
	{
		return tree[treenode];
	}
	//partial overlap
	int mid = (start + end) / 2;
	tree_attrs left_child = query(tree, lazy, start, mid, left, right, 2 * treenode);
	tree_attrs right_child = query(tree, lazy, mid+1, end, left, right, 2 * treenode+1);
	tree_attrs ans;
	ans.sum = left_child.sum + right_child.sum;
	ans.sum_of_squares = left_child.sum_of_squares + right_child.sum_of_squares;
	return ans;
}

int main()
{
	int t;
	cin >> t;
	int case_number = 1;
	while (t--)
	{
		cout << "Case " << case_number << ":" << endl;
		int n, q;
		cin >> n >> q;
		int* arr = new int[n];
		for (int i = 0; i < n; i++)
		{
			cin >> arr[i];
		}
		tree_attrs* tree = new tree_attrs[4 * n]();
		lazy_attrs* lazy = new lazy_attrs[4 * n]();
		build_tree(arr, tree, 0, n - 1, 1);
		while (q--)
		{
			int type_of_operation;
			cin >> type_of_operation;
			if (type_of_operation == 2)
			{
				int left, right;
				cin >> left >> right;
				cout << query(tree, lazy, 0, n-1, left - 1, right - 1, 1).sum_of_squares<<endl;
			}
			else if (type_of_operation == 1)
			{
				int left, right;
				cin >> left >> right;
				int increment;
				cin >> increment;
				lazy_update_incrementor(tree, lazy, 0, n-1, left-1, right-1, 1, increment);
			}
			else
			{
				int left, right;
				cin >> left >> right;
				int x;
				cin >> x;
				lazy_update_setter(tree, lazy, 0, n-1, left-1, right-1, 1, x);
			}
		}
		case_number++;
	}
}
