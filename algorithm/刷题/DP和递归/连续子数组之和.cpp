/*
题目描述
输入一个整形数组（可能有正数和负数），求数组中连续子数组（最少有一个元素）的最大和。要求时间复杂度为O(n)。

输入描述:
【重要】第一行为数组的长度N（N>=1）

接下来N行，每行一个数，代表数组的N个元素
输出描述:
最大和的结果
*/
#include<iostream>
#include<vector>
#include<limits.h>
int main() {
	int arrayLength = 0;
	std::vector<int> vec = {};
	std::vector<int> record = {};
	std::vector<int> result = {};
	std::cin >> arrayLength;
	for (int i = 0;i < arrayLength;i++) {
		int temp = -1;
		std::cin >> temp;
		vec.push_back(temp);
		record.push_back(0);
		result.push_back(0);
	}
	for (int i = 0;i < arrayLength;i++) {
		if (i == 0) {
			record[i] = vec[i];
			result[i] = vec[i];
		}
		else {
			int unchoosed = record[i - 1];
			int choosed = record[i - 1] > 0 ? vec[i] + record[i - 1] : vec[i];
			if (unchoosed > choosed) {
				result[i] = unchoosed;
			}
			else {
				result[i] = choosed;
			}
			record[i] = choosed;
		}
	}
	int max = INT_MIN;
	for (int i = 0;i < arrayLength;i++) {
		if (result[i] > max) {
			max = result[i];
		}
	}
	std::cout << max;
	return 0;
}
