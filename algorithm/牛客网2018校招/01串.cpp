#include<iostream>
/*
���һ��01��������������λ�õ��ַ����ǲ�һ����,���Ǿͽ����01��Ϊ����01��������: "1","10101","0101010"���ǽ���01����
С��������һ��01��s,С�����ҳ�һ����������Ӵ�,��������Ӵ���һ������01����С����Ҫ����æ�������������Ӵ��ĳ����Ƕ��١�
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