package Week3;

public class Ex {
    public static void main(String[] args) {
        int a = 10;
        int b = a;
        b = 20;
        System.out.println(a);

        String str1 = "Hello";
        String str2 = str1;
        str2 = "World";
        System.out.println(str1);

        int[] array1 = {1, 2, 3};
        int[] array2 = array1;
        array2[0] = 100;
        System.out.println(array1[0]);

        int pi = 3;
        double approximatePi = pi;
        System.out.println(approximatePi);

        char letter = 'A';
        int asciiValue = letter;
        System.out.println("The ASCII value of " + letter + " is: " + asciiValue);

        int temperature = 20;  // 변수 선언 및 초기화
        final int FREEZING_POINT = 0;  // 상수 선언 및 초기화

        // 변수 값 변경
        temperature = 25;  // 'temperature' 변수의 값을 25로 변경
        System.out.println("Current temperature: " + temperature);

        // 상수 값 변경 시도 (컴파일 오류 발생)
        //FREEZING_POINT = 10;  // 이 코드는 컴파일 오류를 발생시킵니다.

        System.out.println("Freezing point of water: " + FREEZING_POINT);

        String name="뽀로로";
        int age=22;
        String hobby="노는 게 제일 좋아";

        System.out.println(name + "(" + age + ")세 " + hobby);
    }
}
