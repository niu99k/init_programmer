/*��Ŀ����
ţţ����ʦ������һ������Ķ���:����x �� y,[x, y]��ʾx��y֮��(����x��y)�����������������ϡ�����[3,3] = {3}, [4,7] = {4,5,6,7}.ţţ������һ������Ϊn�ĵ�������,ţţ��֪����Ҫ���ٸ����䲢��������������С�
����:
{1,2,3,4,5,6,7,8,9,10}����ֻ��Ҫ[1,10]��һ������
{1,3,5,6,7}����ֻ��Ҫ[1,1],[3,3],[5,7]����������
��������:
�����������,��һ��һ������n(1 �� n �� 50),
�ڶ���n������a[i](1 �� a[i] �� 50),��ʾţţ������,��֤�����ǵ����ġ�
�������:
���һ������,��ʾ�������������
ʾ��1
����
����
5
1 3 5 6 7
���
����
3*/
#include<iostream>
#include<map>
#include<vector>

int main() {
	int vecLength = 5;
	std::vector<int> numVec = { };
	std::cin >> vecLength;
	for (int i = 0;i < vecLength;i++) {
		int temp = 0;
		std::cin >> temp;
		numVec.push_back(temp);
	}
	int minVecCount = 1;
	for (int i = 0;i < numVec.size();i++) {
		if (i == 0) {
			continue;
		}
		else {
			if (numVec[i] - numVec[i - 1] != 1) {
				minVecCount++;
			}
		}
	}
	std::cout << minVecCount;
	return 0;
}
