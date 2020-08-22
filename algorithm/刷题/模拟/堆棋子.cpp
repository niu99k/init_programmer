/*
��Ŀ����
С�׽�n�����Ӱڷ���һ�����޴�������ϡ���i�����ӷ��ڵ�x[i]��y[i]�С�ͬһ������������ö�����ӡ�ÿһ�β���С�׿��԰�һ���������𲢽����ƶ���ԭ���ӵ��ϡ��¡����ҵ�����һ�������С�С����֪��Ҫ�������ϳ�����һ��������������i(1 �� i �� n)����������Ҫ�����ٲ�������.
��������:
�����������,��һ��һ������n(1 �� n �� 50),��ʾ���ӵĸ���
�ڶ���Ϊn�����ӵĺ�����x[i](1 �� x[i] �� 10^9)
������Ϊn�����ӵ�������y[i](1 �� y[i] �� 10^9)
�������:
���n������,��i����ʾ��������һ������������i����������Ҫ�Ĳ�����,�Կո�ָ��ĩ�޿ո�

��������ʾ:
����1������: ����Ҫ����
����2������: ��ǰ�������ӷ���(1, 1)��
����3������: ��ǰ�������ӷ���(2, 1)��
����4������: ���������Ӷ�����(3, 1)��
ʾ��1
����
����
4
1 2 4 9
1 1 1 1
���
����
0 1 3 10
*/
/*
˼·
���ӣ�https://www.nowcoder.com/questionTerminal/27f3672f17f94a289f3de86b69f8a25b?f=discussion
��Դ��ţ����

����ö�ٷ���Ȼ���ˣ��ؼ����ڣ��������ӵ��Ǹ����ӣ����������Ȼ�����ӳ�ʼ�ĺ�����
���м�
�÷�֤����xy����ʵ�Ƕ����ģ���ֻ����x���꣬�����k�����Ӷѵ�x0�������õĲ������٣�
a�����ӳ�ʼ��x0����ߣ�b�����ӳ�ʼ��x0���ұߣ���a>b,��ô��Ȼ���ں�����Ϊx0-1�ĸ�
�ӣ���k�����ӵ�x0-1�Ĳ�������٣�b>a���������ôx0+1��Ŀ�꽫��x0���ţ�����a=b��
x0-1 ��x0�Ĳ�����һ���ġ���ˣ����ջ�����ӵ�x����ֻҪ�����ӳ�ʼ��x�������п���
*/
#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
#define INT_MAX 0x7fffffff
void getResult(int& chessPieceCount, std::vector<std::pair<int, int>>& chessPiecePosition, std::vector<int>& minMoveResult) {
	//��ʼ�����ܵĵ㼯
	std::vector< std::pair<int, int>  >possibleOpt = {};
	for (int i = 0;i < chessPiecePosition.size();i++) {
		for (int j = 0;j < chessPiecePosition.size();j++) {
			possibleOpt.push_back({ chessPiecePosition[i].first ,chessPiecePosition[j].second });
		}
	}
	//��ʼ��ÿ�����ӵ����ܵ�ľ���
	//ÿ�����ܵ㵽ÿ�����������
	std::vector<std::vector<int>> disVec = {};
	for (int i = 0;i < possibleOpt.size();i++) {
		std::vector<int> temp = {};
		for (int j = 0;j < chessPiecePosition.size();j++) {
			temp.push_back(std::abs(chessPiecePosition[j].first - possibleOpt[i].first) + std::abs(chessPiecePosition[j].second - possibleOpt[i].second));
		}
		std::sort(temp.begin(), temp.end(), std::less<int>());
		disVec.push_back(temp);
	}
	//��ʼ��ÿ�����ܵ㵽���ӵ��ۼ���ֵ
	std::vector<std::vector<int>> disSumVec = {};
	for (int i = 0;i < disVec.size();i++) {
		std::vector<int> temp = {};
		for (int j = 1;j <= disVec[i].size();j++) {
			int sum = 0;
			int flag = 0;
			for (auto& e : disVec[i]) {
				if (flag == j) {
					break;
				}
				else {
					flag++;
					sum += e;
				}
			}
			temp.push_back(sum);
		}
		disSumVec.push_back(temp);
	}
	//���������
	std::vector<int> result = {};
	for (int i = 0;i < chessPieceCount;i++) {
		result.push_back(INT_MAX);
	}
	for (int i = 0;i < disSumVec.size();i++) {
		for (int j = 0;j < disSumVec[i].size();j++) {
			if (disSumVec[i][j] < result[j]) {
				result[j] = disSumVec[i][j];
			}
		}
	}
	minMoveResult = result;
}
int main() {
	int chessPieceCount = 4;
	std::vector<std::pair<int, int>> chessPiecePosition = {  };
	std::cin >> chessPieceCount;
	for (int i = 0;i < chessPieceCount;i++) {
		int temp = -1;
		std::cin >> temp;
		chessPiecePosition.push_back({ temp,-1 });
	}
	for (int i = 0;i < chessPieceCount;i++) {
		int temp = -1;
		std::cin >> temp;
		chessPiecePosition[i].second = temp;
	}
	std::vector<int> minMoveResult = {};
	getResult(chessPieceCount, chessPiecePosition, minMoveResult);
	for (auto& e : minMoveResult) {
		std::cout << e << " ";
	}
	return 0;
}