/*
题目描述
对于任意两个正整数x和k,我们定义repeat(x, k)为将x重复写k次形成的数,例如repeat(1234, 3) = 123412341234,repeat(20,2) = 2020.
牛牛现在给出4个整数x1, k1, x2, k2, 其中v1 = (x1, k1), v2 = (x2, k2),请你来比较v1和v2的大小。
输入描述:
输入包括一行,一行中有4个正整数x1, k1, x2, k2(1 ≤ x1,x2 ≤ 10^9, 1 ≤ k1,k2 ≤ 50),以空格分割
输出描述:
如果v1小于v2输出"Less",v1等于v2输出"Equal",v1大于v2输出"Greater".
示例1
输入
复制
1010 3 101010 2
输出
复制
Equal
*/
#include<iostream>
#include<map>
#include<vector>
void getNum(std::string& num, std::string base, int multiple) {
	for (int i = 0;i < multiple;i++) {
		num += base;
	}
};
void getDigit(int& digit, std::string num) {
	digit = num.size();
};
void compareSameDigit(std::string& result, std::string num1, std::string num2) {
	for (int i = 0;i < num1.size();i++) {
		char test1 = num1[i];
		char test2 = num2[i];
		if (test1 > test2) {
			result = "Greater";
			return;
		}
		else if (test1 < test2) {
			result = "Less";
			return;

		}
	}
	result = "Equal";
};
int main() {
	std::string base1 = "1010";
	int multiple1 = 3;
	std::string base2 = "101010";
	int multiple2 = 2;

	std::cin >> base1;
	std::cin >> multiple1;
	std::cin >> base2;
	std::cin >> multiple2;

	std::string num1 = "";
	std::string num2 = "";
	getNum(num1, base1, multiple1);
	getNum(num2, base2, multiple2);

	//比较位数
	int digit1 = 0;
	int digit2 = 0;
	getDigit(digit1, num1);
	getDigit(digit2, num2);
	if (digit1 > digit2) {
		std::cout << "Greater";
		return 0;
	}
	else if (digit1 < digit2) {
		std::cout << "Less";
		return 0;
	}
	else {
		//从头开始遍历比较大小
		std::string result = "";
		compareSameDigit(result, num1, num2);
		std::cout << result;
	}
}