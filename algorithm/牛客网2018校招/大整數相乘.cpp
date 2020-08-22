#include<iostream>
#include<map>

#define ASIIC_0 48
#define NUMSYSTEM 10
int main() {
	std::string input_A = "";
	std::string input_B = "";
	std::cin >> input_A;
	std::cin >> input_B;
	std::map<int, int> inputToString_A = {};
	std::map<int, int> inputToString_B = {};
	std::map<int, int> result = {};
	int size_A = input_A.size();
	int size_B = input_B.size();
	for (int i = size_A - 1;i >= 0;i--) {
		inputToString_A[size_A - 1 - i] = input_A.at(i) - ASIIC_0;
	}
	for (int i = size_B - 1;i >= 0;i--) {
		inputToString_B[size_A - 1 - i] = input_B.at(i) - ASIIC_0;
	}
	for (auto& e_A : inputToString_A) {
		int position_A = e_A.first;
		int num_A = e_A.second;
		for (auto& e_B : inputToString_B) {
			int position_B = e_B.first;
			int num_B = e_B.second;
			int position_result = position_A + position_B;
			int num_result = num_A * num_B;
			int originNum = result[position_result];
			result[position_result] = originNum + num_result;
		}
	}
	bool isResultResonalble = false;
	while (!isResultResonalble) {
		isResultResonalble = true;
		int originalNum = 0;
		int originalPosition = 0;
		int leftPosition = 0;
		int leftNum = 0;
		for (auto& e : result) {
			originalNum = e.second;
			if (originalNum >= NUMSYSTEM) {
				isResultResonalble = false;
				originalPosition = e.first;
				leftPosition = e.first + 1;
				leftNum = result[leftPosition];
				break;
			}
		}
		if (!isResultResonalble) {
			int correctNumInOriginalPosition = originalNum % 10;
			int correctNumInLeftPosition = originalNum / 10 + leftNum;
			result[originalPosition] = correctNumInOriginalPosition;
			result[leftPosition] = correctNumInLeftPosition;
		}

	}
	std::map<int, int> resultTemp = {};
	for (auto& e : result) {
		resultTemp[0 - e.first] = e.second;
	}
	for (auto& e : resultTemp) {
		std::cout << e.second;
	}
	return 0;
}