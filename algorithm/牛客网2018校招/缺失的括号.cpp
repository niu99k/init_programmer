/*
��Ŀ����
һ�������������ַ��������������:
1�����ַ����������ġ�
2�����s���������ַ�������ô(s)Ҳ�������ġ�
3�����s��t���������ַ��������������������γɵ�stҲ�������ġ�
���磬"(()())", ""��"(())()"�������������ַ�����"())(", "()(" �� ")"�ǲ������������ַ�����
ţţ��һ�������ַ���s,������Ҫ����������λ�þ����ٵ��������,����ת��Ϊһ�������������ַ���������ţţ������Ҫ��Ӷ��ٸ����š�
��������:
�������һ��,һ����������s,���г���length(1 �� length �� 50).
s��ÿ���ַ����������Ż���������,��'('����')'.
�������:
���һ������,��ʾ������Ҫ��ӵ�������
ʾ��1
����
����
(()(()
���
����
2
*/
#include<iostream>
#include<vector>
#include<map>
#include<stack>
#include<algorithm>
#include<string>

int main() {
	using namespace std;
	string s = "";
	cin >> s;
	std::stack<string> stack;
	int count = 0;
	for (int i = 0;i < s.size();i++) {
		string str = s.substr(i, 1);
		if (str != "(" && str != ")") {
			continue;
		}
		else if (str == "(") {
			stack.push(str);
		}
		else if (!stack.empty()) {
			stack.pop();
		}
		else {
			count++;
		}
	}
		count += stack.size();
	std::cout << count;
	return 0;
}