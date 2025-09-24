package Week4.Example;

import java.util.Scanner;

public class Question4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("첫 번째 숫자 입력: ");
        int a = sc.nextInt();
        System.out.print("두 번째 숫자 입력: ");
        int b = sc.nextInt();
        System.out.println("연산자 입력: ");
        String op = sc.next();
        switch (op){
            case "+":
                System.out.println(a+b);
                break;
            case "-":
                System.out.println(a-b);
                break;
            case "*":
                 System.out.println(a*b);
                 break;
            case "/":
                 System.out.println(a/b);
                 break;
            default:
                 System.out.println("Invalid Input");
        }
    }
}
