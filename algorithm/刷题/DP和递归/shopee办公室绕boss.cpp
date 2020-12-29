/*
shopee的办公室非常大，小虾同学的位置坐落在右上角，而大门却在左下角，可以把所有位置抽象为一个网格（门口的坐标为0，0），小虾同学很聪明，每次只向上，或者向右走，因为这样最容易接近目的地，但是小虾同学不想让自己的boss们看到自己经常在他们面前出没，或者迟到被发现。他决定研究一下如果他不通过boss们的位置，他可以有多少种走法？

输入描述:
第一行 x,y,n (0<x<=30, 0<y<=30, 0<=n<= 20) 表示x,y小虾的座位坐标,n 表示boss的数量（ n <= 20）

接下来有n行, 表示boss们的坐标(0<xi<= x, 0<yi<=y，不会和小虾位置重合)

x1, y1

x2, y2

……

xn, yn
输出描述:
输出小虾有多少种走法
*/
/*
用例:
30 30 20
28 14
17 2
6 15
23 12
7 30
23 16
20 7
3 22
14 16
6 9
12 24
25 15
30 9
22 22
19 4
1 20
5 8
20 12
9 20
24 10

对应输出应该为:

51378206950933300

你的输出为:

-25050316
*/
#include<iostream>
#include<vector>
#include<map>
#if 0
int getCount(std::map < int, std::map<int, int>> bossPositionMap, std::vector<std::vector<int>> initInfo, std::pair<int, int>position) {
	int firstCount = 0;
	int secondCount = 0;
	if (bossPositionMap[position.first][position.second] == 1) {
		return 0;
	}
	else if (position.first - 1 < 0 || position.second - 1 < 0) {
		return 1;
	}
	else {
		firstCount = getCount(bossPositionMap, initInfo, { position.first - 1 ,position.second });
		secondCount = getCount(bossPositionMap, initInfo, { position.first  ,position.second - 1 });
		return firstCount + secondCount;
	}
}
#endif
int main() {
	std::pair<int, int >target = {};
	std::vector<std::pair<int, int>> bossPosition = {};
	int bossCount = -1;
	std::cin >> target.first >> target.second >> bossCount;
	for (int i = 0;i < bossCount;i++) {
		std::pair<int, int> temp;
		std::cin >> temp.first >> temp.second;
		bossPosition.push_back(temp);
	}
	std::map < int, std::map<int, int>> bossPositionMap = {};
	for (auto& e : bossPosition) {
		bossPositionMap[e.first][e.second] = 1;
	}
	//初始化
	std::vector<std::vector<long long int>> initInfo = {};
	for (int i = 0;i <= target.first;i++) {
		std::vector<long long int> temp = {};
		for (int j = 0;j <= target.second;j++) {
			temp.push_back(-1);
		}
		initInfo.push_back(temp);
	}
	for (int i = 0;i <= target.first;i++) {
		initInfo[i][0] = 1;
	}
	for (int j = 0;j <= target.second;j++) {
		initInfo[0][j] = 1;
	}
	initInfo[0][0] = 0;
	for (int i = 0;i <= target.first;i++) {
		for (int j = 0;j <= target.second;j++) {
			if (initInfo[i][j] == -1) {
				if (bossPositionMap[i][j] == 1) {
					initInfo[i][j] = 0;
				}
				else {
					initInfo[i][j] = initInfo[i][j - 1] + initInfo[i - 1][j];
				}
			}
		}
	}

	//开始走路
	std::cout << initInfo[target.first][target.second];
	return 0;
}
