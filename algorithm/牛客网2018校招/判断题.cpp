/*
��Ŀ����
ţţ�μ���һ������,���԰���n���ж���,ÿ����һ������1��,ţţ����ǰ��ȫû��׼��,���Կ���ֻ�ܿ�Ե����,ţţ�ڿ�����һ���²���t����Ŀ�Ĵ���"��ȷ",������ţţ��Ϊ"����"�����Խ�����ţţ֪��ʵ����n��������a����Ŀ�Ĵ�Ӧ����"��ȷ",����ţţ��֪����������Щ��Ŀ,ţţϣ�����ܰ�����������ܻ�õ���ߵĿ��Է����Ƕ��١�
��������:
�������һ��,һ����������������n, t, a(1 �� n, t, a �� 50), �Կո�ָ�
�������:
���һ������,��ʾţţ���ܻ�õ���߷��Ƕ��١�
ʾ��1
����
����
3 1 2
���
����
2
*/
#include<iostream>
#include<map>
#include<vector>
int main() {
	int totalCount = 3;
	int getACount = 1;
	int realACount = 2;

	std::cin >> totalCount;
	std::cin >> getACount;
	std::cin >> realACount;
	//���A����,Bȫ�¶�
	if (getACount < realACount) {
		std::cout << totalCount - realACount + getACount;
	}
	else
		//���A���ˣ�Aȫ�¶�
		std::cout << realACount + totalCount - getACount;
	return 0;
}