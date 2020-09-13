/*
��Ŀ����
ţţ����nֻ��ţ,ţţ���ÿֻ��ţ���,�����Ϳ�������׾ٵطֱ������ˡ� ÿ����ţ�������ֶ����Լ���ϲ��,��iֻ��ţ��Ҫһ��1��x[i]֮�������(���а���1��x[i])��
ţţ��Ҫ����������ţ��ϲ��,�����ţţ����ţţ�ж����ָ���ţ��ŵķ���,�������Ҫ��ı�ŷ���������
��������:
�����������,��һ��һ������n(1 �� n �� 50),��ʾ��ţ������ �ڶ���Ϊn������x[i](1 �� x[i] �� 1000)
�������:
���һ������,��ʾţţ������������ţ��ϲ���ϱ�ŵķ���������Ϊ�𰸿��ܴܺ�,�����������1,000,000,007��ģ��
ʾ��1
����
����
4
4 4 4 4
���
����
24
*/

#include<iostream>
#include<vector>
#include<map>
#include<algorithm>

#define MOD 1000000007
using namespace std;
bool cmp(long long a, long long b) {
	return a < b;
}
int main() {
	long long numCount = 0;
	cin >> numCount;
	vector<long long>numVec;
	for (int i = 0;i < numCount;i++) {
		long long temp = 0;
		cin >> temp;
		numVec.push_back(temp);
	}
	sort(numVec.begin(), numVec.end(), cmp);
	long long possibleCount = 1;
	for (int i = 0;i < numVec.size();i++) {
		possibleCount = (possibleCount * (numVec[i]-i)) % MOD;
	}
	cout << possibleCount;
	return 0;
}