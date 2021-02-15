/*
题目描述
给定一个数组arr，请将数组调整为依次相邻的数字，总是先<=、再>=的关系，并交替下去。比如数组中有五个数字，调整成[a,b,c,d,e],使之满足a<=b>=c<=d>=e。
输入描述:
输入包含两行，第一行一个整数n（1 \leq n \leq 10^5）（1≤n≤10
5
 ），代表数组的长度,接下来一行n个整数，代表数组arr（1 \leq arr_i \leq 10^9）（1≤arr
i
​
 ≤10
9
 ）。
输出描述:
输出一行，代表调整后的数组。
示例1
输入
复制
6
1 2 3 4 5 6
输出
复制
1 3 2 5 4 6
示例2
输入
复制
3
1 2 3
输出
复制
1 3 2
备注:
时间复杂度O（nlog_2n）O（nlog
2
​
 n）,空间复杂度O（1）O（1）。
 */
import java.util.*;
public class Main{
    static int arrayLen;
    static int[] array;
    static Scanner scanner;
    public static void main(String[]args){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void result(){
        sort();
        swapWithStep2();
        printResult();
    }
    private static void sort(){
        buildHeap();
        takeMaxAndRebuild();
    }
    private static void takeMaxAndRebuild(){
        int endIndex=arrayLen-1;
        while(endIndex>0){
            swap(0,endIndex);
            endIndex--;
            reBuild(endIndex);
        }
    }
    private static void reBuild(int endIndex){
        int index=0;
        while(true){
            final int leftIndex=(index+1)*2-1;
            final int rightIndex=leftIndex+1;
            if(rightIndex<=endIndex){
                if(array[leftIndex]>=array[rightIndex]&&array[leftIndex]>array[index]){
                    swap(leftIndex,index);
                    index=leftIndex;
                }else if(array[rightIndex]>=array[leftIndex]&&array[rightIndex]>array[index]){
                    swap(rightIndex,index);
                    index=rightIndex;
                }else{
                    break;
                }
            }else if(leftIndex<=endIndex){
                if(array[leftIndex]>array[index]){
                    swap(leftIndex,index);
                    index=leftIndex;
                }else{
                    break;
                }
            }else{
                break;
            }
        }
    }
    private static void buildHeap(){
        for(int i=lastLeafIndex();i>=0;i--){
            int index=i;
            int swapIndex=taskMaxAsRoot(index);
            while(swapIndex!=index){
                index=swapIndex;
                swapIndex=taskMaxAsRoot(index);
            }
        }
    }
    private static int taskMaxAsRoot(int index){
        int result=0;
        final int leftIndex=(index+1)*2-1;
        final int rightIndex=leftIndex+1;
        if(rightIndex<=arrayLen-1){
            if(array[leftIndex]>=array[rightIndex]&&array[leftIndex]>array[index]){
                swap(leftIndex,index);
                result=leftIndex;
            }else if(array[rightIndex]>=array[leftIndex]&&array[rightIndex]>array[index]){
                swap(rightIndex,index);
                result=rightIndex;
            }else{
                result=index;
            }
        }else if(leftIndex<=arrayLen-1){
            if(array[leftIndex]>array[index]){
                swap(leftIndex,index);
                result=leftIndex;
            }else{
                result=index;
            }
        }else{
            result=index;
        }
        return result;
    }
    private static void swap(int index1,int index2){
        final int temp=array[index1];
        array[index1]=array[index2];
        array[index2]=temp;
    }
    private static int lastLeafIndex(){
        int result;
        result=arrayLen/2-1;
        return result;
    }
    private static void swapWithStep2(){
        int beginIndex=2;
        while(beginIndex<=arrayLen-1){
            swap(beginIndex,beginIndex-1);
            beginIndex+=2;
        }
    }
    private static void printResult(){
        for(int i=0;i<arrayLen;i++){
            System.out.print(array[i]+" ");
        }
    }
    private static void init(){
        scanner=new Scanner(System.in);
        arrayLen=scanner.nextInt();
        array=new int[arrayLen];
        for(int i=0;i<arrayLen;i++){
            array[i]=scanner.nextInt();
        }
    }
}