/*
��Ŀ����
����
ţţ��һ���Թ��У��Թ��� n �����ӣ��� m ��ͨ����ÿ��ͨ�������������� uu, vv�����Ϊ uu �ĸ�������Ϊ vv �ĸ��ӿɻ��ൽ�ÿ��ÿ��ͨ��ֻ����һ�Ρ�
ţţ��֪�������Ƿ��ܴ� 11 �Ÿ��ӳ����ص� 11 �Ÿ��ӡ�
����
��һ�и����������� n , m ��
������ m �У�ÿ������������ u��v ��
1��n��100,0000��m��100,0000��L��m
m�� u, v ������ͬ

���
���ܻص� 11 �Ÿ����򷵻�Yes�����򷵻�No��
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
	 * �ܻص�1�ŵ㷵�� Yes�����򷵻� No
	 * @param param int����vector param[0] Ϊ n��param[1] Ϊ m
	 * @param edge Point��vector Point.x , Point.y �ֱ�Ϊһ���ߵ�������
	 * @return string�ַ���
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