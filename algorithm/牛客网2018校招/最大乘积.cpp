#include<iostream>
#include<vector>
#include<algorithm>

#define INT_MAX 0x7fffffff
#define INT_MIN 0x80000000
int main() {
	long long int result = 0;
	int count = 0;
	std::vector<int> numList = {};
	std::cin >> count;
	for (int i = 0;i < count;i++) {
		int tempNum = 0;
		std::cin >> tempNum;
		numList.push_back(tempNum);
	}
	//找最大的正数
	bool flag_0 = false;
	int maxInt = -1;
	for (int num : numList) {
		if (num == 0) {
			flag_0 = true;
		}
		if (num > maxInt) {
			maxInt = num;
		}
	}
	if (maxInt <= 0) {
		if (flag_0) {
			result = 0;
		}
		else {
			std::vector<int> numListTemp = {};
			std::vector<int> numListTemp_ = {};
			int maxValue_1 = INT_MIN;
			int maxValue_2 = INT_MIN;
			int maxValue_3 = INT_MIN;
			maxValue_1 = *std::max_element(numList.begin(), numList.end());
			bool isMeetMaxValue = false;
			for (int e : numList) {
				if (e == maxValue_1 && !isMeetMaxValue) {
					isMeetMaxValue = true;
					continue;
				}
				numListTemp.push_back(e);
			}
			maxValue_2 = *std::max_element(numListTemp.begin(), numListTemp.end());
			isMeetMaxValue = false;
			for (int e : numList) {
				if (e == maxValue_2 && !isMeetMaxValue) {
					isMeetMaxValue = true;
					continue;
				}
				numListTemp_.push_back(e);
			}
			maxValue_3 = *std::max_element(numListTemp_.begin(), numListTemp_.end());
			result = (long long)maxValue_1 * (long long)maxValue_2 * (long long)maxValue_3;
		}
	}
	else {
		std::vector<int> numListTemp = {};
		bool isMeetMaxValue = false;
		for (int e : numList) {
			if (e == maxInt && !isMeetMaxValue) {
				isMeetMaxValue = true;
				continue;
			}
			numListTemp.push_back(e);
		}
		numList = numListTemp;
		numListTemp = {};
		//比较最大的两个正数的积
		int maxValue_1 = INT_MIN;
		int maxValue_2 = INT_MIN;
		maxValue_1 = *std::max_element(numList.begin(), numList.end());
		isMeetMaxValue = false;
		for (int e : numList) {
			if (e == maxValue_1 && !isMeetMaxValue) {
				isMeetMaxValue = true;
				continue;
			}
			numListTemp.push_back(e);
		}
		maxValue_2 = *std::max_element(numListTemp.begin(), numListTemp.end());
		//比较绝对值最大两个负数的积
		numListTemp = {};
		int minValue_1 = INT_MAX;
		int minValue_2 = INT_MAX;
		minValue_1 = *std::min_element(numList.begin(), numList.end());
		isMeetMaxValue = false;
		for (int e : numList) {
			if (e == minValue_1 && !isMeetMaxValue) {
				isMeetMaxValue = true;
				continue;
			}
			numListTemp.push_back(e);
		}

		minValue_2 = *std::min_element(numListTemp.begin(), numListTemp.end());
		//比较
		int maxValueProduct = maxValue_1 * maxValue_2;
		int minValueProduct = minValue_1 * minValue_2;
		if (maxValueProduct >= 0 || minValueProduct >= 0
			) {
			long long temp1 = (long long)std::max(maxValueProduct, minValueProduct);
			long long temp2 = (long long)maxInt;
			result = temp1 * temp2;
		}
		else {
			//取绝对值最小的两个数的积
			minValue_1 = INT_MAX;
			minValue_2 = INT_MAX;
			numListTemp = {};
			numListTemp = {};
			std::vector<int> numListTemp_ = {};
			for (int e : numList) {
				if (e < 0) {
					numListTemp.push_back(-e);
				}
				else {
					numListTemp.push_back(e);
				}
			}
			bool isMeetMaxValue = false;
			minValue_1 = *std::min_element(numListTemp.begin(), numListTemp.end());
			for (int e : numListTemp) {
				if (e == minValue_1 && !isMeetMaxValue) {
					isMeetMaxValue = true;
					continue;
				}
				numListTemp_.push_back(e);
			}
			minValue_1 = *std::min_element(numListTemp_.begin(), numListTemp_.end());
			result = 0 - (long long)minValue_1 * (long long)minValue_2 * (long long)maxInt;
		}
	}
	std::cout << result;
}