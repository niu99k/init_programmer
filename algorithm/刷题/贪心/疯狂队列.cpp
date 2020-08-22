/*
题目描述
小易老师是非常严厉的,它会要求所有学生在进入教室前都排成一列,并且他要求学生按照身高不递减的顺序排列。有一次,n个学生在列队的时候,小易老师正好去卫生间了。学生们终于有机会反击了,于是学生们决定来一次疯狂的队列,他们定义一个队列的疯狂值为每对相邻排列学生身高差的绝对值总和。由于按照身高顺序排列的队列的疯狂值是最小的,他们当然决定按照疯狂值最大的顺序来进行列队。现在给出n个学生的身高,请计算出这些学生列队的最大可能的疯狂值。小易老师回来一定会气得半死。
输入描述:
输入包括两行,第一行一个整数n(1 ≤ n ≤ 50),表示学生的人数
第二行为n个整数h[i](1 ≤ h[i] ≤ 1000),表示每个学生的身高
输出描述:
输出一个整数,表示n个学生列队可以获得的最大的疯狂值。

如样例所示:
当队列排列顺序是: 25-10-40-5-25, 身高差绝对值的总和为15+30+35+20=100。
这是最大的疯狂值了。
示例1
输入
复制
5
5 10 25 40 25
输出
复制
100*/
#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
int getMaxHeghtDelta(std::vector<int >heghtVec) {
	//排序
	int maxHeghtDelta = 0;
	std::sort(heghtVec.begin(), heghtVec.end(), std::less<int>());
	int minHeightFlag = 0;
	int maxHeightFlag = heghtVec.size() - 1;
	//交叉往疯狂队列里放数最后一个数看情况放
	std::vector<int> result = {};
	for (int i = 0;i < heghtVec.size() + 2;i++) {
		result.push_back(0);
	}

	int rightIndex = (heghtVec.size() + 2) / 2;
	int leftIndex = rightIndex - 1;
	int flag = 0;
	int maxFlag = heghtVec.size() - 1;
	int minFlag = 0;
	while (maxFlag > minFlag) {
		if (flag == 0) {
			flag = 1;
			result[leftIndex] = heghtVec[minFlag];
			result[rightIndex] = heghtVec[maxFlag];
		}
		else {
			flag = 0;
			result[leftIndex] = heghtVec[maxFlag];
			result[rightIndex] = heghtVec[minFlag];
		}
		leftIndex--;
		rightIndex++;
		minFlag++;
		maxFlag--;
	}
	int lastNum = 0;
	int restDelta = 0;
	if (maxFlag == minFlag) {
		lastNum = heghtVec[maxFlag];
		if (std::abs(lastNum - heghtVec[maxFlag - 1]) > std::abs(lastNum - heghtVec[maxFlag + 1])) {
			restDelta = std::abs(lastNum - heghtVec[maxFlag - 1]);
		}
		else {
			restDelta = std::abs(lastNum - heghtVec[maxFlag + 1]);
		}
	}

	//计算数组
	for (int i = 0;i < result.size();i++) {
		if (i == 0) {
			continue;
		}
		else if (result[i - 1] == 0 || result[i] == 0) {
			continue;
		}
		else {
			maxHeghtDelta += std::abs(result[i] - result[i - 1]);
		}
	}
	return maxHeghtDelta + restDelta;
}
int main() {
	int studentCount = 0;
	std::cin >> studentCount;
	std::vector<int >heghtVec = {};
	for (int i = 0;i < studentCount;i++) {
		int temp = -1;
		std::cin >> temp;
		heghtVec.push_back(temp);
	}
	int maxHeghtDelta = getMaxHeghtDelta(heghtVec);
	std::cout << maxHeghtDelta;
	return 0;
}