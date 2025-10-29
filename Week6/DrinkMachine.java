package Week6;

/*

기존의 구조는 음료 종류만 하나의 객체로 만들어두었던 상태.
그러나 객체지향이란 각 음료 하나하나가 전부 독립적인 객체여야 함.
예를 들어 콜라가 10개이면 Drink 객체가 10개 생성되어야 유통기한도 개별 속성 등도 따로 가질 수 있는 것임.

* 음료 객체를 단순히 필드를 보관하는 구조체로 사용하는 것이 아닌, 실제 개별 객체로 존재하게 하는 것이 이번 수정본의 핵심이었음.

 */

import java.util.*;
import java.time.LocalDate;

public class DrinkMachine {
    public static void main(String[] args) {
        // AdvancedMachine 클래스 객체 생성
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
    LocalDate expiry; // 유통기한
    String canType;   // 캔 종류 ("뚱캔", "얇은캔")
    String color;     // 캔 색상 ("빨강", "파랑" 등등...)

    // 기본 생성자
    Drink() {}

    // 음료의 모든 정보 초기화하는 생성자
    Drink(String maker, String name, int price, String temp, LocalDate expiry, String canType, String color) {
        this.maker = maker;
        this.name = name;
        this.price = price;
        this.temp = temp;
        this.expiry = expiry;
        this.canType = canType;
        this.color = color;
    }

    // 부모 클래스에 있는 메서드를 자식 클래스에서 재정의
    // -> 부모 클래스가 이미 가지고 있는 메서드를 같은 이름으로 다시 구현(덮어쓰기) 하는 과정
    @Override
    public String toString() {
        return name + "(" + temp + ", " + canType + ", " + color + ") - " + price + "원 [유통기한: " + expiry + "]";
    }
}

// 자판기의 작동 로직 담당 클래스
class AdvancedMachine {
    private Map<String, Queue<Drink>> drinkStorage; // 음료 이름별 큐
    private int insertedMoney;
    private Scanner scanner;
    private final String ADMIN_PASSWORD = "admin"; // 관리자 비밀번호, 테스트 단계이기 때문에 상수로 선언

    public AdvancedMachine() {
        scanner = new Scanner(System.in);
        insertedMoney = 0;
        initializeDrinks();
    }

    // 자판기 초기 음료 등록/자판기 구동 시 초기값 입력 받아 설정할지 고민했으나 테스트 단계라 다른 로직부터 구현-추후 추가 예정
    private void initializeDrinks() {
        drinkStorage = new HashMap<>();
        addDrink("코카콜라", "코카콜라", 1200, "Cold", "코카콜라", "뚱캔", "빨강", 10);
        addDrink("칠성사이다", "롯데칠성", 1100, "Cold", "칠성사이다", "얇은캔", "초록", 8);
        addDrink("포카리스웨트", "동아오츠카", 1000, "Cold", "포카리스웨트", "얇은캔", "파랑", 12);
        addDrink("갈아만든배", "해태", 1300, "Cold", "갈아만든배", "뚱캔", "노랑", 16);
        addDrink("초록매실", "웅진", 900, "Cold", "초록매실", "얇은캔", "초록", 12);
        addDrink("비타500", "광동", 800, "Cold", "비타500", "얇은캔", "주황", 16);
        addDrink("레쓰비", "농심", 700, "Hot", "레쓰비", "뚱캔", "갈색", 10);
        addDrink("맥심 모카골드", "동서식품", 600, "Hot", "맥심 모카골드", "얇은캔", "노랑", 8);
        addDrink("율무차", "담터", 800, "Hot", "율무차", "얇은캔", "살구색", 8);
        addDrink("칸타타", "롯데", 1000, "Hot", "칸타타", "얇은캔", "검은색", 6);
    }

    // 같은 종류의 음료 여러 개 추가
    private void addDrink(String key, String maker, int price, String temp, String name, String canType, String color, int count) {
        drinkStorage.putIfAbsent(key, new LinkedList<>());
        Queue<Drink> q = drinkStorage.get(key);
        for (int i = 0; i < count; i++) {
            LocalDate expiry = LocalDate.now().plusDays(20 + (int)(Math.random() * 50));
            // ^ Math.random()은 0.0<=값<1.0의 실수를 반환하기 때문에 *50으로 0.0 이상 50.0 미만의 실수를 생성, 이를 유통기한으로 대입
            q.offer(new Drink(maker, name, price, temp, expiry, canType, color));
        }
    }

