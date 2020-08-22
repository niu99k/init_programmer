/*
六一儿童节，老师带了很多好吃的巧克力到幼儿园。每块巧克力j的重量为w[j]，对于每个小朋友i，当他分到的巧克力大小达到h[i] (即w[j]>=h[i])，他才会上去表演节目。老师的目标是将巧克力分发给孩子们，使得最多的小孩上台表演。可以保证每个w[i]> 0且不能将多块巧克力分给一个孩子或将一块分给多个孩子。
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