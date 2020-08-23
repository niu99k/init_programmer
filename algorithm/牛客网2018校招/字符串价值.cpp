/*��Ŀ����
��һ����Ȥ���ַ�����ֵ���㷽ʽ:ͳ���ַ�����ÿ���ַ����ֵĴ���,Ȼ���������ַ�������ƽ������Ϊ�ַ����ļ�ֵ
����: �ַ���"abacaba",�������4��'a',2��'b',1��'c',��������ַ����ļ�ֵΪ4 * 4 + 2 * 2 + 1 * 1 = 21
ţţ��һ���ַ���s,�����������s���Ƴ����k���ַ�,���Ŀ�����õõ����ַ����ļ�ֵ��С��
��������:
�����������,��һ��һ���ַ���s,�ַ���s�ĳ���length(1 �� length �� 50),����ֻ����Сд��ĸ('a'-'z')��
�ڶ��а���һ������k(0 �� k �� length),�������Ƴ����ַ�������
�������:
���һ������,��ʾ�õ�����С��ֵ
ʾ��1
����
����
aba
1
���
����
2*/
#include<iostream>
#include<map>
#include<vector>
void initMap(std::string& str, std::map<int, int>& result) {
	std::map<std::string, int>repetitionCountMap = {};
	for (int i = 0;i < str.size();i++) {
		std::string chr = str.substr(i, 1);
		repetitionCountMap[chr] += 1;
	}
	for (auto& e : repetitionCountMap) {
		result[e.second] += 1;
	}

}
void sim(std::map<int, int>& repetitionCountMapAfterSim, std::map<int, int> repetitionCountMap, int moveCount) {
	std::vector<int> repetitionCountMapKeyVec = {};
	for (auto& e : repetitionCountMap) {
		repetitionCountMapKeyVec.push_back(e.first);
	}
	int maxKey = repetitionCountMapKeyVec[repetitionCountMapKeyVec.size() - 1];
	while (moveCount > 0 && maxKey >= 1) {
		if (repetitionCountMap[maxKey] > moveCount) {
			repetitionCountMap[maxKey] -= moveCount;
			if (maxKey - 1 >= 0) {
				repetitionCountMap[maxKey - 1] += moveCount;
				maxKey--;
			}
			moveCount = 0;
		}
		else {
			moveCount -= repetitionCountMap[maxKey];
			if (maxKey >= 1) {
				repetitionCountMap[maxKey - 1] += repetitionCountMap[maxKey];
			}
			repetitionCountMap[maxKey] = 0;
			maxKey--;
		}
	}
	for (auto& e : repetitionCountMap) {
		repetitionCountMapAfterSim[e.first] = e.second;
	}
}
void getResult(int& value, std::map<int, int>& repetitionCountMapAfterSim) {
	for (auto& e : repetitionCountMapAfterSim) {
		value += e.second * e.first * e.first;
	}
};
int main() {
	std::string str = "aba";
	int moveCount = 1;
	std::cin >> str;
	std::cin >> moveCount;

	//init map<int,int>
	std::map<int, int>repetitionCountMap = {};
	initMap(str, repetitionCountMap);

	//sim
	std::map<int, int>repetitionCountMapAfterSim = {};
	sim(repetitionCountMapAfterSim, repetitionCountMap, moveCount);

	//get result
	int value = 0;
	getResult(value, repetitionCountMapAfterSim);

	std::cout << value;
	return 0;
}