    // 프로그램 루프
    public void run() {
        while (true) {
            displayMenu();
            System.out.println("\n====================================\n");
        }
    }

    // 메뉴 표시
    private void displayMenu() {
        System.out.println("--- 어서오세요, 대림 자판기입니다.  ---");
        System.out.println("1. 음료 구매");
        System.out.println("2. 관리자 모드");
        System.out.println("3. 프로그램 종료");
        System.out.print("메뉴를 선택하세요: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> purchaseProcess();
            case 2 -> adminMode();
            case 3 -> {
                System.out.println("프로그램을 종료합니다. 안녕히 가세요.");
                System.exit(0);
            }
            default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
        }
    }

    // 구매 프로세스
    private void purchaseProcess() {
        displayDrinks();
        insertMoney();
        selectDrink();
        returnChange();
    }

    // 관리자 모드
    private void adminMode() {
        System.out.print("관리자 비밀번호를 입력하세요: ");
        String password = scanner.next();

        if (!ADMIN_PASSWORD.equals(password)) {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }

        while (true) {
            System.out.println("\n---  관리자 모드  ---");
            System.out.println("1. 재고 확인");
            System.out.println("2. 음료 추가");
            System.out.println("3. 만료/임박 음료 제거");
            System.out.println("4. 유통기한 상세 조회");
            System.out.println("0. 관리자 모드 종료");
            System.out.print("메뉴를 선택하세요: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1 -> showInventory();
                case 2 -> addNewDrinkByAdmin();
                case 3 -> removeExpiredDrinks();
                case 4 -> showAllExpiryDetails();
                case 0 -> { return; }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 재고 현황 (30일 이하 표시)
    private void showInventory() {
        System.out.println("\n--- 현재 재고 현황 ---");
        LocalDate today = LocalDate.now();

        for (String key : drinkStorage.keySet()) {
            Queue<Drink> q = drinkStorage.get(key);
            if (q.isEmpty()) {
                System.out.println("- " + key + ": 품절");
                continue;
            }

            int total = q.size();
            int nearExpiryCount = 0;

            for (Drink d : q) {
                long daysLeft = java.time.temporal.ChronoUnit.DAYS.between(today, d.expiry);
                if (daysLeft <= 30) nearExpiryCount++;
            }

            if (nearExpiryCount > 0)
                System.out.println("- " + key + ": " + total + "개 (유통기한 30일 이내: " + nearExpiryCount + "개)");
            else
                System.out.println("- " + key + ": " + total + "개");
        }
    }

    // 유통기한 상세 조회 (음료별 남은 일수 표시)
    private void showAllExpiryDetails() {
        System.out.println("\n--- 유통기한 상세 조회 ---");
        LocalDate today = LocalDate.now();

        for (String key : drinkStorage.keySet()) {
            Queue<Drink> q = drinkStorage.get(key);
            if (q.isEmpty()) continue;

            System.out.println("\n[" + key + "]");
            int index = 1;
            for (Drink d : q) {
                long daysLeft = java.time.temporal.ChronoUnit.DAYS.between(today, d.expiry);
                String warning = "";
                if (daysLeft <= 0) warning = " (만료)";
                else if (daysLeft <= 7) warning = " (7일 이하 임박)";
                else if (daysLeft <= 30) warning = " (30일 이내)";
                System.out.println("  " + index + ". " + d.expiry + " (" + daysLeft + "일 남음)" + warning);
                index++;
            }
        }
    }

    // 관리자 모드-음료 추가
    private void addNewDrinkByAdmin() {
        System.out.print("추가할 음료 이름: ");
        String name = scanner.next();
        System.out.print("제조사: ");
        String maker = scanner.next();
        System.out.print("가격: ");
        int price = scanner.nextInt();
        System.out.print("온도 (Hot/Cold): ");
        String temp = scanner.next();
        System.out.print("캔 형태 (뚱캔/얇은캔): ");
        String canType = scanner.next();
        System.out.print("캔 색상: ");
        String color = scanner.next();
        System.out.print("수량: ");
        int count = scanner.nextInt();

        addDrink(name, maker, price, temp, name, canType, color, count);
        System.out.println("새 음료 '" + name + "'이(가) " + count + "개 추가되었습니다.");
    }

    // 관리자 모드-만료 및 임박 음료 제거
    private void removeExpiredDrinks() {
        LocalDate today = LocalDate.now();
        int removed = 0;

        for (String key : drinkStorage.keySet()) {
            Queue<Drink> q = drinkStorage.get(key);
            Queue<Drink> tempQueue = new LinkedList<>();

            while (!q.isEmpty()) {
                Drink d = q.poll();
                long daysLeft = java.time.temporal.ChronoUnit.DAYS.between(today, d.expiry);

                // 30일 이하 남은 음료 제거
                if (daysLeft > 30) tempQueue.offer(d);
                else removed++;
            }
            drinkStorage.put(key, tempQueue);
        }

        System.out.println("만료되었거나 유통기한 30일 이하인 음료 " + removed + "개가 제거되었습니다.");
    }

    // 음료 목록 표시
    public void displayDrinks() {
        System.out.println("\n--- 판매 중인 음료 ---");
        int index = 1;
        for (String key : drinkStorage.keySet()) {
            Queue<Drink> q = drinkStorage.get(key);
            if (q.isEmpty()) continue;
            Drink sample = q.peek();
            System.out.println(index + ". " + sample.name + " (" + sample.temp + ", " + sample.canType + ", " + sample.color + ") - " + sample.price + "원 [재고: " + q.size() + "개]");
            index++;
        }
        System.out.println("----------------------------------------");
    }

    // 돈 입력
    public void insertMoney() {
        System.out.print("돈을 넣어주세요: ");
        int money = scanner.nextInt();
        this.insertedMoney += money;
        System.out.println("현재 투입된 금액: " + this.insertedMoney + "원");
    }

    // 음료 선택
    public void selectDrink() {
        List<String> drinkNames = new ArrayList<>(drinkStorage.keySet());
        System.out.print("원하시는 음료의 번호를 선택하세요 (0: 취소): ");
        int choice = scanner.nextInt();

        if (choice == 0) {
            System.out.println("구매를 취소합니다.");
            return;
        }
        if (choice < 1 || choice > drinkNames.size()) {
            System.out.println("잘못된 번호입니다. 다시 선택해주세요.");
            return;
        }

        String selectedKey = drinkNames.get(choice - 1);
        Queue<Drink> queue = drinkStorage.get(selectedKey);

        if (queue == null || queue.isEmpty()) {
            System.out.println("죄송합니다. 해당 음료는 품절되었습니다.");
            return;
        }

        Drink selectedDrink = queue.peek();
        if (selectedDrink.expiry.isBefore(LocalDate.now())) {
            System.out.println("죄송합니다. 해당 음료는 유통기한이 만료되었습니다.");
            queue.poll();
            return;
        }

        if (insertedMoney >= selectedDrink.price) {
            dispenseDrink(queue.poll());
            // ^ 인수 ‘queue.poll()’이(가) null일 수 있습니다. < 이 경고문의 경우, 품절 확인 조건문을 통해
            // 이미 조건 검증이 완료된 상태이므로 신경 안 써도 되는 상태
            insertedMoney -= selectedDrink.price;
        } else {
            System.out.println("잔액이 부족합니다. 돈을 더 넣어주세요.");
        }
    }

    // 음료 제공
    private void dispenseDrink(Drink drink) {
        System.out.println("'" + drink.name + "' 음료(" + drink.canType + ", " + drink.color + ")가 나왔습니다! 맛있게 드세요!");
    }

    // 거스름돈 반환
    public void returnChange() {
        if (insertedMoney > 0) {
            System.out.println("거스름돈 " + insertedMoney + "원을 반환합니다.");
            insertedMoney = 0;
        }
    }
}
