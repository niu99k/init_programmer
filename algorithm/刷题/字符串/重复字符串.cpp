/*
��Ŀ����
ţţ��һ����Сд��ĸ��ɵ��ַ���s,��s�п�����һЩ��ĸ�ظ����֡�������"banana"��,��ĸ'a'����ĸ'n'�ֱ���������κ����Ρ�
����ţţ��ϲ���ظ�������ͬһ����ĸ,��ֻ�뱣����һ�γ��ֲ�ɾ����������ֵ���ĸ�������ţţ��ɶ�s�Ĳ�����
��������:
�������һ���ַ���s,s�ĳ���length(1 �� length �� 1000),s�е�ÿ���ַ�����Сд��Ӣ����ĸ('a' - 'z')
�������:
���һ���ַ���,��ʾ����ţţҪ����ַ���
ʾ��1
����
����
banana
���
����
ban
*/
#include<iostream>
#include<map>
#include<vector>
#include<string>
int main() {
	std::string str = "";
	std::string result = "";
	std::cin >> str;
	std::map<std::string, int> testRepetition;
	for (int i = 0;i < str.size();i++) {
		std::string chr = str.substr(i, 1);
		if (testRepetition[chr] == 1) {
			continue;
		}
		else {
			testRepetition[chr] = 1;
			result += (chr);
		}
	}
	std::cout << result;
}