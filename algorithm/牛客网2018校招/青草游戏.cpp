/*
��Ŀ����
ţţ�����򶼺�ϲ����ݡ��������Ǿ����������Ϸ��
�����һ��װ��n����ݵ�����,ţţ���������ν���,ţţ�ȿ�ʼ����ÿ���غ���,ÿ����ұ����һЩ�����е����,���Ե���ݷ���������4��x����,����1,4,16,64�ȵȡ������������гԵ���Ч������ݵ������ܡ�
�ٶ�ţţ�������ǰ�����ѷ���������Ϸ,�����ʤ���ߵ����֡�
��������:
�������t+1�С�
��һ�а���һ������t(1 �� t �� 100),��ʾ�����.
������t��ÿ��һ��n(1 �� n �� 10^9),��ʾ��ݷ���
�������:
����ÿһ��n,���ţţʤ�����"niu",�������ʤ�����"yang"��
ʾ��1
����
����
3
1
2
3
���
����
niu
yang
niu*/

#include<iostream>
#include<map>
#include<vector>
#include<string>


/*https://blog.csdn.net/huyahuioo/article/details/80081031*/
int main() {
	int grassCount = 0;
	std::vector<long> grassVec = {};
	std::cin >> grassCount;
	for (int i = 0;i < grassCount;i++) {
		int temp = 0;
		std::cin >> temp;
		grassVec.push_back(temp);
	}
	std::vector<std::string> name = { "niu","yang" };

	for (int i = 0;i < grassCount;i++) {
		int result = 0;
		switch (grassVec[i] % 5) {
		case 0:result = 1;break;
		case 1:result = 0;break;
		case 2:result = 1;break;
		case 3:result = 0;break;
		case 4:result = 0;break;
		default:break;
		}

		std::cout << name[result] << "\n";
	}
	return 0;
}

