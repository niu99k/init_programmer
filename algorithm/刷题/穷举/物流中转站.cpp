/*
��Ŀ����
Shopee�������кܶ����תվ����ѡַ�Ĺ����У���ѡ�����û�����ĵط���һ��������תվ��

�������һ����άƽ������ÿ�������Ƿ�����Ϊ1�������ǿյ���Ϊ0���ҵ�һ���յ��޽�һ��������תվ��ʹ�����������תվ�����еķ��ӵľ���֮����С�� ���޽����򷵻���С�ľ���͡�����޷��޽����򷵻� -1��


����Χ������100*100���ڵ�������μ������С�ľ���ͣ�

��ƽ������ǳ��������£���α��ⲻ��Ҫ�ļ��㣿

��������:
4
0 1 1 0
1 1 0 1
0 0 1 0
0 0 0 0

�����뷽�������Ȼ���������뷿�ӺͿյص����ݣ��Կո�ָ���
�������:
8

���޽����򷵻���С�ľ���͡�����޷��޽����򷵻� -1��
*/
#include<iostream>
#include<vector>
#include<cmath>

#define INT_MAX 0x7fffffff
#define INT_MIN 0x80000000
int main() {
	int map[100][100];
	int mapSize = -1;
	std::cin >> mapSize;
	if (mapSize <= 0) {
		std::cout << "-1";
		return 0;
	}
	bool noEmptyFlag = true;
	for (int i = 0;i < mapSize;i++) {
		for (int j = 0;j < mapSize;j++) {
			int temp = -1;
			std::cin >> temp;
			if (temp == 0) {
				noEmptyFlag = false;
			}
			map[i][j] = temp;
		}
	}
	if (noEmptyFlag) {
		std::cout << "-1";
		return 0;
	}
	//��ʼbrute force
	int minDis = INT_MAX;
	for (int i = 0;i < mapSize;i++) {
		for (int j = 0;j < mapSize;j++) {
			if (map[i][j] == 0) {
				int dis = 0;
				for (int i2 = 0;i2 < mapSize;i2++) {
					for (int j2 = 0;j2 < mapSize;j2++) {
						if (map[i2][j2] == 1) {
							dis += (std::abs(i2 - i) + std::abs(j2 - j));
						}
					}
				}
				if (dis < minDis) {
					minDis = dis;
				}
			}
		}
	}
	std::cout << minDis;
	return 0;
}
