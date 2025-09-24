package Week4.Example;

import java.util.Scanner;

public class Question11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("첫 번째 숫자 입력: ");
        int a=sc.nextInt();
        System.out.println("두 번째 숫자 입력: ");
        int b=sc.nextInt();
        System.out.println("세 번째 숫자 입력: ");
        int c=sc.nextInt();
        if (a>b && a>c){
            System.out.println(a);
        }
        else if (b>a && b>c){
            System.out.println(b);
        }
        else if (c>a && c>b){
            System.out.println(c);
        }
        else{
            System.out.println("error");
        }
    }
}
