package Week6;

import java.util.Scanner;
import java.util.ArrayList;

// 자판기 프로그램을 실행하는 메인 클래스
public class DrinkMachine {
    public static void main(String[] args) {
        // AdvancedMachine 클래스의 인스턴스(객체) 생성
        AdvancedMachine machine = new AdvancedMachine();
        // 자판기 프로그램 실행
        machine.run();
    }
}

// 음료 정보를 담는 클래스
class Drink {
    String maker;   // 제조사
    String name;    // 음료 이름
    int price;      // 가격
    String temp;    // 음료 온도 ("Hot", "Cold")
    int quantity;   // 재고 수량

    // 기본 생성자
    Drink() {}

    /*
     * 음료의 모든 정보를 초기화하는 생성자
     * maker: 제조사
     * name: 음료 이름
     * price: 가격
     * temp: 음료 온도
     * quantity: 초기 재고 수량
     */
    Drink(String maker, String name, int price, String temp, int quantity) {
        this.maker = maker;
        this.name = name;
        this.price = price;
        this.temp = temp;
        this.quantity = quantity;
    }

    /*
     * 재고가 남아있는지 확인하는 메소드
     * 재고가 있으면 true, 없으면 false를 반환
     */
    public boolean hasStock() {
        return quantity > 0;
    }

    // 음료가 판매될 때 재고를 1 감소시킨다.
    public void decreaseStock() {
        if (hasStock()) {
            this.quantity--;
        }
    }

    /*
     * 음료의 정보를 문자열 형태로 반환
     * 재고가 없으면 "(품절)" 표시를 추가하며
     * "이름(온도) - 가격원 [재고: n개]" 형식으로 반환
     */
    @Override
    public String toString() {
        String stockStatus = hasStock() ? "[재고: " + quantity + "개]" : "[품절]";
        return name + "(" + temp + ") - " + price + "원 " + stockStatus;
    }
}

// 자판기의 작동 로직을 담당하는 클래스
class AdvancedMachine {
    // ArrayList를 사용하여 음료 목록을 유연하게 관리
    private ArrayList<Drink> drinks;
    private int insertedMoney;
    private Scanner scanner;
    private final String ADMIN_PASSWORD = "admin"; // 관리자 모드 비밀번호

    // AdvancedMachine 클래스의 생성자
    // 자판기가 시작될 때 필요한 초기 설정
    public AdvancedMachine() {
        scanner = new Scanner(System.in);
        insertedMoney = 0;
        initializeDrinks(); // 음료 목록 초기화 메서드 호출
    }

    // 자판기에 판매할 음료를 초기화하고 추가
    private void initializeDrinks() {
        drinks = new ArrayList<>(); // ArrayList 생성

        // --- 차가운 음료 ---
        drinks.add(new Drink("코카콜라", "코카콜라", 1200, "Cold", 10));
        drinks.add(new Drink("롯데칠성", "칠성사이다", 1100, "Cold", 8));
        drinks.add(new Drink("동아오츠카", "포카리스웨트", 1000, "Cold", 12));
        drinks.add(new Drink("해태", "갈아만든배", 1300, "Cold", 5));
        drinks.add(new Drink("웅진", "초록매실", 900, "Cold", 7));
        drinks.add(new Drink("광동", "비타500", 800, "Cold", 15));

        // --- 따뜻한 음료 ---
        drinks.add(new Drink("농심", "레쓰비", 700, "Hot", 10));
        drinks.add(new Drink("동서식품", "맥심 모카골드", 600, "Hot", 20));
        drinks.add(new Drink("담터", "율무차", 800, "Hot", 8));
        drinks.add(new Drink("롯데", "칸타타", 1000, "Hot", 6));
    }

    // 자판기 프로그램을 실행하는 메인 루프
    public void run() {
        while (true) {
            displayMenu(); // 사용자 모드 또는 관리자 모드 선택
            System.out.println("\n====================================\n");
        }
    }

