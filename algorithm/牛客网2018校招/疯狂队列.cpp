/*
��Ŀ����
С����ʦ�Ƿǳ�������,����Ҫ������ѧ���ڽ������ǰ���ų�һ��,������Ҫ��ѧ��������߲��ݼ���˳�����С���һ��,n��ѧ�����жӵ�ʱ��,С����ʦ����ȥ�������ˡ�ѧ���������л��ᷴ����,����ѧ���Ǿ�����һ�η��Ķ���,���Ƕ���һ�����еķ��ֵΪÿ����������ѧ����߲�ľ���ֵ�ܺ͡����ڰ������˳�����еĶ��еķ��ֵ����С��,���ǵ�Ȼ�������շ��ֵ����˳���������жӡ����ڸ���n��ѧ�������,��������Щѧ���жӵ������ܵķ��ֵ��С����ʦ����һ�������ð�����
��������:
�����������,��һ��һ������n(1 �� n �� 50),��ʾѧ��������
�ڶ���Ϊn������h[i](1 �� h[i] �� 1000),��ʾÿ��ѧ�������
�������:
���һ������,��ʾn��ѧ���жӿ��Ի�õ����ķ��ֵ��

��������ʾ:
����������˳����: 25-10-40-5-25, ��߲����ֵ���ܺ�Ϊ15+30+35+20=100��
�������ķ��ֵ�ˡ�
ʾ��1
����
����
5
5 10 25 40 25
���
����
100*/
#include<iostream>
#include<map>
#include<vector>
#include<algorithm>
int getMaxHeghtDelta(std::vector<int >heghtVec) {
	//����
	int maxHeghtDelta = 0;
	std::sort(heghtVec.begin(), heghtVec.end(), std::less<int>());
	int minHeightFlag = 0;
	int maxHeightFlag = heghtVec.size() - 1;
	//��������������������һ�����������
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

	//��������
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