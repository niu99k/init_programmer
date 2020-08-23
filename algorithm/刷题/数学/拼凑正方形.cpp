/*题目描述
牛牛有4根木棍,长度分别为a,b,c,d。羊羊家提供改变木棍长度的服务,如果牛牛支付一个硬币就可以让一根木棍的长度加一或者减一。牛牛需要用这四根木棍拼凑一个正方形出来,牛牛最少需要支付多少硬币才能让这四根木棍拼凑出正方形。
输入描述:
输入包括一行,四个整数a,b,c,d(1 ≤ a,b,c,d ≤ 10^6), 以空格分割
输出描述:
输出一个整数,表示牛牛最少需要支付的硬币
示例1
输入
复制
4 1 5 4
输出
复制
4*/
#include<iostream>
#include<map>
#include<vector>

#define INT_MAX 0x7ffffff
#define INT_MIN 0x8000000
#define NUMBER_COUNT 4
int main() {
	std::vector<int> stickLengthVec = {};
	for (int i = 0;i < NUMBER_COUNT;i++) {
		int temp = 0;
		std::cin >> temp;
		stickLengthVec.push_back(temp);
	}
	int minMove = INT_MAX;

	for (int i = 0;i < stickLengthVec.size();i++) {
		int countTest = 0;
		for (int j = 0;j < stickLengthVec.size();j++) {
			countTest += std::abs(stickLengthVec[i] - stickLengthVec[j]);
		}
		if (countTest < minMove) {
			minMove = countTest;
		}
	}
	std::cout << minMove;

	return 0;
}