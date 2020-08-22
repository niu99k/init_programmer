/*
题目描述
题意
牛牛在一个迷宫中，迷宫有 n 个格子，有 m 条通道，每条通道连接两个格子 uu, vv，编号为 uu 的格子与编号为 vv 的格子可互相到达，每人每条通道只能走一次。
牛牛想知道，他是否能从 11 号格子出发回到 11 号格子。
输入
第一行给定两个整数 n , m 。
接下来 m 行，每行有两个整数 u，v 。
1≤n≤100,0000≤m≤100,0000≤L≤m
m对 u, v 互不相同

输出
若能回到 11 号格子则返回Yes，否则返回No。
*/
#include<iostream>
#include<vector>
#include<map>
#include <string>

struct Point {
	int x;
	int y;
	Point(int x, int y) {
		this->x = x;
		this->y = y;
	}
};

class Solution {
public:
	/**
	 * 能回到1号点返回 Yes，否则返回 No
	 * @param param int整型vector param[0] 为 n，param[1] 为 m
	 * @param edge Point类vector Point.x , Point.y 分别为一条边的两个点
	 * @return string字符串
	 */
	std::string solve(std::vector<int>& param, std::vector<Point>& edge) {
		int m = param[0];
		int n = param[1];
		std::map<int, int> exploredMap = {};
		std::vector<int> stackRecord = {};
		std::map<int, std::vector<int>> graph = {};
		for (int i = 0;i < m;i++) {
			int temp1 = edge[i].x;
			int temp2 = edge[i].y;
			std::vector<int> tempVec = graph[temp1];
			bool flag = false;
			for (int j = 0;j < tempVec.size();j++) {
				if (tempVec[j] == temp2) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				tempVec.push_back(temp2);
				graph[temp1] = tempVec;
			}
			tempVec = graph[temp2];
			flag = false;
			for (int j = 0;j < tempVec.size();j++) {
				if (tempVec[j] == temp1) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				tempVec.push_back(temp1);
				graph[temp2] = tempVec;
			}
		}

		int arc = 1;
		std::vector<int> tempVec = graph[arc];
		bool isLoop = false;
		bool isNextArcExist = false;
		while (true) {
			if (isNextArcExist && !stackRecord.empty()) {
				arc = stackRecord[stackRecord.size() - 1];
				stackRecord.pop_back();
				tempVec = graph[arc];
			}
			isNextArcExist = false;
			for (auto& e : tempVec) {
				if (exploredMap[e] == 0 && !isNextArcExist) {
					arc = e;
					exploredMap[e] = 1;
					isNextArcExist = true;
				}
				else if (exploredMap[e] == 0) {
					stackRecord.push_back(e);
				}
			}
			if (arc == 1) {
				isLoop = true;
				break;
			}
			if (!isNextArcExist && stackRecord.size() == 0) {
				break;
			}
			tempVec = graph[arc];
		}
		if (isLoop) {
			return  "Yes";
		}
		else {
			return "No";
		}
	}

};
int main() {
	Solution solution;
	std::vector<int> param = {};
	std::vector<Point> edge = {};
	param.push_back(12);
	param.push_back(4);
	edge.push_back(Point(7, 11));
	edge.push_back(Point(3, 2));
	edge.push_back(Point(5, 1));
	edge.push_back(Point(1, 6));
	edge.push_back(Point(6, 4));
	edge.push_back(Point(7, 2));
	edge.push_back(Point(7, 4));
	edge.push_back(Point(4, 2));
	edge.push_back(Point(1, 3));
	edge.push_back(Point(6, 3));
	edge.push_back(Point(3, 7));
	edge.push_back(Point(5, 6));
	std::cout << solution.solve(param, edge);
	return 0;
}