#include<iostream>
#include<vector>
#include<map>
/*
题目描述
牛牛现在有n张扑克牌，每张扑克牌都有点数和花色两部分组成。点数为‘1’-‘9’的正整数，花色为'C','D','H','S''其中的一个，分别表示梅花、方块、红桃、黑桃。现在牛牛想按一定的顺序把这n张牌扔掉。扔牌顺序的规则如下1.：
1.如果现在还剩素数张牌，则将牌顶的牌扔掉
2.如果现在还剩非素数张牌，则将牌底的牌扔掉
牛牛想知道他的扔牌顺序是什么，请返回扔牌顺序的字符串

示例1
输入
复制
"3C8D6H3D"
输出
复制
"3D3C8D6H"
说明
开始n=4，为非素数，扔掉牌底的牌3D
n=3，为素数，扔掉牌顶的牌3C
n=2，为素数，扔掉牌顶的牌8D
n=1，为非素数，扔掉牌底的牌6H
示例2
输入
复制
"8S8S8S8S8S8S8S"
输出
复制
"8S8S8S8S8S8S8S"
说明
因为全是8S，所以扔牌顺序的每一张牌也都是8S
备注:
对于100\%的数据，1\leq n\leq 10对于100%的数据，1≤n≤10
*/
std::vector<std::string> initString(std::string inputStr) {
	std::vector<std::string> initStrVec = {};
	while (inputStr != "") {
		std::string temp = inputStr.substr(0, 2);
		initStrVec.push_back(temp);
		inputStr = inputStr.substr(2, inputStr.size() - 2);
	}
	return initStrVec;
}
bool isss(int num) {
	for (int i = 2;i < num;i++) {
		if (i * i > num) {
			return false;
		}
		if (num % i == 0) {
			return true;
		}
	}
	return false;
}
void initssVector(std::vector<int> & initss, int n) {
	for (int i = 1;i <= n;i++) {
		if (isss(i)) {
			initss[i] = 1;
		}
	}
}

std::string Orderofpoker(std::string x) {
	int n = 0;
	std::string result = "";
	//初始化扑克牌相关数组 string[]
	std::vector<std::string> initStrVector = initString(x);
	n = initStrVector.size();
	std::vector<int> initss = {};
	for (int i = 0;i <= n;i++) {
		initss.push_back(-1);
	}
	initssVector(initss, n);
	std::map<int, int> order = {};
	for (int i = 1;i <= n;i++) {
		if (initss[i] == 1) {
			//扔前牌
			order[i] = 0;
		}
		else {
			//扔尾牌
			order[i] = 1;
		}
	}
	int frontFlag = 0;
	int postFlag = n - 1;
	while (frontFlag != postFlag) {
		if (order[postFlag - frontFlag + 1] == 1) {
			result += initStrVector[frontFlag];
			frontFlag++;
		}
		else {
			result += initStrVector[postFlag];
			postFlag--;
		}
	}
	result += initStrVector[postFlag];
	return result;
}
int main() {
	std::cout << Orderofpoker("3C8D6H3D");
	return 0;
}
