/*
��Ŀ����
������������������x��k,���Ƕ���repeat(x, k)Ϊ��x�ظ�дk���γɵ���,����repeat(1234, 3) = 123412341234,repeat(20,2) = 2020.
ţţ���ڸ���4������x1, k1, x2, k2, ����v1 = (x1, k1), v2 = (x2, k2),�������Ƚ�v1��v2�Ĵ�С��
��������:
�������һ��,һ������4��������x1, k1, x2, k2(1 �� x1,x2 �� 10^9, 1 �� k1,k2 �� 50),�Կո�ָ�
�������:
���v1С��v2���"Less",v1����v2���"Equal",v1����v2���"Greater".
ʾ��1
����
����
1010 3 101010 2
���
����
Equal
*/
#include<iostream>
#include<map>
#include<vector>
void getNum(std::string& num, std::string base, int multiple) {
	for (int i = 0;i < multiple;i++) {
		num += base;
	}
};
void getDigit(int& digit, std::string num) {
	digit = num.size();
};
void compareSameDigit(std::string& result, std::string num1, std::string num2) {
	for (int i = 0;i < num1.size();i++) {
		char test1 = num1[i];
		char test2 = num2[i];
		if (test1 > test2) {
			result = "Greater";
			return;
		}
		else if (test1 < test2) {
			result = "Less";
			return;

		}
	}
	result = "Equal";
};
int main() {
	std::string base1 = "1010";
	int multiple1 = 3;
	std::string base2 = "101010";
	int multiple2 = 2;

	std::cin >> base1;
	std::cin >> multiple1;
	std::cin >> base2;
	std::cin >> multiple2;

	std::string num1 = "";
	std::string num2 = "";
	getNum(num1, base1, multiple1);
	getNum(num2, base2, multiple2);

	//�Ƚ�λ��
	int digit1 = 0;
	int digit2 = 0;
	getDigit(digit1, num1);
	getDigit(digit2, num2);
	if (digit1 > digit2) {
		std::cout << "Greater";
		return 0;
	}
	else if (digit1 < digit2) {
		std::cout << "Less";
		return 0;
	}
	else {
		//��ͷ��ʼ�����Ƚϴ�С
		std::string result = "";
		compareSameDigit(result, num1, num2);
		std::cout << result;
	}
}