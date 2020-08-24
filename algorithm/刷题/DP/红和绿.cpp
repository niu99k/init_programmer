/*
题目描述
牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。牛牛想知道他最少需要涂染几个正方形。
如样例所示: s = RGRGR
我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
输入描述:
输入包括一个字符串s,字符串s长度length(1 ≤ length ≤ 50),其中只包括'R'或者'G',分别表示红色和绿色。
输出描述:
输出一个整数,表示牛牛最少需要涂染的正方形数量*/

#include<iostream>
#include<map>
#include<vector>


int main() {
	std::string str = "";
	std::cin >> str;
	int minPaintCount = 0;
	std::map<int, int > dpMap = {};
	dpMap[1] = 0;
	for (int i = 1;i < str.size();i++) {
		if (str.substr(i, 1) == "R") {
			int change = dpMap[i] + 1;
			int notChange = 0;
			for (int j = 0;j < i;j++) {
				if (str.substr(j, 1) == "G") {
					notChange++;
				}
			}
			if (change > notChange) {
				dpMap[i + 1] = notChange;
			}
			else {
				dpMap[i + 1] = dpMap[i] + 1;
			}
		}
		else {
			dpMap[i + 1] = dpMap[i];
		}
	}
	std::cout << dpMap[str.size()];
	return 0;
}
