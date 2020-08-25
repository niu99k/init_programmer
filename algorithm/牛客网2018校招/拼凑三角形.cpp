/*题目描述
牛牛手中有三根木棍,长度分别是a,b,c。牛牛可以把任一一根木棍长度削短,牛牛的目标是让这三根木棍构成一个三角形,并且牛牛还希望这个三角形的周长越大越好。
输入描述:
输入包括一行,一行中有正整数a, b, c(1 ≤ a, b, c ≤ 100), 以空格分割
输出描述:
输出一个整数,表示能拼凑出的周长最大的三角形。
示例1
输入
复制
1 2 3
输出
复制
5*/
#include<iostream>
#include<map>
#include<vector>

#define EDGE_COUNT 3
int main() {
	std::vector<int> edgeVec = {};
	for (int i = 0;i < EDGE_COUNT;i++) {
		int temp = 0;
		std::cin >> temp;
		edgeVec.push_back(temp);
	}

	//找到最大的边
	int max = 0;
	for (auto& e : edgeVec) {
		if (e > max) {
			max = e;
		}
	}

	//削到刚刚好
	int maxAccept = 0;
	bool isPass = false;
	for (auto& e : edgeVec) {
		if (e == max && !isPass) {
			isPass = true;
			continue;
		}
		maxAccept += e;
	}

	//相加
	int perimeter = 0;
	if (max < maxAccept) {
		perimeter += max;
		perimeter += maxAccept;
	}
	else {
		perimeter += maxAccept;
		perimeter += maxAccept - 1;
	}
	std::cout << perimeter;
	return 0;
}