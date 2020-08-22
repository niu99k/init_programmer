/*
题目描述
小易将n个棋子摆放在一张无限大的棋盘上。第i个棋子放在第x[i]行y[i]列。同一个格子允许放置多个棋子。每一次操作小易可以把一个棋子拿起并将其移动到原格子的上、下、左、右的任意一个格子中。小易想知道要让棋盘上出现有一个格子中至少有i(1 ≤ i ≤ n)个棋子所需要的最少操作次数.
输入描述:
输入包括三行,第一行一个整数n(1 ≤ n ≤ 50),表示棋子的个数
第二行为n个棋子的横坐标x[i](1 ≤ x[i] ≤ 10^9)
第三行为n个棋子的纵坐标y[i](1 ≤ y[i] ≤ 10^9)
输出描述:
输出n个整数,第i个表示棋盘上有一个格子至少有i个棋子所需要的操作数,以空格分割。行末无空格

如样例所示:
对于1个棋子: 不需要操作
对于2个棋子: 将前两个棋子放在(1, 1)中
对于3个棋子: 将前三个棋子放在(2, 1)中
对于4个棋子: 将所有棋子都放在(3, 1)中
示例1
输入
复制
4
1 2 4 9
1 1 1 1
输出
复制
0 1 3 10
*/
/*
思路
链接：https://www.nowcoder.com/questionTerminal/27f3672f17f94a289f3de86b69f8a25b?f=discussion
来源：牛客网

暴力枚举法居然过了，关键在于，最后堆棋子的那个格子，横纵坐标必然在棋子初始的横纵坐
标中间
用反证法，xy轴其实是独立的，先只考虑x坐标，假设把k个棋子堆到x0格子所用的步骤最少，
a个棋子初始在x0的左边，b个棋子初始在x0的右边，且a>b,那么必然存在横坐标为x0-1的格
子，这k个棋子到x0-1的步数会更少，b>a的情况，那么x0+1的目标将比x0更优，至于a=b，
x0-1 和x0的步数是一样的。因此，最终汇聚棋子的x坐标只要在棋子初始的x个坐标中考虑
*/
#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
#define INT_MAX 0x7fffffff
void getResult(int& chessPieceCount, std::vector<std::pair<int, int>>& chessPiecePosition, std::vector<int>& minMoveResult) {
	//初始化可能的点集
	std::vector< std::pair<int, int>  >possibleOpt = {};
	for (int i = 0;i < chessPiecePosition.size();i++) {
		for (int j = 0;j < chessPiecePosition.size();j++) {
			possibleOpt.push_back({ chessPiecePosition[i].first ,chessPiecePosition[j].second });
		}
	}
	//初始化每个棋子到可能点的距离
	//每个可能点到每个点距离排序
	std::vector<std::vector<int>> disVec = {};
	for (int i = 0;i < possibleOpt.size();i++) {
		std::vector<int> temp = {};
		for (int j = 0;j < chessPiecePosition.size();j++) {
			temp.push_back(std::abs(chessPiecePosition[j].first - possibleOpt[i].first) + std::abs(chessPiecePosition[j].second - possibleOpt[i].second));
		}
		std::sort(temp.begin(), temp.end(), std::less<int>());
		disVec.push_back(temp);
	}
	//初始化每个可能点到棋子的累加数值
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
	//排序给出答案
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