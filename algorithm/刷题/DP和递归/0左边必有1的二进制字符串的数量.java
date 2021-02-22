/*
题目描述
给定一个整数n，求由“0”字符和“1”字符组成的长度为n的所有字符串中，满足“0”字符的左边必有“1”字符的字符串的数量。
输入描述:
输入一行，包含一个整数n（1 \leq n \leq2*10^7）（1≤n≤2∗10
7
 ）。
输出描述:
输出一个整数，表示返回的答案，由于字符串的数量巨大，可能会溢出，请输出对2^{29}2
29
 取模后的答案。
示例1
输入
复制
1
输出
复制
1
说明
只有“1”满足
示例2
输入
复制
2
输出
复制
2
说明
只有“10”和“11”满足
示例3
输入
复制
3
输出
复制
3
说明
只有“101”，“110”，“111”满足
备注:
时间复杂度O（log_2n）O（log
2
​
 n）。额外空间复杂度O（1）O（1）。
 */
import java.util.*;
public class Main{
    static Scanner scanner;
    static long strLen;
    static long mod=1<<29;
    public static void main(String[]args){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void result(){
        if(strLen==1){
            System.out.println(1);
        }else if(strLen==2){
            System.out.println(2);
        }else{
            Map<Long,long[][]> matrixMap=matrixMap();
            System.out.println(calcResult(matrixMap));
        }
    }
    private static long calcResult(Map<Long,long[][]>matrixMap){
        long result;
        long[][] finalMatrix=finalMatrix(matrixMap);
        result=(2*finalMatrix[0][0]+1*finalMatrix[1][0])%mod;
        return result;
    }
    private static long[][] finalMatrix(Map<Long,long[][]>matrixMap){
        long [][] result;
        Set<Long> matrixKeySet=matrixMap.keySet();
        Stack<Long> multiList=new Stack<>();
        long total=strLen-2;
        while(total>0){
            final long temp=total;
            long maxLess=matrixKeySet.stream()
                    .filter(muti->muti<=temp)
                    .max((o1,o2)->{return o1-o2<0?-1:1;})
                    .get();
            multiList.push(maxLess);
            total-=maxLess;
        }
        result=matrixMap.get(multiList.pop());
        while(!multiList.isEmpty()){
            result=maxtrixMuti(result,matrixMap.get(multiList.pop()));
        }
        return result;
    }
    private static Map<Long,long[][]> matrixMap(){
        Map<Long,long[][]> result=new HashMap<>();
        long count=1L;
        long[][] baseMatrix={{1,1},{1,0}};
        result.put(1L,baseMatrix);
        while(count<=10000000){
            count*=2;
            long[][] maxtrixMuti=maxtrixMuti(baseMatrix,baseMatrix);
            baseMatrix=maxtrixMuti;
            result.put(count,maxtrixMuti);
        }
        return result;
    }
    private static long[][] maxtrixMuti(long[][] matrix1,long[][]matrix2){
        long[][] result=new long[2][2];
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                result[i][j]=rowMultiColum(i,j,matrix1,matrix2);
            }
        }
        return result;
    }
    private static long rowMultiColum(int row,int column,long [][] matrix1,long[][]matrix2){
        long result=0L;
        for(int i=0;i<2;i++){
            result=(result+(matrix1[row][i]*matrix2[i][column])%mod)%mod;
        }
        return result;
    }
    private static void init(){
        scanner=new Scanner (System.in);
        strLen=scanner.nextLong();
    }
}