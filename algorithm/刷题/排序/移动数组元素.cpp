/*��Ŀ����
ţţ��һ������Ϊn����������,ţţ���������н�������Ϊһ�����ϸ��������С�ţţ�Ƚ�����,�����ƶ������ٵ������������,�����������һ����������Ҫ�ƶ����ٸ������е�Ԫ�ء�(��һ��Ԫ�ز�����ԭ�����ڵ�λ��,���Ԫ�ؾ��Ǳ��ƶ��˵�)
��������:
�����������,��һ��һ������n(1 �� n �� 50),�����еĳ���
�ڶ���n������x[i](1 �� x[i] �� 100),�������е�ÿ����
�������:
���һ������,��������Ҫ�ƶ���Ԫ�ظ���
ʾ��1
����
����
3
3 2 1
���
����
2*/

#include<iostream>
#include<map>
#include<vector>
#include<algorithm>

int main() {
	int arrayCount = 3;
	std::vector<int> numVec = {};
	std::cin >> arrayCount;
	for (int i = 0;i < arrayCount;i++) {
		int temp = 0;
		std::cin >> temp;
		numVec.push_back(temp);
	}

	std::vector<int> numVecWithSort = {};
	for (int i = 0;i < arrayCount;i++) {
		numVecWithSort.push_back(numVec[i]);
	}
	std::sort(numVecWithSort.begin(), numVecWithSort.end(), std::less<int>());

	int count = 0;
	for (int i = 0;i < arrayCount;i++) {
		if (numVec[i] != numVecWithSort[i]) {
			count++;
		}
	}
	std::cout << count;
	return 0;
}