/*
存在n+1个房间，每个房间依次为房间1 2 3...i，每个房间都存在一个传送门，i房间的传送门可以把人传送到房间pi(1<=pi<=i),现在路人甲从房间1开始出发(当前房间1即第一次访问)，每次移动他有两种移动策略：
	A. 如果访问过当前房间 i 偶数次，那么下一次移动到房间i+1；
	B. 如果访问过当前房间 i 奇数次，那么移动到房间pi；
现在路人甲想知道移动到房间n+1一共需要多少次移动；
输入描述:
第一行包括一个数字n(30%数据1<=n<=100，100%数据 1<=n<=1000)，表示房间的数量，接下来一行存在n个数字 pi(1<=pi<=i), pi表示从房间i可以传送到房间pi。
输出描述:
输出一行数字，表示最终移动的次数，最终结果需要对1000000007 (10e9 + 7) 取模。
示例1
输入
复制
2
1 2
输出
复制
4
说明
开始从房间1 只访问一次所以只能跳到p1即 房间1， 之后采用策略A跳到房间2，房间2这时访问了一次因此采用策略B跳到房间2，之后采用策略A跳到房间3，因此到达房间3需要 4 步操作。
*/
#include<iostream>
#include<vector>
#include<map>
#define MOD 1000000007
using namespace std;

void initDpMap(vector<long long>& dpVec, map<int, long long>& doorIndex) {
	for (auto& e : doorIndex) {
		if (e.first == e.second) {
			dpVec.push_back(1);
		}
		else {
			int total = 0;
			int beginIndex = e.second;
			for (int i = beginIndex;i < dpVec.size();i++) {
				total = (total + dpVec[i]) % MOD;
			}
			total = (total + dpVec.size() - beginIndex + 1) % MOD;
			dpVec.push_back(total);
		}
	}
}
int main() {
	int numCount = 0;
	//表示从奇数到偶数的步数
	vector<long long> dpVec;
	map<int, long long> doorIndex;
	cin >> numCount;
	for (int i = 0;i < numCount;i++) {
		int temp = 0;
		cin >> temp;
		doorIndex[i] = temp - 1;
	}

	initDpMap(dpVec, doorIndex);
	int totalCount = 0;
	for (auto& e : dpVec) {
		totalCount = (totalCount + e) % MOD;
	}
	totalCount = (totalCount + dpVec.size()) % MOD;
	cout << totalCount;


	return 0;

}