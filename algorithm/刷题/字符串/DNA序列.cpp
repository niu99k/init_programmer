/*
��Ŀ����
ţţ�ִ�������й�����������һ������,���ţţ��Ҫ�������й����ߴ�DNA����s���ҳ����û�г�����DNA����s�е�DNAƬ�εĳ��ȡ�
����:s = AGGTCTA
�����а��������г���Ϊ1��('A','C','G','T')Ƭ��,���ǳ���Ϊ2��û��ȫ������,���������в�����"AA",�������2��
��������:
�������һ���ַ���s,�ַ�������length(1 �� length �� 2000),����ֻ����'A','C','G','T'�������ַ���
�������:
���һ��������,�����û�г�����DNA����s�е�DNAƬ�εĳ��ȡ�
ʾ��1
����
����
AGGTCTA
���
����
2
*/
#include<iostream>
#include<map>
#include<vector>
#define DNA_CATEGORY 4

void getRealCount(int& realCount, int& countIndex, std::string& str) {
	std::map<std::string, int> testCountMap = {};
	for (int i = 0;i < str.size() + 1 - countIndex;i++) {
		std::string oneRealstr = str.substr(i, countIndex);
		testCountMap[oneRealstr] = 1;
	}
	realCount = testCountMap.size();
}
void getMaxCount(int& maxCount, int& countIndex, int categoryCount) {
	maxCount = 1;
	for (int i = 0;i < countIndex;i++) {
		maxCount *= categoryCount;
	}
}
int main() {
	std::string str = "AGGTCTA";
	std::cin >> str;
	for (int i = 1;i <= str.size();i++) {
		int realCount = 0;
		getRealCount(realCount, i, str);
		int maxCount = 0;
		getMaxCount(maxCount, i, DNA_CATEGORY);
		if (realCount < maxCount) {
			std::cout << i;
			return 0;
		}
	}
	//��������� ���ַ�������
	return 0;
}