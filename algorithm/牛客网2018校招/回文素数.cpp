/*题目描述
如果一个整数只能被1和自己整除,就称这个数是素数。
如果一个数正着反着都是一样,就称为这个数是回文数。例如:6, 66, 606, 6666
如果一个数字既是素数也是回文数,就称这个数是回文素数
牛牛现在给定一个区间[L, R],希望你能求出在这个区间内有多少个回文素数。
输入描述:
输入包括一行,一行中有两个整数(1 ≤ L ≤ R ≤ 1000)
输出描述:
输出一个整数,表示区间内回文素数个数。
示例1
输入
复制
100 150
输出
复制
2*/
#include<iostream>
#include<map>
#include<vector>
#include<string>

void isPrimeFunc(bool& isPrime, int& num) {
	if (num == 1) {
		isPrime = false;
	}
	for (int i = 2;i * i <= num;i++) {
		if (num % i == 0) {
			isPrime = false;
			break;
		}
	}
}
void isHuiwenPrimeFunc(bool& isHuiwenPrime, int& num) {
	std::string numToString = std::to_string(num);
	int endIndex = numToString.size() - 1;
	int beginIndex = 0;
	while (endIndex > beginIndex) {
		if (numToString[beginIndex] != numToString[endIndex]) {
			isHuiwenPrime = false;
			break;
		}
		beginIndex++;
		endIndex--;
	}
};
int main() {
	int left = 100;
	int right = 150;
	std::cin >> left;
	std::cin >> right;

	int count = 0;
	for (int i = left;i <= right;i++) {
		bool isPrime = true;
		bool isHuiwenPrime = true;

		isPrimeFunc(isPrime, i);
		isHuiwenPrimeFunc(isHuiwenPrime, i);

		if (isPrime && isHuiwenPrime) {
			count++;
		}
	}
	std::cout << count;
	return 0;
}