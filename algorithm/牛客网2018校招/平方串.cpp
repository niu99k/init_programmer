/*
题目描述
如果一个字符串S是由两个字符串T连接而成,即S = T + T, 我们就称S叫做平方串,例如"","aabaab","xxxx"都是平方串.
牛牛现在有一个字符串s,请你帮助牛牛从s中移除尽量少的字符,让剩下的字符串是一个平方串。换句话说,就是找出s的最长子序列并且这个子序列构成一个平方串。
输入描述:
输入一个字符串s,字符串长度length(1 ≤ length ≤ 50),字符串只包括小写字符。
输出描述:
输出一个正整数,即满足要求的平方串的长度。
示例1
输入
复制
frankfurt
输出
复制
4
*/

#include<iostream>
#include<vector>
#include<map>
#include<algorithm>
using namespace std;
void getMaxCount(int& count, string& str1, string& str2) {
	map<int, map<int, int>> dpMap = {};
	for (int i = 0;i < str1.size();i++) {
		for (int j = 0;j < str2.size();j++) {
			if (str1[i] == str2[j]) {
				dpMap[i][j] = dpMap[i - 1][j - 1] + 1;
			}
			else {
				dpMap[i][j] = max(dpMap[i - 1][j], dpMap[i][j - 1]);
			}
		}
	}
	count = dpMap[str1.size() - 1][str2.size() - 1];
}

int main() {
	string str = "";
	cin >> str;
	int maxCount = 0;
	for (int i = 1;i < str.size();i++) {
		string str1 = str.substr(0, i);
		string str2 = str.substr(i, str.size() - i);
		int count = 0;
		getMaxCount(count, str1, str2);
		if (count > maxCount) {
			maxCount = count;
		}
	}
	cout << maxCount * 2;
	return 0;
}