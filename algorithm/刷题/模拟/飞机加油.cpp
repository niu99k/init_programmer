/*
��Ŀ����
ţţ�������������������Լ��ķɻ����������ǽ��м��������ķ��С�f[i]��ʾ��i�η��������ȼ�͵�����������ֻ�ܰ���f������������˳����С�
����ɻ�����s��ȼ��,Ϊ����������,ÿ�η���ǰ�ɻ���ȼ����Ӧ���ڵ��ڴ˴���������Ҫ��ȼ��������������Ǽ����ڲ����м��͵�����������ܽ��еķ��д�����
��������:
�����������,��һ�а�����������n��s(1 �� n �� 50, 1 �� s �� 1000),�ֱ��ʾ�ƻ����еĴ����ͷ����ʼ״̬���е�ȼ������
�ڶ��а���n������f[i], (1 �� f[i] �� 1000), ��ʾÿ�μƻ���������Ҫ��ȼ������
�������:
���һ������,��ʾ�����ܽ��еķ��д�����
ʾ��1
����
����
7 10
1 2 3 4 5 6 7
���
����
4
*/
#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
int main() {
	int flyCount = 7;
	int initFuelTotal = 10;
	std::vector<int> fuelArrays = {};
	std::cin >> flyCount;
	std::cin >> initFuelTotal;
	for (int i = 0;i < flyCount;i++) {
		int temp = 0;
		std::cin >> temp;
		fuelArrays.push_back(temp);
	}
	//std::sort(fuelArrays.begin(), fuelArrays.end(), std::less<int>());

	int testCount = 0;
	bool isAllPassed = true;
	for (int i = 0;i < fuelArrays.size();i++) {
		testCount += fuelArrays[i];
		if (testCount > initFuelTotal) {
			std::cout << i;
			isAllPassed = false;
			break;
		}
	}
	if (isAllPassed) {
		std::cout << flyCount;
	}
	return 0;
}