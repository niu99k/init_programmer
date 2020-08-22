#include<iostream>
#include<vector>
#include<map>
/*
��Ŀ����
ţţ������n���˿��ƣ�ÿ���˿��ƶ��е����ͻ�ɫ��������ɡ�����Ϊ��1��-��9��������������ɫΪ'C','D','H','S''���е�һ�����ֱ��ʾ÷�������顢���ҡ����ҡ�����ţţ�밴һ����˳�����n�����ӵ�������˳��Ĺ�������1.��
1.������ڻ�ʣ�������ƣ����ƶ������ӵ�
2.������ڻ�ʣ���������ƣ����Ƶ׵����ӵ�
ţţ��֪����������˳����ʲô���뷵������˳����ַ���

ʾ��1
����
����
"3C8D6H3D"
���
����
"3D3C8D6H"
˵��
��ʼn=4��Ϊ���������ӵ��Ƶ׵���3D
n=3��Ϊ�������ӵ��ƶ�����3C
n=2��Ϊ�������ӵ��ƶ�����8D
n=1��Ϊ���������ӵ��Ƶ׵���6H
ʾ��2
����
����
"8S8S8S8S8S8S8S"
���
����
"8S8S8S8S8S8S8S"
˵��
��Ϊȫ��8S����������˳���ÿһ����Ҳ����8S
��ע:
����100\%�����ݣ�1\leq n\leq 10����100%�����ݣ�1��n��10
*/
std::vector<std::string> initString(std::string inputStr) {
	std::vector<std::string> initStrVec = {};
	while (inputStr != "") {
		std::string temp = inputStr.substr(0, 2);
		initStrVec.push_back(temp);
		inputStr = inputStr.substr(2, inputStr.size() - 2);
	}
	return initStrVec;
}
bool isss(int num) {
	for (int i = 2;i < num;i++) {
		if (i * i > num) {
			return false;
		}
		if (num % i == 0) {
			return true;
		}
	}
	return false;
}
void initssVector(std::vector<int> & initss, int n) {
	for (int i = 1;i <= n;i++) {
		if (isss(i)) {
			initss[i] = 1;
		}
	}
}

std::string Orderofpoker(std::string x) {
	int n = 0;
	std::string result = "";
	//��ʼ���˿���������� string[]
	std::vector<std::string> initStrVector = initString(x);
	n = initStrVector.size();
	std::vector<int> initss = {};
	for (int i = 0;i <= n;i++) {
		initss.push_back(-1);
	}
	initssVector(initss, n);
	std::map<int, int> order = {};
	for (int i = 1;i <= n;i++) {
		if (initss[i] == 1) {
			//��ǰ��
			order[i] = 0;
		}
		else {
			//��β��
			order[i] = 1;
		}
	}
	int frontFlag = 0;
	int postFlag = n - 1;
	while (frontFlag != postFlag) {
		if (order[postFlag - frontFlag + 1] == 1) {
			result += initStrVector[frontFlag];
			frontFlag++;
		}
		else {
			result += initStrVector[postFlag];
			postFlag--;
		}
	}
	result += initStrVector[postFlag];
	return result;
}
int main() {
	std::cout << Orderofpoker("3C8D6H3D");
	return 0;
}
