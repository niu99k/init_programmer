/*
假设一个探险家被困在了地底的迷宫之中，要从当前位置开始找到一条通往迷宫出口的路径。迷宫可以用一个二维矩阵组成，有的部分是墙，有的部分是路。迷宫之中有的路上还有门，每扇门都在迷宫的某个地方有与之匹配的钥匙，只有先拿到钥匙才能打开门。请设计一个算法，帮助探险家找到脱困的最短路径。如前所述，迷宫是通过一个二维矩阵表示的，每个元素的值的含义如下 0-墙，1-路，2-探险家的起始位置，3-迷宫的出口，大写字母-门，小写字母-对应大写字母所代表的门的钥匙

迷宫的地图，用二维矩阵表示。第一行是表示矩阵的行数和列数M和N
后面的M行是矩阵的数据，每一行对应与矩阵的一行（中间没有空格）。M和N都不超过100, 门不超过10扇。
*/
#include<iostream>
#include<map>
#include<vector>
#include<queue>
#include<string>
#include <bitset>

#define UP 0
#define RIGHT 1
#define DOWN 2
#define LEFT 3
#define START '2'
#define ROAD '1'
#define TARGET '3'
#define BASE 2
#define WALL '0'
#define INT_MAX 0x7fffffff
int minStepCount = INT_MAX;

int transfer(std::string str) {
	std::bitset<11> bit(str);
	return bit.to_ullong();
}
#if 1
int getShortestPath(int mazeSize_x, int mazeSize_y, char maze[1024][1024], std::string keysStatus, int keysSize, std::pair<int, int>position, char target) {
	//访问索引
	int isVisitedMap[101][101][1025];
	int stepCountMap[101][101][1025];
	for (int i = 0;i < 101;i++) {
		for (int j = 0;j < 101;j++) {
			for (int k = 0;k < 1025;k++) {
				isVisitedMap[i][j][k] = 0;
				stepCountMap[i][j][k] = 0;
			}
		}
	}
	//访问队列
	std::queue < std::pair<std::pair<int, int>, std::string>> visitQueue;
	visitQueue.push({ position,keysStatus });
	isVisitedMap[position.first][position.second][transfer(keysStatus)] = 1;
	std::pair<int, int> positionTemp = {};
	std::pair<int, int> targetPosition = {};
	while (!visitQueue.empty()) {
		positionTemp = visitQueue.front().first;
		std::string status = visitQueue.front().second;
		visitQueue.pop();
		char element = maze[positionTemp.first][positionTemp.second];
		if (element == target) {
			targetPosition = positionTemp;
			continue;
		}
		else if (element == WALL) {
			continue;
		}
		else if (element >= 'A' && element <= 'Z' &&
			status[element - 'A'] == '0'
			) {
			continue;
		}
		else {
			int status_int = transfer(status);
			if (element >= 'a' && element <= 'z') {
				int originCount = stepCountMap[positionTemp.first][positionTemp.second][status_int];
				status[element - 'a'] = '1';
				status_int = transfer(status);
				stepCountMap[positionTemp.first][positionTemp.second][status_int] = originCount;
			}
			//相邻节点
			int adjList_x[4];
			int adjList_y[4];
			int x = positionTemp.first + 1;
			int y = positionTemp.second;
			int index = 0;
			if (x < mazeSize_x && x >= 0 &&
				y < mazeSize_y && y >= 0
				) {
				adjList_x[index] = x;
				adjList_y[index] = y;
				index++;
			}
			x = positionTemp.first - 1;
			y = positionTemp.second;
			if (x < mazeSize_x && x >= 0 &&
				y < mazeSize_y && y >= 0
				) {
				adjList_x[index] = x;
				adjList_y[index] = y;
				index++;
			}
			x = positionTemp.first;
			y = positionTemp.second + 1;
			if (x < mazeSize_x && x >= 0 &&
				y < mazeSize_y && y >= 0
				) {
				adjList_x[index] = x;
				adjList_y[index] = y;
				index++;
			}
			x = positionTemp.first;
			y = positionTemp.second - 1;
			if (x < mazeSize_x && x >= 0 &&
				y < mazeSize_y && y >= 0
				) {
				adjList_x[index] = x;
				adjList_y[index] = y;
				index++;
			}

			for (int i = 0;i < index;i++) {
				int adj_x = adjList_x[i];
				int adj_y = adjList_y[i];
				if (isVisitedMap[adj_x][adj_y][status_int] == 0) {
					isVisitedMap[adj_x][adj_y][status_int] = 1;
					if (stepCountMap[adj_x][adj_y][status_int] == 0) {
						visitQueue.push({ {adj_x,adj_y},status });
						stepCountMap[adj_x][adj_y][status_int] = stepCountMap[positionTemp.first][positionTemp.second][status_int] + 1;
					}
					else if (stepCountMap[adjList_x[i]][adjList_y[i]][status_int] > stepCountMap[positionTemp.first][positionTemp.second][status_int] + 1) {
						visitQueue.push({ {adj_x,adj_y},status });
						stepCountMap[adj_x][adj_y][status_int] = stepCountMap[positionTemp.first][positionTemp.second][status_int] + 1;
					}
				}
			}
		}
	}
	int minCout = INT_MAX;
	for (auto& e : stepCountMap[targetPosition.first][targetPosition.second]) {
		for (int i = 0;i < 1025;i++)
			if (stepCountMap[targetPosition.first][targetPosition.second][i] < minCout &&
				stepCountMap[targetPosition.first][targetPosition.second][i] != 0
				) {
				minCout = stepCountMap[targetPosition.first][targetPosition.second][i];
			}
	}
	return minCout;
}
#endif
int main() {
#if 1
	int mazeSize_x = 0;
	int mazeSize_y = 0;
	char maze[1024][1024];
	std::vector<std::pair<std::pair<int, int>, char>> keys = {};
	//邻接索引
	std::map<std::pair<int, int>, std::vector<std::pair<int, int>>> adjIndexMap = {};
	std::cin >> mazeSize_x;
	std::cin >> mazeSize_y;
	std::pair<int, int>startPosition = {};
	for (int i = 0;i < mazeSize_x;i++) {
		std::string lineTemp = "";
		std::cin >> lineTemp;
		for (int j = 0;j < mazeSize_y;j++) {
			char temp = 0;
			if (j >= lineTemp.size()) {
				temp = WALL;
			}
			else {
				temp = lineTemp.at(j);
			}
			if (temp >= 'a' && temp <= 'z') {
				keys.push_back({ { i,j }, temp });
			}
			else if (temp == START) {
				startPosition.first = i;
				startPosition.second = j;
			}
			//初始化矩阵
			maze[i][j] = temp;
		}
	}
	std::map<std::pair<int, int>, std::string> keysStatuses = {};
	std::string init = "";
	for (int i = 0;i < keys.size();i++) {
		init += "0";
	}
	for (int i = 0;i < mazeSize_x;i++) {
		for (int j = 0;j < mazeSize_y;j++) {
			keysStatuses[{i, j}] = init;
		}
	}
	int result = getShortestPath(mazeSize_x, mazeSize_y, maze, init, keys.size(), startPosition, TARGET);
	std::cout << result;

#endif
	return 0;
}