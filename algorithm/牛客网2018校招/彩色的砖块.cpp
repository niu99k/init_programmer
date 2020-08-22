/*小易有一些彩色的砖块。每种颜色由一个大写字母表示。各个颜色砖块看起来都完全一样。现在有一个给定的字符串s,s中每个字符代表小易的某个砖块的颜色。小易想把他所有的砖块排成一行。如果最多存在一对不同颜色的相邻砖块,那么这行砖块就很漂亮的。请你帮助小易计算有多少种方式将他所有砖块排成漂亮的一行。(如果两种方式所对应的砖块颜色序列是相同的,那么认为这两种方式是一样的。)
例如: s = "ABAB",那么小易有六种排列的结果:
"AABB","ABAB","ABBA","BAAB","BABA","BBAA"
其中只有"AABB"和"BBAA"满足最多只有一对不同颜色的相邻砖块。*/
#include<iostream>
#include <map>

int main() {
	std::string str = "";
	std::cin >> str;
	std::map<char, int> temp = {};
	for (int i = 0;i < str.size();i++) {
		temp[str[i]] = 0;
	}
	int cate = temp.size();
	if (cate == 1) {
		std::cout << 1;
	}
	else if (cate == 2) {
		std::cout << 2;
	}
	else {
		std::cout << 0;
	}
	return 0;
}