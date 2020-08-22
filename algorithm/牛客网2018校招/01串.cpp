#include<iostream>
/*
如果一个01串任意两个相邻位置的字符都是不一样的,我们就叫这个01串为交错01串。例如: "1","10101","0101010"都是交错01串。
小易现在有一个01串s,小易想找出一个最长的连续子串,并且这个子串是一个交错01串。小易需要你帮帮忙求出最长的这样的子串的长度是多少。
*/
int main() {
	std::string input;
	std::cin >> input;
	int beginIndex = 0;
	int endIndex = 0;
	char lastChar = input[0];
	char testChar = input[0];
	int maxStepCount = 0;
	for (int i = 0;i < input.size();i++) {
		testChar = input[i];
		if ((lastChar == testChar && i > 0)) {
			endIndex = i;
			int stepCount = endIndex - beginIndex;
			if (stepCount > maxStepCount) {
				maxStepCount = stepCount;
			}
			beginIndex = i;
			endIndex = i;
		}
		else if(i!=input.size()-1){
			endIndex = i;

		}
		else {
			int stepCount = i - beginIndex+1;
			if (stepCount > maxStepCount) {
				maxStepCount = stepCount;
			}
		}
		lastChar = testChar;
	}
	std::cout << maxStepCount;
	return 0;
}