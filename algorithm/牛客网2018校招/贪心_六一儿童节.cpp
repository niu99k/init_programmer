/*
��һ��ͯ�ڣ���ʦ���˺ܶ�óԵ��ɿ������׶�԰��ÿ���ɿ���j������Ϊw[j]������ÿ��С����i�������ֵ����ɿ�����С�ﵽh[i] (��w[j]>=h[i])�����Ż���ȥ���ݽ�Ŀ����ʦ��Ŀ���ǽ��ɿ����ַ��������ǣ�ʹ������С����̨���ݡ����Ա�֤ÿ��w[i]> 0�Ҳ��ܽ�����ɿ����ָ�һ�����ӻ�һ��ָ�������ӡ�
*/
#include<iostream>
#include<vector>
#include<algorithm>
int main() {
    int childrenSize = 0;
    std::vector<int> childrenSatisfactionList = {};
    int chocolateSize = 0;
    std::vector<int> chocolateQualityList = {};
    int result = 0;
 
    std::cin >> childrenSize;
    for (int i = 0;i < childrenSize;i++) {
        int temp = 0;
        std::cin >> temp;
        childrenSatisfactionList.push_back(temp);
    }
    std::cin >> chocolateSize;
    for (int i = 0;i < chocolateSize;i++) {
        int temp = 0;
        std::cin >> temp;
        chocolateQualityList.push_back(temp);
    }
    std::sort(chocolateQualityList.begin(), chocolateQualityList.end());
    for (auto& e_children : childrenSatisfactionList) {
        int distributedChocolateQuality = -1;
        for (auto& e_chocolate : chocolateQualityList) {
            if (e_chocolate >= e_children) {
                result++;
                distributedChocolateQuality = e_chocolate;
                break;
            }
        }
        bool isDistributedChocolatePushed = false;
        std::vector<int> chocolateQualityListTemp = {};
        for (auto& e_chocolate : chocolateQualityList) {
            if (e_chocolate == distributedChocolateQuality && !isDistributedChocolatePushed) {
                isDistributedChocolatePushed = true;
                continue;
            }
            chocolateQualityListTemp.push_back(e_chocolate);
        }
        chocolateQualityList = chocolateQualityListTemp;
    }
    std::cout << result;
    return 0;
}