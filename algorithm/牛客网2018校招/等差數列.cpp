
/*
如果一个数列S满足对于所有的合法的i,都有S[i + 1] = S[i] + d, 这里的d也可以是负数和零,我们就称数列S为等差数列。
小易现在有一个长度为n的数列x,小易想把x变为一个等差数列。小易允许在数列上做交换任意两个位置的数值的操作,并且交换操作允许交换多次。但是有些数列通过交换还是不能变成等差数列,小易需要判别一个数列是否能通过交换操作变成等差数列
*/
#include<iostream>
#include <map>
#include<algorithm>
#define MAX_ARRAY_COUNT 51
int main() {
	int input[MAX_ARRAY_COUNT];
	int arrayCount = 0;
	std::cin >> arrayCount;
	for (int i = 0;i < arrayCount;i++) {
		std::cin >> input[i];
	}
	std::sort(input, input + arrayCount);
	int dif = input[1] - input[0];
	bool isPossible = true;
	for (int i = 1;i < arrayCount;i++) {
		if (input[i] - input[i-1] != dif) {
			isPossible = false;
			break;
		}
	}
	if (isPossible) {
		std::cout << "Possible";
	}
	else {
		std::cout << "Impossible";
	}
	return 0;
}