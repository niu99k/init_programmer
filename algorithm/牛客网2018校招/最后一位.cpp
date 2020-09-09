/*
题目描述
牛牛选择了一个正整数X,然后把它写在黑板上。然后每一天他会擦掉当前数字的最后一位,直到他擦掉所有数位。 在整个过程中,牛牛会把所有在黑板上出现过的数字记录下来,然后求出他们的总和sum.
例如X = 509, 在黑板上出现过的数字依次是509, 50, 5, 他们的和就是564.
牛牛现在给出一个sum,牛牛想让你求出一个正整数X经过上述过程的结果是sum.
输入描述:
输入包括正整数sum(1 ≤ sum ≤ 10^18)
输出描述:
输出一个正整数,即满足条件的X,如果没有这样的X,输出-1。
示例1
输入
复制
564
输出
复制
509*/

#include<iostream>
#include<vector>
#include<map>
#include<string>

int main() {
	using namespace std;
	long long num = 0;
	std::cin >> num;
	long long originalResult = num;
	string numToStr = to_string(num);
	int digit = numToStr.size();
	long long temp = 1;
	long long firstDivide = 0;
	for (int i = 0;i < digit;i++) {
		firstDivide += temp;
		temp *= 10;
	}
	vector<int> resultVec;
	while (firstDivide > 0) {
		int temp4Result = num / firstDivide;
		resultVec.push_back(temp4Result);
		num -= temp4Result * firstDivide;
		firstDivide /= 10;
	}

	long long testResult = 0;
	long long xishu = 1;
	for (int i = resultVec.size() - 1;i >= 0;i--) {
		testResult += xishu * resultVec[i];
		xishu *= 10;
	}
	long long expectedResult = 0;
	while (testResult > 0) {
		expectedResult += testResult;
		testResult /= 10;
	}

	if (expectedResult == originalResult) {
		for (auto& e : resultVec) {
			std::cout << e;
		}
	}
	else {
		std::cout << -1;
	}
	return 0;
}