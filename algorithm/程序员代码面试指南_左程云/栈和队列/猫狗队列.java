/*
题目描述
实现一种猫狗队列的结构，要求如下：
1. 用户可以调用 add 方法将 cat 或者 dog 放入队列中
2. 用户可以调用 pollAll 方法将队列中的 cat 和 dog 按照进队列的先后顺序依次弹出
3. 用户可以调用 pollDog 方法将队列中的 dog 按照进队列的先后顺序依次弹出
4. 用户可以调用 pollCat 方法将队列中的 cat 按照进队列的先后顺序依次弹出
5. 用户可以调用 isEmpty 方法检查队列中是否还有 dog 或 cat
6. 用户可以调用 isDogEmpty 方法检查队列中是否还有 dog
7. 用户可以调用 isCatEmpty 方法检查队列中是否还有 cat
输入描述:
第一行输入一个整数 n 表示 用户的操作总次数。

以下 n行 每行表示用户的一次操作

每行的第一个参数为一个字符串 s，若 s = “add”， 则后面接着有 “cat x”（表示猫）或者“dog x”（表示狗），其中的 x 表示猫狗的编号。
输出描述:
对于每个操作：

若为 “add”，则不需要输出。

以下仅列举几个代表操作，其它类似的操作输出同理。

若为 “pollAll”，则将队列中的 cat 和 dog 按照进队列的先后顺序依次弹出。(FIFO)，格式见样例。

若为 "isEmpty"，则检查队列中是否还有 dog 或 cat， 为空则输出 “yes”， 否则输出 “no”。
示例1
输入
复制
11
add cat 1
add dog 2
pollAll
isEmpty
add cat 5
isDogEmpty
pollCat
add dog 10
add cat 199
pollDog
pollAll
输出
复制
cat 1
dog 2
yes
yes
cat 5
dog 10
cat 199
备注:
1 \le n \le 10000001≤n≤1000000
保证每个猫和狗的编号x都不相同且 1 \le x \le 10000001≤x≤1000000
保证没有不合法的操作
 */

import java.util.*;

public class Main {
    static class Animal {
        int count;
        int id;
        String name;

        void printId() {
            System.out.println(name + " " + id);
        }

        void debug() {
            System.out.println(count + " " + id + " " + name);
        }
    }

    static class Cat extends Animal {
        Cat() {
            count = time;
            time += 1;
            name = "cat";
        }
    }

    static class Dog extends Animal {
        Dog() {
            count = time;
            time += 1;
            name = "dog";
        }
    }

    static Scanner scanner;
    static int operationCount;
    static Queue<Cat> catQueue;
    static Queue<Dog> dogQueue;
    static int time;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        for (int i = 0; i < operationCount; i++) {
            String operation = scanner.next();
            switch (operation) {
                case "add":
                    add(scanner.next());
                    break;
                case "pollAll":
                    pollAll();
                    break;
                case "isEmpty":
                    isEmpty();
                    break;
                case "pollDog":
                    pollDog();
                    break;
                case "pollCat":
                    pollCat();
                    break;
                case "isDogEmpty":
                    isDogEmpty();
                    break;
                case "isCatEmpty":
                    isCatEmpty();
                    break;
                default:
                    break;
            }
        }
    }

    private static void isCatEmpty() {
        if (catQueue.isEmpty()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    private static void isDogEmpty() {
        if (dogQueue.isEmpty()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    private static void pollCat() {
        while (!catQueue.isEmpty()) {
            catQueue.poll().printId();
        }
    }

    private static void pollDog() {
        while (!dogQueue.isEmpty()) {
            dogQueue.poll().printId();
        }
    }

    private static void isEmpty() {
        if (catQueue.isEmpty() && dogQueue.isEmpty()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    private static void pollAll() {
        while (!catQueue.isEmpty() || !dogQueue.isEmpty()) {
            if (catQueue.isEmpty()) {
                dogQueue.poll().printId();
            } else if (dogQueue.isEmpty()) {
                catQueue.poll().printId();
            } else {
                if (dogQueue.peek().count > catQueue.peek().count) {
                    catQueue.poll().printId();
                } else {
                    dogQueue.poll().printId();
                }
            }
        }
    }

    private static void add(String animalName) {
        switch (animalName) {
            case "cat":
                Cat cat = new Cat();
                cat.id = scanner.nextInt();
                catQueue.offer(cat);
                break;
            case "dog":
                Dog dog = new Dog();
                dog.id = scanner.nextInt();
                dogQueue.offer(dog);
                break;
        }
    }

    private static void init() {
        time = 0;
        scanner = new Scanner(System.in);
        catQueue = new LinkedList<>();
        dogQueue = new LinkedList<>();
        operationCount = scanner.nextInt();
    }
}