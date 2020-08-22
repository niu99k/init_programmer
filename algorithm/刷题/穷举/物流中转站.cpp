/*
题目描述
Shopee物流会有很多个中转站。在选址的过程中，会选择离用户最近的地方建一个物流中转站。

假设给你一个二维平面网格，每个格子是房子则为1，或者是空地则为0。找到一个空地修建一个物流中转站，使得这个物流中转站到所有的房子的距离之和最小。 能修建，则返回最小的距离和。如果无法修建，则返回 -1。


若范围限制在100*100以内的网格，如何计算出最小的距离和？

当平面网格非常大的情况下，如何避免不必要的计算？

输入描述:
4
0 1 1 0
1 1 0 1
0 0 1 0
0 0 0 0

先输入方阵阶数，然后逐行输入房子和空地的数据，以空格分隔。
输出描述:
8

能修建，则返回最小的距离和。如果无法修建，则返回 -1。
*/
#include<iostream>
#include<vector>
#include<cmath>

#define INT_MAX 0x7fffffff
#define INT_MIN 0x80000000
int main() {
	int map[100][100];
	int mapSize = -1;
	std::cin >> mapSize;
	if (mapSize <= 0) {
		std::cout << "-1";
		return 0;
	}
	bool noEmptyFlag = true;
	for (int i = 0;i < mapSize;i++) {
		for (int j = 0;j < mapSize;j++) {
			int temp = -1;
			std::cin >> temp;
			if (temp == 0) {
				noEmptyFlag = false;
			}
			map[i][j] = temp;
		}
	}
	if (noEmptyFlag) {
		std::cout << "-1";
		return 0;
	}
	//开始brute force
	int minDis = INT_MAX;
	for (int i = 0;i < mapSize;i++) {
		for (int j = 0;j < mapSize;j++) {
			if (map[i][j] == 0) {
				int dis = 0;
				for (int i2 = 0;i2 < mapSize;i2++) {
					for (int j2 = 0;j2 < mapSize;j2++) {
						if (map[i2][j2] == 1) {
							dis += (std::abs(i2 - i) + std::abs(j2 - j));
						}
					}
				}
				if (dis < minDis) {
					minDis = dis;
				}
			}
		}
	}
	std::cout << minDis;
	return 0;
}
