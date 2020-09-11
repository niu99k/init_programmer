/*
题目描述
牛牛学习了冒泡排序,并写下以下冒泡排序的伪代码,注意牛牛排序的数组a是从下标0开始的。
BubbleSort(a):
	Repeat length(a)-1 times:
		For every i from 0 to length(a) - 2:
			If a[i] > a[i+1] then:
				 Swap a[i] and a[i+1]
牛牛现在要使用上述算法对一个数组A排序。
在排序前牛牛允许执行最多k次特定操作(可以不使用完),每次特定操作选择一个连续子数组,然后对其进行翻转,并且k次特定操作选择的子数组不相交。
例如A = {1, 2, 3, 4, 5, 6, 7}, k = 1,如果牛牛选择的子数组是[2,4](注意下标从0开始),那么翻转之后的数组变为A = {1, 2, 5, 4, 3, 6, 7}。
牛牛知道冒泡排序的效率一定程度上取决于Swap操作次数,牛牛想知道对于一个数组A在进行k次特定操作之后,再进行上述冒泡排序最少的Swap操作次数是多少?
输入描述:
输入包括两行,第一行包括两个正整数n和k(2 ≤ n ≤ 50, 1 ≤ k ≤ 50),表示数组的长度和允许最多的特定操作次数。
第二行n个正整数A[i](1 ≤ A[i] ≤ 1000),表示数组内的元素,以空格分割。
输出描述:
输出一个整数,表示在执行最多k次特定操作之后,对数组进行上述冒泡排序需要的Swap操作次数。
示例1
输入
复制
3 2
2 3 1
输出
复制
1*/

#include<iostream>
#include<map>
#include<vector>

#define INT_MAX 0x7fffffff
using namespace std;

/*
7 2
7 2 2 13 5 5 2
3
0*/
int getReverseCountBeforeI(vector<int>& numVec, int index) {
	int count = 0;
	for (int i = 0;i < index;i++) {
		int testNum = numVec[i];
		for (int j = i + 1;j < numVec.size();j++) {
			int compareNum = numVec[j];
			if (compareNum < testNum) {
				count++;
			}
		}
	}
	return count;
};
int getReverseCountBeforeIAfterReverse(vector<int>& numVec, int preIndex, int postIndex) {
	vector<int> testVec;
	for (int i = postIndex;i >= preIndex;i--) {
		testVec.push_back(numVec[i]);
	}
	int testCount = testVec.size();
	for (int i = postIndex + 1;i < numVec.size();i++) {
		testVec.push_back(numVec[i]);
	}
	int count = 0;
	for (int i = 0;i < testCount;i++) {
		int testNum = testVec[i];
		for (int j = i + 1;j < testVec.size();j++) {
			int compareNum = testVec[j];
			if (compareNum < testNum) {
				count++;
			}
		}
	}
	return count;
}
int getReverseCountAfterI(vector<int>& numVec, int index) {
	int count = 0;
	int testNum = numVec[index];
	for (int j = index + 1;j < numVec.size();j++) {
		int compareNum = numVec[j];
		if (compareNum < testNum) {
			count++;
		}
	}
	return count;
};
void initDpMap(map<int, int>& dpMap, int index, int shiftCount, vector<int>& numVec) {
	if (index < 0) {
		dpMap[index] = 0;
	}
	else if (index <= 0) {
		dpMap[index] = getReverseCountBeforeI(numVec, 1);
	}
	else if (dpMap.count(index) != 0) {
		return;
	}
	else {
		initDpMap(dpMap, index - 1, shiftCount, numVec);
		int minReverseCount = dpMap[index - 1] + getReverseCountAfterI(numVec, index);
		bool isShift = false;
		for (int i = 0;i < index;i++) {
			int reverseCount = 0;
			initDpMap(dpMap, i - 1, shiftCount - 1, numVec);
			int reverseCountBeforeI = dpMap[i - 1];
			int reverseCountBeforeIAfterReverse = getReverseCountBeforeIAfterReverse(numVec, i, index);
			reverseCount += reverseCountBeforeI;
			reverseCount += reverseCountBeforeIAfterReverse;
			if (reverseCount < minReverseCount) {
				minReverseCount = reverseCount;
				isShift = true;
			}
		}
		dpMap[index] = minReverseCount;
	}
}

int main() {
	int numCount = 0;
	int shiftCount = 0;
	vector<int> numVec;
	cin >> numCount;
	cin >> shiftCount;
	for (int i = 0;i < numCount;i++) {
		int temp = 0;
		cin >> temp;
		numVec.push_back(temp);
	}
	map<int, int> dpMap;
	initDpMap(dpMap, numCount - 1, shiftCount, numVec);
	int min = INT_MAX;
	cout << dpMap[numCount - 1];
	return 0;
}