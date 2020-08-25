/*��Ŀ����
ţţ����������ľ��,���ȷֱ���a,b,c��ţţ���԰���һһ��ľ����������,ţţ��Ŀ������������ľ������һ��������,����ţţ��ϣ����������ε��ܳ�Խ��Խ�á�
��������:
�������һ��,һ������������a, b, c(1 �� a, b, c �� 100), �Կո�ָ�
�������:
���һ������,��ʾ��ƴ�ճ����ܳ����������Ρ�
ʾ��1
����
����
1 2 3
���
����
5*/
#include<iostream>
#include<map>
#include<vector>

#define EDGE_COUNT 3
int main() {
	std::vector<int> edgeVec = {};
	for (int i = 0;i < EDGE_COUNT;i++) {
		int temp = 0;
		std::cin >> temp;
		edgeVec.push_back(temp);
	}

	//�ҵ����ı�
	int max = 0;
	for (auto& e : edgeVec) {
		if (e > max) {
			max = e;
		}
	}

	//�����ոպ�
	int maxAccept = 0;
	bool isPass = false;
	for (auto& e : edgeVec) {
		if (e == max && !isPass) {
			isPass = true;
			continue;
		}
		maxAccept += e;
	}

	//���
	int perimeter = 0;
	if (max < maxAccept) {
		perimeter += max;
		perimeter += maxAccept;
	}
	else {
		perimeter += maxAccept;
		perimeter += maxAccept - 1;
	}
	std::cout << perimeter;
	return 0;
}