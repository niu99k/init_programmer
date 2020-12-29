/*
题目描述
小易非常喜欢拥有以下性质的数列:
1、数列的长度为n
2、数列中的每个数都在1到k之间(包括1和k)
3、对于位置相邻的两个数A和B(A在B前),都满足(A <= B)或(A mod B != 0)(满足其一即可)
例如,当n = 4, k = 7
那么{1,7,7,2},它的长度是4,所有数字也在1到7范围内,并且满足第三条性质,所以小易是喜欢这个数列的
但是小易不喜欢{4,4,4,2}这个数列。小易给出n和k,希望你能帮他求出有多少个是他会喜欢的数列。
输入描述:
输入包括两个整数n和k(1 ≤ n ≤ 10, 1 ≤ k ≤ 10^5)
输出描述:
输出一个整数,即满足要求的数列个数,因为答案可能很大,输出对1,000,000,007取模的结果。
示例1
输入
复制
2 2
输出
复制
3
*/
#include<iostream>
#include<map>
#include<vector>

#define MAXARRATCOUNT 11
#define MAXNUM 100005
#define MOD 1000000007
//void getInitApproximateVecForEachNum(int num, std::vector<int>& initApproximateVecForEachNum) {
//	for (int i = 1;i < num;i++) {
//		if (num % i != 0) {
//			initApproximateVecForEachNum.push_back(i);
//		}
//	}
//}
//void getInitApproximateVec(int maxNum, std::map<int, std::vector<int>>& initApproximateVec) {
//	for (int i = 1;i <= maxNum;i++) {
//		std::vector<int> initApproximateVecForEachNum = {};
//		getInitApproximateVecForEachNum(i, initApproximateVecForEachNum);
//		initApproximateVec[i] = initApproximateVecForEachNum;
//	}
//}
void getInitApproximateVecGoodNumForEveryNum(int maxNum, std::map<int, std::vector<int>>& goodNumForEveryNum) {
	for (int i = 1;i <= maxNum;i++) {
		for (int j = 1;j < i;j++) {
			if (i % j != 0)
				goodNumForEveryNum[i].push_back(j);
		}
	}
}
//void getDPInitVec(int arrayCount, int beginNum, std::vector<std::vector<int>>& dpInit, std::map<int, std::vector<int>>& goodNumForEveryNum) {
//	if (arrayCount == 1) {
//		dpInit[arrayCount][beginNum] = 1;
//		return;
//	}
//	else if (dpInit[arrayCount][beginNum] != -1) {
//		return;
//	}
//	else {
//		int count = 0;
//		for (auto& e : goodNumForEveryNum[beginNum]) {
//			getDPInitVec(arrayCount - 1, e, dpInit, goodNumForEveryNum);
//			count += dpInit[arrayCount - 1][e];
//		}
//		dpInit[arrayCount][beginNum] = count;
//	}
//
//}

int main() {
	int arrayCount = 9;
	int maxNum = 1;
	std::cin >> arrayCount;
	std::cin >> maxNum;
	//std::map<int, std::vector<int>> initApproximateVec;
	//初始化1-k 的约数（不包括自身）集合
	//getInitApproximateVec(maxNum, initApproximateVec);
	//初始化1-k实际需要遍历的数字集合
	//std::map<int, std::vector<int>> goodNumForEveryNum = {};
	//getInitApproximateVecGoodNumForEveryNum(maxNum, goodNumForEveryNum);
	//初始化DP表dp[n][begin]
	//std::vector<std::vector<int>> dpInit = {};
	/*for (int i = 0;i <= arrayCount;i++) {
		std::vector<int> temp = {};
		for (int j = 0;j <= maxNum;j++) {
			temp.push_back(0);
		}
		dpInit.push_back(temp);
	}*/
	int dpInit[MAXARRATCOUNT][MAXNUM];
	for (int arrayIndex = 1;arrayIndex <= arrayCount;arrayIndex++) {
		int sum = 0;
		for (int beginIndex = 1; beginIndex <= maxNum;beginIndex++) {
			if (arrayIndex == 1) {
				dpInit[arrayIndex][beginIndex] = 1;
			}
			else {
				/*for (auto& e : goodNumForEveryNum[beginIndex]) {
					count += dpInit[arrayIndex - 1][e];
				}
				for (int temp = beginIndex;temp <= maxNum;temp++) {
					count += dpInit[arrayIndex - 1][temp];
				}*/
				sum =(sum + dpInit[arrayIndex - 1][beginIndex])%MOD;
			}
		}
		int illeagal = 0;
		if (arrayIndex == 1) {
			continue;
		}
		for (int beginIndex = 1; beginIndex <= maxNum;beginIndex++) {
			dpInit[arrayIndex][beginIndex] = sum;
		}
		for (int beginIndex = 1;beginIndex <= maxNum;beginIndex++) {
			for (int rate = 2;true;rate++) {
				int illegalFirst = rate * beginIndex;
				if (illegalFirst > maxNum) {
					break;
				}
				dpInit[arrayIndex][illegalFirst] = (dpInit[arrayIndex][illegalFirst] - dpInit[arrayIndex - 1][beginIndex]+MOD)%MOD;
			}
		}
	}
	//查到结果
	int count = 0;
	for (int i = 1;i <= maxNum;i++) {
		//getDPInitVec(arrayCount, i, dpInit, goodNumForEveryNum);
		count =(count + dpInit[arrayCount][i])%MOD;
	}
	std::cout << count % MOD;
	return 0;
}
