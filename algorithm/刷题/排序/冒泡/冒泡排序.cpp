/*
��Ŀ����
ţţѧϰ��ð������,��д������ð�������α����,ע��ţţ���������a�Ǵ��±�0��ʼ�ġ�
BubbleSort(a):
	Repeat length(a)-1 times:
		For every i from 0 to length(a) - 2:
			If a[i] > a[i+1] then:
				 Swap a[i] and a[i+1]
ţţ����Ҫʹ�������㷨��һ������A����
������ǰţţ����ִ�����k���ض�����(���Բ�ʹ����),ÿ���ض�����ѡ��һ������������,Ȼ�������з�ת,����k���ض�����ѡ��������鲻�ཻ��
����A = {1, 2, 3, 4, 5, 6, 7}, k = 1,���ţţѡ�����������[2,4](ע���±��0��ʼ),��ô��ת֮��������ΪA = {1, 2, 5, 4, 3, 6, 7}��
ţţ֪��ð�������Ч��һ���̶���ȡ����Swap��������,ţţ��֪������һ������A�ڽ���k���ض�����֮��,�ٽ�������ð���������ٵ�Swap���������Ƕ���?
��������:
�����������,��һ�а�������������n��k(2 �� n �� 50, 1 �� k �� 50),��ʾ����ĳ��Ⱥ����������ض�����������
�ڶ���n��������A[i](1 �� A[i] �� 1000),��ʾ�����ڵ�Ԫ��,�Կո�ָ
�������:
���һ������,��ʾ��ִ�����k���ض�����֮��,�������������ð��������Ҫ��Swap����������
ʾ��1
����
����
3 2
2 3 1
���
����
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