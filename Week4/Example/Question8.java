package Week4.Example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Question8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("키 입력: ");
        int cm=sc.nextInt();
        System.out.println("몸무게 입력: ");
        int kg=sc.nextInt();
        double m=cm/100.0;
        double bmi= (double) kg /(m*m);
        if (bmi<18.5){
            System.out.println("저체중");
        }
        else if (bmi<25){
            System.out.println("정상");
        }
        else if (bmi<30){
            System.out.println("과체중");
        }
        else{
            System.out.println("비만");
        }
    }
}