    // 초기 메뉴(사용자/관리자)를 표시
    private void displayMenu() {
        System.out.println("--- 어서오세요, 대림 자판기입니다.  ---");
        System.out.println("1. 음료 구매");
        System.out.println("2. 관리자 모드");
        System.out.println("3. 프로그램 종료");
        System.out.print("메뉴를 선택하세요: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                purchaseProcess(); // 음료 구매 프로세스 시작
                break;
            case 2:
                adminMode(); // 관리자 모드 시작
                break;
            case 3:
                System.out.println("프로그램을 종료합니다. 안녕히 가세요.");
                System.exit(0); // 프로그램 강제 종료
            default:
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
        }
    }

    // 실제 음료 구매가 이루어지는 프로세스
    private void purchaseProcess() {
        displayDrinks();
        insertMoney();
        selectDrink();
        returnChange();
    }

    // 관리자 모드 기능 수행
    private void adminMode() {
        System.out.print("관리자 비밀번호를 입력하세요: ");
        String password = scanner.next();

        if (ADMIN_PASSWORD.equals(password)) {
            System.out.println("\n---  관리자 모드 ️ ---");
            System.out.println("현재 재고 현황:");
            for (Drink drink : drinks) {
                System.out.println("- " + drink.name + ": " + drink.quantity + "개");
            }
            // (추가 기능 구현 가능: 재고 채우기, 음료 추가/삭제 등)
        } else {
            System.out.println("비밀번호가 틀렸습니다.");
        }
    }

    // 판매 중인 음료 목록을 온도를 기준으로 분류해 표시
    public void displayDrinks() {
        System.out.println("\n--- 차가운 음료 ---");
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).temp.equals("Cold")) {
                System.out.println((i + 1) + ". " + drinks.get(i).toString());
            }
        }
        System.out.println("\n--- 따뜻한 음료 ---");
        for (int i = 0; i < drinks.size(); i++) {
            if (drinks.get(i).temp.equals("Hot")) {
                System.out.println((i + 1) + ". " + drinks.get(i).toString());
            }
        }
        System.out.println("----------------------------------------");
    }

    // 사용자로부터 돈 입력받음
    public void insertMoney() {
        System.out.print("돈을 넣어주세요: ");
        int money = scanner.nextInt();
        this.insertedMoney += money;
        System.out.println("현재 투입된 금액: " + this.insertedMoney + "원");
    }

    // 사용자로부터 음료를 선택받고 구매 가능 여부 확인
    public void selectDrink() {
        System.out.print("🥤 원하시는 음료의 번호를 선택하세요 (0: 취소): ");
        int choice = scanner.nextInt();

        if (choice == 0) {
            System.out.println("구매를 취소합니다.");
            return; // 메서드 종료
        }

        if (choice > 0 && choice <= drinks.size()) {
            Drink selectedDrink = drinks.get(choice - 1);

            if (!selectedDrink.hasStock()) {
                System.out.println("죄송합니다. 해당 음료는 품절되었습니다.");
            } else if (insertedMoney >= selectedDrink.price) {
                dispenseDrink(selectedDrink);
                insertedMoney -= selectedDrink.price;
                selectedDrink.decreaseStock(); // 재고 감소
            } else {
                System.out.println("잔액이 부족합니다. 돈을 더 넣어주세요.");
            }
        } else {
            System.out.println("잘못된 번호입니다. 다시 선택해주세요.");
        }
    }

    // 선택된 음료 제공 처리 (drink: 사용자가 선택한 음료 객체)
    private void dispenseDrink(Drink drink) {
        System.out.println("'" + drink.name + "' 음료가 나왔습니다! 맛있게 드세요!");
    }

    // 남은 금액이 있다면 거스름돈 반환
    public void returnChange() {
        if (insertedMoney > 0) {
            System.out.println("거스름돈 " + insertedMoney + "원을 반환합니다.");
            insertedMoney = 0;
        }
    }
}