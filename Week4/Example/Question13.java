package Week4.Example;

import java.util.Scanner;

public class Question13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        System.out.print("첫 번째 숫자: ");
        int n1 = sc.nextInt();
        System.out.print("첫 번째 연산자(+,-,*,/): ");
        char op1 = sc.next().charAt(0);
        System.out.print("두 번째 숫자: ");
        int n2 = sc.nextInt();
        System.out.print("두 번째 연산자(+,-,*,/): ");
        char op2 = sc.next().charAt(0);
        System.out.print("세 번째 숫자: ");
        int n3 = sc.nextInt();

        double result = 0;  // 결과값

        // 1. op1이 *, / 인 경우 먼저 처리
        switch (op1) {
            case '*':
                double firstMul = n1 * n2;
                switch (op2) {
                    case '+': result = firstMul + n3; break;
                    case '-': result = firstMul - n3; break;
                    case '*': result = firstMul * n3; break;
                    case '/': result = firstMul / n3; break;
                }
                break;

            case '/':
                double firstDiv = (double) n1 / n2;
                switch (op2) {
                    case '+': result = firstDiv + n3; break;
                    case '-': result = firstDiv - n3; break;
                    case '*': result = firstDiv * n3; break;
                    case '/': result = firstDiv / n3; break;
                }
                break;

            // 2. op1이 +, - 인 경우 → op2가 * 또는 /라면 그걸 먼저 계산
            default:
                double mid = 0;
                switch (op2) {
                    case '*': mid = n2 * n3; break;
                    case '/': mid = (double) n2 / n3; break;
                    case '+': mid = n2 + n3; break;
                    case '-': mid = n2 - n3; break;
                }

                switch (op1) {
                    case '+': result = n1 + mid; break;
                    case '-': result = n1 - mid; break;
                }
        }

        System.out.println("결과: " + result);
    }
}
