/*
��Ŀ����
����һ���������飨�����������͸������������������������飨������һ��Ԫ�أ������͡�Ҫ��ʱ�临�Ӷ�ΪO(n)��

��������:
����Ҫ����һ��Ϊ����ĳ���N��N>=1��

������N�У�ÿ��һ���������������N��Ԫ��
�������:
���͵Ľ��
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
