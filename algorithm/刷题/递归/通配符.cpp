/*
题目描述
在Linux Shell命令下通配符'*'表示0个或多个字符, 现编写一段代码实现通配符'*'的功能，注意只需要实现'*', 不用实现其他通配符。
输入描述:
第一行输入通配字符串
第二行输入要匹配查找的字符串
输出描述:
输出所有匹配的字串起始位置和长度，每行一个匹配输出
如果不匹配，则输出 -1 0
如果有多个按照起始位置和长度的正序输出。
*/
#include<iostream>
#include<vector>
#include<cmath>

#define MAX_INT 0x7fffffff
#define MIN_INT 0x80000000

void search(std::string& str, std::string& regular, std::vector<int>& result, int strBeginIndex, int regularBeginIndex) {
	if (regularBeginIndex == regular.size()) {
		result.push_back(strBeginIndex);
	}
	else if (strBeginIndex == str.size()) {
		if (regular[regularBeginIndex] == '*' && regularBeginIndex == regular.size() - 1) {
			result.push_back(strBeginIndex);
			return;
		}
		return;
	}
	else if (str[strBeginIndex] == regular[regularBeginIndex]) {
		search(str, regular, result, strBeginIndex + 1, regularBeginIndex + 1);
	}
	else if (regular[regularBeginIndex] == '*') {
		search(str, regular, result, strBeginIndex, regularBeginIndex + 1);
		search(str, regular, result, strBeginIndex + 1, regularBeginIndex);
	}
	return;

}
int main() {
	std::string regular = "";
	std::string str = "";
	std::cin >> regular >> str;
	bool existFlag = false;
	for (int i = 0;i < str.size();i++) {
		std::vector<int> result = {};
		search(str, regular, result, i, 0);
		if (result.size() != 0) {
			for (auto& e : result) {
				existFlag = true;
				if (e - i > 0)
					std::cout << i << " " << e - i << "\n";
			}
		}
	}
	if (!existFlag) {
		std::cout << "-1 0";
	}
	return 0;
}
