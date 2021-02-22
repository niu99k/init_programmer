/*
题目描述
给定一个字符串str，str表示一个公式，公式里可以有整数，加减乘除和左右括号，返回公式的计算结果（注意：题目中所有运算都是整型运算，向下取整,且保证数据合法，不会出现除0等情况）。
输入描述:
输出一行字符串，代表str（1 \leq length_{str} \leq 1000 ）（1≤length
str
​
 ≤1000）（保证str计算的结果不会出现除零，int溢出等情况）。
输出描述:
输出一个整数，代表表达式的计算结果。
示例1
输入
复制
48*((70-65)-43)+8*1
输出
复制
-1816
示例2
输入
复制
3+1*4
输出
复制
7
 */
import java.util.*;
public class Main{
    static Scanner scanner;
    static String string;
    public static void main(String[]args){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void result(){
        System.out.println(solve(0)[0]);
    }
    private static int[] solve(int index){
        int[] result=new int[2];
        int pre=0;
        Stack<String> stack=new Stack<>();
        while(index<string.length()){
            final char chr=string.charAt(index);
            if(chr==')'){
                index++;
                break;
            }else if(chr>='0'&&chr<='9'){
                pre=pre*10+(chr-'0');
                index++;
            }else if(chr!='('){
                addNum(stack,pre);
                stack.push(chr+"");
                index++;
                pre=0;
            }else{
                int []bracketResult=solve(index+1);
                index=bracketResult[1];
                pre=bracketResult[0];
            }
        }
        addNum(stack,pre);
        result[0]=addAndSub(stack);
        result[1]=index;
        return result;
    }
    private static int addAndSub(Stack<String>stack){
        int result=0;
        Stack<String> stack2=new Stack<>();
        while(!stack.isEmpty()){
            stack2.push(stack.pop());
        }
        result=Integer.valueOf(stack2.pop());
        while(!stack2.isEmpty()){
            final String str=stack2.pop();
            if(str.equals("+")){
                result+=Integer.valueOf(stack2.pop());
            }else if(str.equals("-")){
                result-=Integer.valueOf(stack2.pop());
            }
        }
        return result;
    }
    private static void addNum(Stack<String> stack,int num){
        if(!stack.isEmpty()){
            final String str=stack.peek();
            if(str.equals("*")||str.equals("/")){
                stack.pop();
                int preNum=Integer.valueOf(stack.pop());
                num=str.equals("*")?preNum*num:preNum/num;
            }
        }
        stack.push(num+"");
    }
    private static void init(){
        scanner=new Scanner(System.in);
        string=scanner.next();
    }
}