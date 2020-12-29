/*
��Ŀ����
ţţ��һЩ�ų�һ�е������Ρ�ÿ���������Ѿ���Ⱦ�ɺ�ɫ������ɫ��ţţ���ڿ���ѡ������һ��������Ȼ������������ɫ������һ�ֽ���Ⱦɫ,��������ε���ɫ���ᱻ���ǡ�ţţ��Ŀ���������Ⱦɫ֮��,ÿ����ɫR����ÿ����ɫG������������ţţ��֪����������ҪͿȾ���������Ρ�
��������ʾ: s = RGRGR
����ͿȾ֮����RRRGG����Ҫ����,ͿȾ�ĸ���Ϊ2,û�б�������õ�ͿȾ������
��������:
�������һ���ַ���s,�ַ���s����length(1 �� length �� 50),����ֻ����'R'����'G',�ֱ��ʾ��ɫ����ɫ��
�������:
���һ������,��ʾţţ������ҪͿȾ������������*/

#include<iostream>
#include<map>
#include<vector>


int main() {
	std::string str = "";
	std::cin >> str;
	int minPaintCount = 0;
	std::map<int, int > dpMap = {};
	dpMap[1] = 0;
	for (int i = 1;i < str.size();i++) {
		if (str.substr(i, 1) == "R") {
			int change = dpMap[i] + 1;
			int notChange = 0;
			for (int j = 0;j < i;j++) {
				if (str.substr(j, 1) == "G") {
					notChange++;
				}
			}
			if (change > notChange) {
				dpMap[i + 1] = notChange;
			}
			else {
				dpMap[i + 1] = dpMap[i] + 1;
			}
		}
		else {
			dpMap[i + 1] = dpMap[i];
		}
	}
	std::cout << dpMap[str.size()];
	return 0;
}
