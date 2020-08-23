/*题目描述
有一种有趣的字符串价值计算方式:统计字符串中每种字符出现的次数,然后求所有字符次数的平方和作为字符串的价值
例如: 字符串"abacaba",里面包括4个'a',2个'b',1个'c',于是这个字符串的价值为4 * 4 + 2 * 2 + 1 * 1 = 21
牛牛有一个字符串s,并且允许你从s中移除最多k个字符,你的目标是让得到的字符串的价值最小。
输入描述:
输入包括两行,第一行一个字符串s,字符串s的长度length(1 ≤ length ≤ 50),其中只包含小写字母('a'-'z')。
第二行包含一个整数k(0 ≤ k ≤ length),即允许移除的字符个数。
输出描述:
输出一个整数,表示得到的最小价值
示例1
输入
复制
aba
1
输出
复制
2*/
#include<iostream>
#include<map>
#include<vector>
void initMap(std::string& str, std::map<int, int>& result) {
	std::map<std::string, int>repetitionCountMap = {};
	for (int i = 0;i < str.size();i++) {
		std::string chr = str.substr(i, 1);
		repetitionCountMap[chr] += 1;
	}
	for (auto& e : repetitionCountMap) {
		result[e.second] += 1;
	}

}
void sim(std::map<int, int>& repetitionCountMapAfterSim, std::map<int, int> repetitionCountMap, int moveCount) {
	std::vector<int> repetitionCountMapKeyVec = {};
	for (auto& e : repetitionCountMap) {
		repetitionCountMapKeyVec.push_back(e.first);
	}
	int maxKey = repetitionCountMapKeyVec[repetitionCountMapKeyVec.size() - 1];
	while (moveCount > 0 && maxKey >= 1) {
		if (repetitionCountMap[maxKey] > moveCount) {
			repetitionCountMap[maxKey] -= moveCount;
			if (maxKey - 1 >= 0) {
				repetitionCountMap[maxKey - 1] += moveCount;
				maxKey--;
			}
			moveCount = 0;
		}
		else {
			moveCount -= repetitionCountMap[maxKey];
			if (maxKey >= 1) {
				repetitionCountMap[maxKey - 1] += repetitionCountMap[maxKey];
			}
			repetitionCountMap[maxKey] = 0;
			maxKey--;
		}
	}
	for (auto& e : repetitionCountMap) {
		repetitionCountMapAfterSim[e.first] = e.second;
	}
}
void getResult(int& value, std::map<int, int>& repetitionCountMapAfterSim) {
	for (auto& e : repetitionCountMapAfterSim) {
		value += e.second * e.first * e.first;
	}
};
int main() {
	std::string str = "aba";
	int moveCount = 1;
	std::cin >> str;
	std::cin >> moveCount;

	//init map<int,int>
	std::map<int, int>repetitionCountMap = {};
	initMap(str, repetitionCountMap);

	//sim
	std::map<int, int>repetitionCountMapAfterSim = {};
	sim(repetitionCountMapAfterSim, repetitionCountMap, moveCount);

	//get result
	int value = 0;
	getResult(value, repetitionCountMapAfterSim);

	std::cout << value;
	return 0;
}