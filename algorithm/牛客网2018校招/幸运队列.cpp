/*
��Ŀ����
ţţ�õ�һ������Ϊn����������V,ţţ����һ�����������е�����ֵΪ��������������ֵ�ʹδ�ֵ�����ֵ(�δ�ֵ���ϸ�Ĵδ�)��ţţ������Ҫ�������V����������������������ֵ����Ƕ��١�������ţţ�ɡ�
��������:
��һ��һ������n,�����еĳ��ȡ�(2<= n <= 100000)
�ڶ���n���������α�ʾ�������ÿ����ֵV[i], (1 �� V[i] �� 10^8)�ұ�֤V[1]��V[n]�����ٴ��ڲ�ͬ������ֵ.
�������:
���һ������,����������ֵ
ʾ��1
����
����
5
5 2 1 4 3
���
����
7
*/
#include<iostream>
#include<map>
#include<vector>
#include<stack>
#include<algorithm>
int main() {
	//���õ���ջ
	int numCount = 0;
	std::cin >> numCount;
	std::stack<int> numStack;
	//����Ԫ��Υ������ԭ�򣨵�������ջ���popֱ��ջ�������㵥������ԭ��
	int max = 0;
	for (int i = 0;i < numCount;i++) {
		int temp = 0;
		std::cin >> temp;
		while (!numStack.empty() && temp >= numStack.top()) {
			int top = numStack.top();
			numStack.pop();
			max = std::max(max, top ^ temp);
		}
		if (!numStack.empty()) {
			int top = numStack.top();
			max = std::max(max, top ^ temp);
		}
		numStack.push(temp);
	}
	std::cout << max;
}