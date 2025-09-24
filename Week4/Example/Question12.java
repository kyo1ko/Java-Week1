package Week4.Example;

import java.util.Scanner;

public class Question12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("점수 네 개 순서대로 입력: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        if (a>90){
            System.out.println("A");
        }
        else if (a>80){
            System.out.println("B");
        }
        else if (a>70){
            System.out.println("C");
        }
        else if (a>60){
            System.out.println("D");
        }
        else{
            System.out.println("F");
        }
        if (b>90){
            System.out.println("A");
        }
        else if (b>80){
            System.out.println("B");
        }
        else if (b>70){
            System.out.println("C");
        }
        else if (b>60){
            System.out.println("D");
        }
        else{
            System.out.println("F");
        }
        if (c>90){
            System.out.println("A");
        }
        else if (c>80){
            System.out.println("B");
        }
        else if (c>70){
            System.out.println("C");
        }
        else if (c>60){
            System.out.println("D");
        }
        else{
            System.out.println("F");
        }
        if (d>90){
            System.out.println("A");
        }
        else if (d>80){
            System.out.println("B");
        }
        else if (d>70){
            System.out.println("C");
        }
        else if (d>60){
            System.out.println("D");
        }
        else{
            System.out.println("F");
        }
        double avg=(double)(a+b+c+d)/4;
        if (avg>=60){
            System.out.println("합격");
        }
        else{
            System.out.println("불합격");
        }
    }
}
