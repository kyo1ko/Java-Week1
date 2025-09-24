package Week4.Example;

import java.util.Scanner;

public class Question7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("월(1-12) 입력: ");
        int month = sc.nextInt();
        switch (month){
            case 12,1,2:
                System.out.println("겨울");
                break;
            case 3,4,5:
                System.out.println("봄");
                break;
            case 6,7,8:
                 System.out.println("여름");
                 break;
            case 9,10,11:
                System.out.println("가을");
                break;
            default:
                System.out.println("Invalid month");
        }
    }
}
