/*
题目描述
牛牛得到一个长度为n的整数序列V,牛牛定义一段连续子序列的幸运值为这段子序列中最大值和次大值的异或值(次大值是严格的次大)。牛牛现在需要求出序列V的所有连续子序列中幸运值最大是多少。请你帮帮牛牛吧。
输入描述:
第一行一个整数n,即序列的长度。(2<= n <= 100000)
第二行n个数，依次表示这个序列每个数值V[i], (1 ≤ V[i] ≤ 10^8)且保证V[1]到V[n]中至少存在不同的两个值.
输出描述:
输出一个整数,即最大的幸运值
示例1
输入
复制
5
5 2 1 4 3
输出
复制
7
*/
#include<iostream>
#include<map>
#include<vector>
#include<stack>
#include<algorithm>
int main() {
	//设置单调栈
	int numCount = 0;
	std::cin >> numCount;
	std::stack<int> numStack;
	//若新元素违反单调原则（递增）则将栈逐个pop直到栈重新满足单调递增原则
	int max = 0;
	for (int i = 0;i < numCount;i++) {
		int temp = 0;
		std::cin >> temp;
		while (!numStack.empty() && temp >= numStack.top()) {
			int top = numStack.top();
			numStack.pop();
			max = std::max(max, top ^ temp);
		}
		if (!numStack.empty()) {
			int top = numStack.top();
			max = std::max(max, top ^ temp);
		}
		numStack.push(temp);
	}
	std::cout << max;
}