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
public class Main {
    static int arrayLen;
    static int[] array;
    static Scanner scanner;
    static int maxNum=1000000000;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }
    private static void result(){
        sort();
        shuffle();
        printResult();
    }
    private static void sort(){
        buildHeap();
        takeMaxAndAdjust();
    }
    private static void takeMaxAndAdjust(){
        int endIndex=arrayLen-1;
        swap(endIndex,0);
        endIndex--;
        while(endIndex>0){
            adjustHeap(endIndex);
            swap(endIndex,0);
            endIndex--;
        }
    }
    private static void adjustHeap(int endIndex){
        int adjustIndex=0;
        while(true){
            final int maxIndex=maxIndex(adjustIndex,endIndex);
            if(maxIndex==adjustIndex){
                break;
            }else{
                swap(maxIndex,adjustIndex);
                adjustIndex=maxIndex;
            }
        }
    }
    private static int maxIndex(int adjustIndex,int endIndex){
        int result;
        final int leftIndex=(adjustIndex+1)*2-1;
        final int rightIndex=leftIndex+1;
        if(rightIndex<=endIndex){
            final int childMaxIndex=array[rightIndex]>array[leftIndex]?rightIndex:leftIndex;
            result=array[adjustIndex]>array[childMaxIndex]?adjustIndex:childMaxIndex;
        }else if(leftIndex<=endIndex){
            result=array[leftIndex]>array[adjustIndex]?leftIndex:adjustIndex;
        }else{
            result=adjustIndex;
        }
        return result;
    }
    private static void buildHeap(){
        for(int i=lastLeafIndex();i>=0;i--){
            changeMaxAsRoot(i);
        }
    }
    private  static void changeMaxAsRoot(int rootIndex){
        final int leftIndex=(rootIndex+1)*2-1;
        final int rightIndex=leftIndex+1;
        if(rightIndex>=arrayLen-1){
            if(array[leftIndex]>array[rootIndex]){
                swap(leftIndex,rootIndex);
            }
        }else{
            if(array[leftIndex]>array[rootIndex]&&array[leftIndex]>=array[rightIndex]){
                swap(leftIndex,rootIndex);
            }else if(array[rightIndex]>array[rootIndex]&&array[rightIndex]>=array[leftIndex]){
                swap(rightIndex,rootIndex);
            }
        }
    }
    private static void swap(int index1,int index2){
        int temp=array[index1];
        array[index1]=array[index2];
        array[index2]=temp;
    }
    private static int lastLeafIndex(){
        int result=0;
        result=(arrayLen/2)-1;
        return result;
    }
    private static void shuffle(){
        if(arrayLen%2==0){
            reverseHalf(0,arrayLen-1);
            shuffle(0,arrayLen-1);
        }else{
            reverseHalf(1,arrayLen-1);
            shuffle(1,arrayLen-1);
        }
    }
    private static void reverseHalf(int beginIndex,int endIndex){
        final int len=endIndex-beginIndex+1;
        reverse(beginIndex,beginIndex+len/2-1);
        reverse(endIndex-len/2+1,endIndex);
        reverse(beginIndex,endIndex);
    }
    private static void reverse(int beginIndex,int endIndex){
        while(endIndex>beginIndex){
            swap(beginIndex,endIndex);
            endIndex--;
            beginIndex++;
        }
    }
    private static void shuffle(int beginIndex,int endIndex){
        List<Integer> shuffleMap=shuffleMap();
        while(beginIndex<=endIndex){
            final int len=endIndex-beginIndex+1;
            final int maxShuffleCount=maxShuffleCount(len,shuffleMap);
            reversePart(beginIndex,endIndex,maxShuffleCount);
            push(beginIndex,endIndex,maxShuffleCount,shuffleMap);
            beginIndex+=(maxShuffleCount);
        }
    }
    private static void reversePart(int beginIndex,int endIndex,int maxShuffleCount){
        int beginReverseIndex=beginIndex+maxShuffleCount/2;
        int splitIndex=beginIndex+(endIndex-beginIndex+1)/2-1;
        int endReverseIndex=splitIndex+maxShuffleCount/2;
        reverse(beginReverseIndex,splitIndex);
        reverse(splitIndex+1,endReverseIndex);
        reverse(beginReverseIndex,endReverseIndex);
    }
    private static void push(int beginIndex,int endIndex,int maxShuffleCount,List<Integer> shuffleMap){
        for(int i=0;i<pushCount(maxShuffleCount,shuffleMap);i++){
            push(beginIndex,pushBeginIndex(beginIndex,i),maxShuffleCount);
        }
    }
    private static void push(int pushArrayBeginIndex,int beginIndex,int maxShuffleCount){
        final int endIndex=beginIndex;
        int startIndex=nextPushIndex(pushArrayBeginIndex,beginIndex,maxShuffleCount);
        int temp1=array[beginIndex];
        while(startIndex!=endIndex){
            int temp2=array[startIndex];
            array[startIndex]=temp1;
            temp1=temp2;
            startIndex=nextPushIndex(pushArrayBeginIndex,startIndex,maxShuffleCount);
        }
        array[startIndex]=temp1;
    }
    private static int nextPushIndex(int pushArrayBeginIndex,int beginIndex,int maxShuffleCount){
        int result;
        final int splitIndex=maxShuffleCount/2;
        int mapIndex=beginIndex-pushArrayBeginIndex;
        if(mapIndex>=splitIndex){
            result=pushArrayBeginIndex+(mapIndex-splitIndex)*2;
        }else{
            result=pushArrayBeginIndex+mapIndex*2+1;
        }
        return result;
    }
    private static int pushBeginIndex(int beginIndex,int index){
        int result;
        int base=1;
        for(int i=0;i<index;i++){
            base*=3;
        }
        result=beginIndex+base-1;
        return result;
    }
    private static int pushCount(int maxShuffleCount,List<Integer> shuffleMap){
        int result=0;
        for(int i=0;i<shuffleMap.size();i++){
            if(maxShuffleCount==shuffleMap.get(i)){
                result=i+1;
                break;
            }
        }
        return result;
    }
    private static int maxShuffleCount(int len,List<Integer> shuffleMap){
        int result;
        result=shuffleMap.stream()
                .filter(num->num<=len)
                .max((o1,o2)->{return o1-o2;})
                .get();
        return result;
    }
    private static List<Integer> shuffleMap(){
        List<Integer> result=new ArrayList<>();
        long baseNum=1;
        while(baseNum*3-1<=maxNum){
            result.add((int)(baseNum*3-1));
            baseNum*=3;
        }
        return result;
    }
    private static void printResult(){
        for(int i=0;i<arrayLen;i++){
            System.out.print(array[i]+" ");
        }
    }
    private static void deInit(){
        scanner.close();
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