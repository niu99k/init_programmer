/*
��Ŀ����
���һ���ַ���S���������ַ���T���Ӷ���,��S = T + T, ���Ǿͳ�S����ƽ����,����"","aabaab","xxxx"����ƽ����.
ţţ������һ���ַ���s,�������ţţ��s���Ƴ������ٵ��ַ�,��ʣ�µ��ַ�����һ��ƽ���������仰˵,�����ҳ�s��������в�����������й���һ��ƽ������
��������:
����һ���ַ���s,�ַ�������length(1 �� length �� 50),�ַ���ֻ����Сд�ַ���
�������:
���һ��������,������Ҫ���ƽ�����ĳ��ȡ�
ʾ��1
����
����
frankfurt
���
����
4
*/

#include<iostream>
#include<vector>
#include<map>
#include<algorithm>
using namespace std;
void getMaxCount(int& count, string& str1, string& str2) {
	map<int, map<int, int>> dpMap = {};
	for (int i = 0;i < str1.size();i++) {
		for (int j = 0;j < str2.size();j++) {
			if (str1[i] == str2[j]) {
				dpMap[i][j] = dpMap[i - 1][j - 1] + 1;
			}
			else {
				dpMap[i][j] = max(dpMap[i - 1][j], dpMap[i][j - 1]);
			}
		}
	}
	count = dpMap[str1.size() - 1][str2.size() - 1];
}

int main() {
	string str = "";
	cin >> str;
	int maxCount = 0;
	for (int i = 1;i < str.size();i++) {
		string str1 = str.substr(0, i);
		string str2 = str.substr(i, str.size() - i);
		int count = 0;
		getMaxCount(count, str1, str2);
		if (count > maxCount) {
			maxCount = count;
		}
	}
	cout << maxCount * 2;
	return 0;
}