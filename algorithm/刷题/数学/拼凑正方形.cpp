/*��Ŀ����
ţţ��4��ľ��,���ȷֱ�Ϊa,b,c,d��������ṩ�ı�ľ�����ȵķ���,���ţţ֧��һ��Ӳ�ҾͿ�����һ��ľ���ĳ��ȼ�һ���߼�һ��ţţ��Ҫ�����ĸ�ľ��ƴ��һ�������γ���,ţţ������Ҫ֧������Ӳ�Ҳ��������ĸ�ľ��ƴ�ճ������Ρ�
��������:
�������һ��,�ĸ�����a,b,c,d(1 �� a,b,c,d �� 10^6), �Կո�ָ�
�������:
���һ������,��ʾţţ������Ҫ֧����Ӳ��
ʾ��1
����
����
4 1 5 4
���
����
4*/
#include<iostream>
#include<map>
#include<vector>

#define INT_MAX 0x7ffffff
#define INT_MIN 0x8000000
#define NUMBER_COUNT 4
int main() {
	std::vector<int> stickLengthVec = {};
	for (int i = 0;i < NUMBER_COUNT;i++) {
		int temp = 0;
		std::cin >> temp;
		stickLengthVec.push_back(temp);
	}
	int minMove = INT_MAX;

	for (int i = 0;i < stickLengthVec.size();i++) {
		int countTest = 0;
		for (int j = 0;j < stickLengthVec.size();j++) {
			countTest += std::abs(stickLengthVec[i] - stickLengthVec[j]);
		}
		if (countTest < minMove) {
			minMove = countTest;
		}
	}
	std::cout << minMove;

	return 0;
}