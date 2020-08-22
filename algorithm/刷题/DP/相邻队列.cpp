/*
��Ŀ����
С�׷ǳ�ϲ��ӵ���������ʵ�����:
1�����еĳ���Ϊn
2�������е�ÿ��������1��k֮��(����1��k)
3������λ�����ڵ�������A��B(A��Bǰ),������(A <= B)��(A mod B != 0)(������һ����)
����,��n = 4, k = 7
��ô{1,7,7,2},���ĳ�����4,��������Ҳ��1��7��Χ��,�����������������,����С����ϲ��������е�
����С�ײ�ϲ��{4,4,4,2}������С�С�׸���n��k,ϣ�����ܰ�������ж��ٸ�������ϲ�������С�
��������:
���������������n��k(1 �� n �� 10, 1 �� k �� 10^5)
�������:
���һ������,������Ҫ������и���,��Ϊ�𰸿��ܴܺ�,�����1,000,000,007ȡģ�Ľ����
ʾ��1
����
����
2 2
���
����
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
	//��ʼ��1-k ��Լ������������������
	//getInitApproximateVec(maxNum, initApproximateVec);
	//��ʼ��1-kʵ����Ҫ���������ּ���
	//std::map<int, std::vector<int>> goodNumForEveryNum = {};
	//getInitApproximateVecGoodNumForEveryNum(maxNum, goodNumForEveryNum);
	//��ʼ��DP��dp[n][begin]
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
	//�鵽���
	int count = 0;
	for (int i = 1;i <= maxNum;i++) {
		//getDPInitVec(arrayCount, i, dpInit, goodNumForEveryNum);
		count =(count + dpInit[arrayCount][i])%MOD;
	}
	std::cout << count % MOD;
	return 0;
}
