/*
��Ŀ����
С��Ϊ�������ĸ�ĸ�������Ѿ����������, ���������ȥ�Լ���סһ��ʱ�䡣һ����������������໨��: С��ÿ������һ��ˮ��������Ҫÿ��֧��xԪ�ķ�����𡣵�ǰС�������Ѿ���f��ˮ����dԪǮ, С��Ҳ��ȥ�̵깺��һЩˮ��, �̵�ÿ��ˮ������pԪ��С��Ϊ�˱������������������, ϣ���ܶ��������ʱ��Խ��Խ��, С��ϣ��������������һ��������ܶ�����������졣
�������� :
�������һ��, �ĸ�����x, f, d, p(1 �� x, f, d, p �� 2 * 10 ^ 9), �Կո�ָ�
������� :
���һ������, ��ʾС������ܶ�����������졣
ʾ��1
����
����
3 5 100 10
���
����
11
*/
#include<iostream>
#include<map>
#include<vector>
int liveMaxDays(int fruitCount, int money, int costPerFruit, int dayRent) {
	int result = -1;
	//�Ȱ�����ˮ������
	int dayBeforeFruitOut = fruitCount;
	//����ˮ��û���� Ǯ������ ��𻹹����پ�ס����
	int rentCostBeforeFruitOut = dayRent * dayBeforeFruitOut;
	if (rentCostBeforeFruitOut > money) {
		result = money / dayRent;
	}
	else {
		//����ˮ������ Ǯ���� ÿ�콻�����ˮ�� ��ס���ס���
		int restMoney = money - rentCostBeforeFruitOut;
		int insistDay = restMoney / (dayRent + costPerFruit);
		result = dayBeforeFruitOut + insistDay;
	}
	return result;
}
int main() {
	int fruitCount = 5;
	int money = 100;
	int costPerFruit = 10;
	int dayRent = 3;
	std::cin >> dayRent >> fruitCount >> money >> costPerFruit;
	int day = liveMaxDays(fruitCount, money, costPerFruit, dayRent);
	std::cout << day;
	return 0;
}