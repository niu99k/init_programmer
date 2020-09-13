/*
题目描述
牛牛养了n只奶牛,牛牛想给每只奶牛编号,这样就可以轻而易举地分辨它们了。 每个奶牛对于数字都有自己的喜好,第i只奶牛想要一个1和x[i]之间的整数(其中包含1和x[i])。
牛牛需要满足所有奶牛的喜好,请帮助牛牛计算牛牛有多少种给奶牛编号的方法,输出符合要求的编号方法总数。
输入描述:
输入包括两行,第一行一个整数n(1 ≤ n ≤ 50),表示奶牛的数量 第二行为n个整数x[i](1 ≤ x[i] ≤ 1000)
输出描述:
输出一个整数,表示牛牛在满足所有奶牛的喜好上编号的方法数。因为答案可能很大,输出方法数对1,000,000,007的模。
示例1
输入
复制
4
4 4 4 4
输出
复制
24
*/

#include<iostream>
#include<vector>
#include<map>
#include<algorithm>

#define MOD 1000000007
using namespace std;
bool cmp(long long a, long long b) {
	return a < b;
}
int main() {
	long long numCount = 0;
	cin >> numCount;
	vector<long long>numVec;
	for (int i = 0;i < numCount;i++) {
		long long temp = 0;
		cin >> temp;
		numVec.push_back(temp);
	}
	sort(numVec.begin(), numVec.end(), cmp);
	long long possibleCount = 1;
	for (int i = 0;i < numVec.size();i++) {
		possibleCount = (possibleCount * (numVec[i]-i)) % MOD;
	}
	cout << possibleCount;
	return 0;
}