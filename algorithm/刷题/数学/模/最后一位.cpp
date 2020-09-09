/*
��Ŀ����
ţţѡ����һ��������X,Ȼ�����д�ںڰ��ϡ�Ȼ��ÿһ�����������ǰ���ֵ����һλ,ֱ��������������λ�� ������������,ţţ��������ںڰ��ϳ��ֹ������ּ�¼����,Ȼ��������ǵ��ܺ�sum.
����X = 509, �ںڰ��ϳ��ֹ�������������509, 50, 5, ���ǵĺ;���564.
ţţ���ڸ���һ��sum,ţţ���������һ��������X�����������̵Ľ����sum.
��������:
�������������sum(1 �� sum �� 10^18)
�������:
���һ��������,������������X,���û��������X,���-1��
ʾ��1
����
����
564
���
����
509*/

#include<iostream>
#include<vector>
#include<map>
#include<string>

int main() {
	using namespace std;
	long long num = 0;
	std::cin >> num;
	long long originalResult = num;
	string numToStr = to_string(num);
	int digit = numToStr.size();
	long long temp = 1;
	long long firstDivide = 0;
	for (int i = 0;i < digit;i++) {
		firstDivide += temp;
		temp *= 10;
	}
	vector<int> resultVec;
	while (firstDivide > 0) {
		int temp4Result = num / firstDivide;
		resultVec.push_back(temp4Result);
		num -= temp4Result * firstDivide;
		firstDivide /= 10;
	}

	long long testResult = 0;
	long long xishu = 1;
	for (int i = resultVec.size() - 1;i >= 0;i--) {
		testResult += xishu * resultVec[i];
		xishu *= 10;
	}
	long long expectedResult = 0;
	while (testResult > 0) {
		expectedResult += testResult;
		testResult /= 10;
	}

	if (expectedResult == originalResult) {
		for (auto& e : resultVec) {
			std::cout << e;
		}
	}
	else {
		std::cout << -1;
	}
	return 0;
}