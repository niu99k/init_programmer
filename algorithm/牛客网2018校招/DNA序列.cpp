/*
题目描述
牛牛又从生物科研工作者那里获得一个任务,这次牛牛需要帮助科研工作者从DNA序列s中找出最短没有出现在DNA序列s中的DNA片段的长度。
例如:s = AGGTCTA
序列中包含了所有长度为1的('A','C','G','T')片段,但是长度为2的没有全部包含,例如序列中不包含"AA",所以输出2。
输入描述:
输入包括一个字符串s,字符串长度length(1 ≤ length ≤ 2000),其中只包含'A','C','G','T'这四种字符。
输出描述:
输出一个正整数,即最短没有出现在DNA序列s中的DNA片段的长度。
示例1
输入
复制
AGGTCTA
输出
复制
2
*/
#include<iostream>
#include<map>
#include<vector>
#define DNA_CATEGORY 4

void getRealCount(int& realCount, int& countIndex, std::string& str) {
	std::map<std::string, int> testCountMap = {};
	for (int i = 0;i < str.size() + 1 - countIndex;i++) {
		std::string oneRealstr = str.substr(i, countIndex);
		testCountMap[oneRealstr] = 1;
	}
	realCount = testCountMap.size();
}
void getMaxCount(int& maxCount, int& countIndex, int categoryCount) {
	maxCount = 1;
	for (int i = 0;i < countIndex;i++) {
		maxCount *= categoryCount;
	}
}
int main() {
	std::string str = "AGGTCTA";
	std::cin >> str;
	for (int i = 1;i <= str.size();i++) {
		int realCount = 0;
		getRealCount(realCount, i, str);
		int maxCount = 0;
		getMaxCount(maxCount, i, DNA_CATEGORY);
		if (realCount < maxCount) {
			std::cout << i;
			return 0;
		}
	}
	//逐个数量级 找字符串数量
	return 0;
}