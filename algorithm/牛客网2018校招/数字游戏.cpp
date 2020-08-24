/*
题目描述
牛牛举办了一场数字游戏,有n个玩家参加这个游戏,游戏开始每个玩家选定一个数,然后将这个数写在纸上(十进制数,无前缀零),然后接下来对于每一个数字将其数位按照非递减顺序排列,得到新的数,新数的前缀零将被忽略。得到最大数字的玩家赢得这个游戏。
输入描述:
输入包括两行,第一行包括一个整数n(1 ≤ n ≤ 50),即玩家的人数
第二行n个整数x[i](0 ≤ x[i] ≤ 100000),即每个玩家写下的整数。
输出描述:
输出一个整数,表示赢得游戏的那个玩家获得的最大数字是多少。
示例1
输入
复制
3
9638 8210 331
输出
复制
3689*/
#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
void getVecAfterSort(std::vector<std::vector<char>>& vecAfterSort, std::vector<std::string>& numVec) {
	for (int i = 0;i < numVec.size();i++) {
		std::string strTemp = numVec[i];
		std::vector<char> chrVec = {};
		for (int j = 0;j < strTemp.size();j++) {
			chrVec.push_back(strTemp[j]);
		}
		std::sort(chrVec.begin(), chrVec.end(), std::less<int>());
		vecAfterSort.push_back(chrVec);
	}
};
void getVecRemoveZero(std::vector<std::vector<char>>& vecAfterRemoveZero, std::vector<std::vector<char>>& vecAfterSort) {
	for (int i = 0;i < vecAfterSort.size();i++) {
		std::vector<char> chrVecTemp = vecAfterSort[i];
		if (chrVecTemp[0] != '0') {
			vecAfterRemoveZero.push_back(chrVecTemp);
		}
		else {
			std::vector<char>  chrVecCopy = {};
			for (int j = 0;j < chrVecTemp.size();j++) {
				if (chrVecTemp[j] != '0') {
					chrVecCopy.push_back(chrVecTemp[j]);
				}
			}
			vecAfterRemoveZero.push_back(chrVecCopy);
		}
	}
};
void getLess(std::vector<char>& minChrVec, std::vector<char>& testChrVec) {
	int digitCountA = minChrVec.size();
	int digitCountB = testChrVec.size();
	if (digitCountA < digitCountB) {
		minChrVec = testChrVec;
		return;
	}
	else if (digitCountA > digitCountB) {
		return;
	}
	else {
		for (int i = 0;i < digitCountA;i++) {
			if (minChrVec[i] < testChrVec[i]) {
				minChrVec = testChrVec;
				return;
			}
			else if (minChrVec[i] > testChrVec[i]) {
				return;
			}
		}
	}
};
int main() {
	int playerCount = 0;
	std::vector<std::string> numVec = {};
	std::cin >> playerCount;
	for (int i = 0;i < playerCount;i++) {
		std::string str = "";
		std::cin >> str;
		numVec.push_back(str);
	}

	//char数组排序
	std::vector<std::vector<char>> vecAfterSort = {};
	getVecAfterSort(vecAfterSort, numVec);

	//char 数组 remove 0
	std::vector<std::vector<char>> vecAfterRemoveZero = {};
	getVecRemoveZero(vecAfterRemoveZero, vecAfterSort);

	//getResult
	std::vector<char> minChrVec = {};
	for (int i = 0;i < vecAfterRemoveZero.size();i++) {
		if (i == 0) {
			minChrVec = vecAfterRemoveZero[i];
		}
		else {
			getLess(minChrVec, vecAfterRemoveZero[i]);
		}

	}
	for (int i = 0;i < minChrVec.size();i++) {
		std::cout << minChrVec[i];
	}
	return 0;
}