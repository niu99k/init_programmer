/*
��Ŀ����
��Linux Shell������ͨ���'*'��ʾ0�������ַ�, �ֱ�дһ�δ���ʵ��ͨ���'*'�Ĺ��ܣ�ע��ֻ��Ҫʵ��'*', ����ʵ������ͨ�����
��������:
��һ������ͨ���ַ���
�ڶ�������Ҫƥ����ҵ��ַ���
�������:
�������ƥ����ִ���ʼλ�úͳ��ȣ�ÿ��һ��ƥ�����
�����ƥ�䣬����� -1 0
����ж��������ʼλ�úͳ��ȵ����������
*/
#include<iostream>
#include<vector>
#include<cmath>

#define MAX_INT 0x7fffffff
#define MIN_INT 0x80000000

void search(std::string& str, std::string& regular, std::vector<int>& result, int strBeginIndex, int regularBeginIndex) {
	if (regularBeginIndex == regular.size()) {
		result.push_back(strBeginIndex);
	}
	else if (strBeginIndex == str.size()) {
		if (regular[regularBeginIndex] == '*' && regularBeginIndex == regular.size() - 1) {
			result.push_back(strBeginIndex);
			return;
		}
		return;
	}
	else if (str[strBeginIndex] == regular[regularBeginIndex]) {
		search(str, regular, result, strBeginIndex + 1, regularBeginIndex + 1);
	}
	else if (regular[regularBeginIndex] == '*') {
		search(str, regular, result, strBeginIndex, regularBeginIndex + 1);
		search(str, regular, result, strBeginIndex + 1, regularBeginIndex);
	}
	return;

}
int main() {
	std::string regular = "";
	std::string str = "";
	std::cin >> regular >> str;
	bool existFlag = false;
	for (int i = 0;i < str.size();i++) {
		std::vector<int> result = {};
		search(str, regular, result, i, 0);
		if (result.size() != 0) {
			for (auto& e : result) {
				existFlag = true;
				if (e - i > 0)
					std::cout << i << " " << e - i << "\n";
			}
		}
	}
	if (!existFlag) {
		std::cout << "-1 0";
	}
	return 0;
}
