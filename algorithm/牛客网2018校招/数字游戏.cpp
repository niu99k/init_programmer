/*
��Ŀ����
ţţ�ٰ���һ��������Ϸ,��n����Ҳμ������Ϸ,��Ϸ��ʼÿ�����ѡ��һ����,Ȼ�������д��ֽ��(ʮ������,��ǰ׺��),Ȼ�����������ÿһ�����ֽ�����λ���շǵݼ�˳������,�õ��µ���,������ǰ׺�㽫�����ԡ��õ�������ֵ����Ӯ�������Ϸ��
��������:
�����������,��һ�а���һ������n(1 �� n �� 50),����ҵ�����
�ڶ���n������x[i](0 �� x[i] �� 100000),��ÿ�����д�µ�������
�������:
���һ������,��ʾӮ����Ϸ���Ǹ���һ�õ���������Ƕ��١�
ʾ��1
����
����
3
9638 8210 331
���
����
3689*/
#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
void getVecAfterSort(std::vector<std::vector<char>>& vecAfterSort, std::vector<std::string>& numVec) {
	for (int i = 0;i < numVec.size();i++) {
		std::string strTemp = numVec[i];
		std::vector<char> chrVec = {};
		for (int j = 0;j < strTemp.size();j++) {
			chrVec.push_back(strTemp[j]);
		}
		std::sort(chrVec.begin(), chrVec.end(), std::less<int>());
		vecAfterSort.push_back(chrVec);
	}
};
void getVecRemoveZero(std::vector<std::vector<char>>& vecAfterRemoveZero, std::vector<std::vector<char>>& vecAfterSort) {
	for (int i = 0;i < vecAfterSort.size();i++) {
		std::vector<char> chrVecTemp = vecAfterSort[i];
		if (chrVecTemp[0] != '0') {
			vecAfterRemoveZero.push_back(chrVecTemp);
		}
		else {
			std::vector<char>  chrVecCopy = {};
			for (int j = 0;j < chrVecTemp.size();j++) {
				if (chrVecTemp[j] != '0') {
					chrVecCopy.push_back(chrVecTemp[j]);
				}
			}
			vecAfterRemoveZero.push_back(chrVecCopy);
		}
	}
};
void getLess(std::vector<char>& minChrVec, std::vector<char>& testChrVec) {
	int digitCountA = minChrVec.size();
	int digitCountB = testChrVec.size();
	if (digitCountA < digitCountB) {
		minChrVec = testChrVec;
		return;
	}
	else if (digitCountA > digitCountB) {
		return;
	}
	else {
		for (int i = 0;i < digitCountA;i++) {
			if (minChrVec[i] < testChrVec[i]) {
				minChrVec = testChrVec;
				return;
			}
			else if (minChrVec[i] > testChrVec[i]) {
				return;
			}
		}
	}
};
int main() {
	int playerCount = 0;
	std::vector<std::string> numVec = {};
	std::cin >> playerCount;
	for (int i = 0;i < playerCount;i++) {
		std::string str = "";
		std::cin >> str;
		numVec.push_back(str);
	}

	//char��������
	std::vector<std::vector<char>> vecAfterSort = {};
	getVecAfterSort(vecAfterSort, numVec);

	//char ���� remove 0
	std::vector<std::vector<char>> vecAfterRemoveZero = {};
	getVecRemoveZero(vecAfterRemoveZero, vecAfterSort);

	//getResult
	std::vector<char> minChrVec = {};
	for (int i = 0;i < vecAfterRemoveZero.size();i++) {
		if (i == 0) {
			minChrVec = vecAfterRemoveZero[i];
		}
		else {
			getLess(minChrVec, vecAfterRemoveZero[i]);
		}

	}
	for (int i = 0;i < minChrVec.size();i++) {
		std::cout << minChrVec[i];
	}
	return 0;
}