/*
题目描述
给定一个字符串str，判断是不是整体有效的括号字符串(整体有效：即存在一种括号匹配方案，使每个括号字符均能找到对应的反向括号，且字符串中不包含非括号字符)。
输入描述:
输入包含一行，代表str（ 1 \leq length_{str} \leq 10^5）（1≤length
str
​
 ≤10
5
 ）。
输出描述:
输出一行，如果str是整体有效的括号字符串，请输出“YES”，否则输出“NO”。
示例1
输入
复制
(())
输出
复制
YES
示例2
输入
复制
()a()
输出
复制
NO
说明
()a()中包含了 ‘a’，a不是括号字符
备注:
时间复杂度O（n）O（n），额外空间复杂度O（1）O（1）
 */
import java.util.*;
public class Main{
    static Scanner scanner;
    static String str;
    public static void main(String args[]){
        init();
        result();
        deInit();
    }
    private static void deInit(){
        scanner.close();
    }
    private static void result(){
        System.out.println(isValid()?"YES":"NO");
    }
    private static boolean isValid(){
        boolean result=true;
        Stack<Character> stack=new Stack();
        for(int i=0;i<str.length();i++){
            final char chr=str.charAt(i);
            if(chr!='('&&chr!=')'){
                result=false;
                break;
            }else if(chr=='('){
                stack.push(chr);
            }else{
                if(stack.isEmpty()){
                    result=false;
                    break;
                }else{
                    stack.pop();
                }
            }
        }
        if(!stack.isEmpty()){
            result=false;
        }
        return result;
    }
    private static void init(){
        scanner=new Scanner(System.in);
        str=scanner.next();
    }
}