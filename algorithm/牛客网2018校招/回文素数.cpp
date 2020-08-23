/*��Ŀ����
���һ������ֻ�ܱ�1���Լ�����,�ͳ��������������
���һ�������ŷ��Ŷ���һ��,�ͳ�Ϊ������ǻ�����������:6, 66, 606, 6666
���һ�����ּ�������Ҳ�ǻ�����,�ͳ�������ǻ�������
ţţ���ڸ���һ������[L, R],ϣ���������������������ж��ٸ�����������
��������:
�������һ��,һ��������������(1 �� L �� R �� 1000)
�������:
���һ������,��ʾ�����ڻ�������������
ʾ��1
����
����
100 150
���
����
2*/
#include<iostream>
#include<map>
#include<vector>
#include<string>

void isPrimeFunc(bool& isPrime, int& num) {
	if (num == 1) {
		isPrime = false;
	}
	for (int i = 2;i * i <= num;i++) {
		if (num % i == 0) {
			isPrime = false;
			break;
		}
	}
}
void isHuiwenPrimeFunc(bool& isHuiwenPrime, int& num) {
	std::string numToString = std::to_string(num);
	int endIndex = numToString.size() - 1;
	int beginIndex = 0;
	while (endIndex > beginIndex) {
		if (numToString[beginIndex] != numToString[endIndex]) {
			isHuiwenPrime = false;
			break;
		}
		beginIndex++;
		endIndex--;
	}
};
int main() {
	int left = 100;
	int right = 150;
	std::cin >> left;
	std::cin >> right;

	int count = 0;
	for (int i = left;i <= right;i++) {
		bool isPrime = true;
		bool isHuiwenPrime = true;

		isPrimeFunc(isPrime, i);
		isHuiwenPrimeFunc(isHuiwenPrime, i);

		if (isPrime && isHuiwenPrime) {
			count++;
		}
	}
	std::cout << count;
	return 0;
}