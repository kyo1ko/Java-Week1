import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

/*

기존의 구조는 음료 종류만 하나의 객체로 만들어두었던 상태.
그러나 객체지향이란 각 음료 하나하나가 전부 독립적인 객체여야 함.
예를 들어 콜라가 10개이면 Drink 객체가 10개 생성되어야 유통기한도 개별 속성 등도 따로 가질 수 있는 것임.

* 음료 객체를 단순히 필드를 보관하는 구조체로 사용하는 것이 아닌, 실제 개별 객체로 존재하게 하는 것이 이번 수정본의 핵심이었음.

*/

object DrinkMachine {
    @JvmStatic
    fun main(args: Array<String>) {
        // AdvancedMachine 클래스 객체 생성
        val machine = AdvancedMachine()
        // 자판기 프로그램 실행
        machine.run()
    }
}

// 음료 정보를 담는 클래스
data class Drink (
    var maker: String? = null, // 제조사
    var name: String? = null, // 음료 이름
    var price: Int = 0, // 가격
    var temp: String? = null, // 음료 온도 ("Hot", "Cold")
    var expiry: LocalDate? = null, // 유통기한
    var canType: String? = null, // 캔 종류 ("뚱캔", "얇은캔")
    var color: String? = null // 캔 색상 ("빨강", "파랑" 등등...)
)

// 자판기의 작동 로직 담당 클래스
data class AdvancedMachine (
    private var drinkStorage: MutableMap<String, Queue<Drink>> = mutableMapOf(), // 음료 이름별 큐
    private var insertedMoney: Int = 0,
    private val scanner: Scanner = Scanner(System.`in`),
    private val ADMIN_PASSWORD: String = "admin" // 관리자 비밀번호, 테스트 단계이기 때문에 상수로 선언
){

     init { // 초기 생성 시 행동 지정
         addDrink("코카콜라", "코카콜라", 1200, "Cold", "코카콜라", "뚱캔", "빨강", 10)
         addDrink("칠성사이다", "롯데칠성", 1100, "Cold", "칠성사이다", "얇은캔", "초록", 8)
         addDrink("포카리스웨트", "동아오츠카", 1000, "Cold", "포카리스웨트", "얇은캔", "파랑", 12)
         addDrink("갈아만든배", "해태", 1300, "Cold", "갈아만든배", "뚱캔", "노랑", 16)
         addDrink("초록매실", "웅진", 900, "Cold", "초록매실", "얇은캔", "초록", 12)
         addDrink("비타500", "광동", 800, "Cold", "비타500", "얇은캔", "주황", 16)
         addDrink("레쓰비", "농심", 700, "Hot", "레쓰비", "뚱캔", "갈색", 10)
         addDrink("맥심 모카골드", "동서식품", 600, "Hot", "맥심 모카골드", "얇은캔", "노랑", 8)
         addDrink("율무차", "담터", 800, "Hot", "율무차", "얇은캔", "살구색", 8)
         addDrink("칸타타", "롯데", 1000, "Hot", "칸타타", "얇은캔", "검은색", 6)
     }

    // 같은 종류의 음료 여러 개 추가
    private fun addDrink(
        key: String,
        maker: String,
        price: Int,
        temp: String,
        name: String,
        canType: String,
        color: String,
        count: Int
    ) {
        drinkStorage.putIfAbsent(key, LinkedList())
        val q = drinkStorage[key]

        for (i in 0..<count) {
            val expiry = LocalDate.now().plusDays((20 + (Math.random() * 50).toInt()).toLong())
            // ^ Math.random()은 0.0<=값<1.0의 실수를 반환하기 때문에 *50으로 0.0 이상 50.0 미만의 실수를 생성, 이를 유통기한으로 대입
            q?.offer(Drink(maker, name, price, temp, expiry, canType, color))
        }
    }

    // 프로그램 루프
    fun run() {
        while (true) {
            displayMenu()
            println("\n====================================\n")
        }
    }

    // 메뉴 표시
    private fun displayMenu() {
        println("--- 어서오세요, 대림 자판기입니다.  ---")
        println("1. 음료 구매")
        println("2. 관리자 모드")
        println("3. 프로그램 종료")
        print("메뉴를 선택하세요: ")
        val choice = scanner.nextInt()

        when (choice) {
            1 -> purchaseProcess()
            2 -> adminMode()
            3 -> {
                println("프로그램을 종료합니다. 안녕히 가세요.")
                System.exit(0)
            }

            else -> println("잘못된 입력입니다. 다시 선택해주세요.")
        }
    }

    // 구매 프로세스
    private fun purchaseProcess() {
        displayDrinks()
        insertMoney()
        selectDrink()
        returnChange()
    }

    // 관리자 모드
    private fun adminMode() {
        print("관리자 비밀번호를 입력하세요: ")
        val password = scanner.next()

        if (ADMIN_PASSWORD != password) {
            println("비밀번호가 틀렸습니다.")
            return
        }

        while (true) {
            println("\n---  관리자 모드  ---")
            println("1. 재고 확인")
            println("2. 음료 추가")
            println("3. 만료/임박 음료 제거")
            println("4. 유통기한 상세 조회")
            println("0. 관리자 모드 종료")
            print("메뉴를 선택하세요: ")
            val adminChoice = scanner.nextInt()

            when (adminChoice) {
                1 -> showInventory()
                2 -> addNewDrinkByAdmin()
                3 -> removeExpiredDrinks()
                4 -> showAllExpiryDetails()
                0 -> {
                    return
                }

                else -> println("잘못된 입력입니다.")
            }
        }
    }

    // 재고 현황 (30일 이하 표시)
    private fun showInventory() {
        println("\n--- 현재 재고 현황 ---")
        val today = LocalDate.now()

        for (key in drinkStorage.keys) {
            val q = drinkStorage[key]
            if (q?.isEmpty() == true) {
                println("- $key: 품절")
                continue
            }

            val total = q?.size
            var nearExpiryCount = 0

            if (q?.isEmpty() == true) {
                for (d in q) {
                    val daysLeft = ChronoUnit.DAYS.between(today, d.expiry)
                    if (daysLeft <= 30) nearExpiryCount++
                }
            }

            if (nearExpiryCount > 0) println("- " + key + ": " + total + "개 (유통기한 30일 이내: " + nearExpiryCount + "개)")
            else println("- " + key + ": " + total + "개")
        }
    }

    // 유통기한 상세 조회 (음료별 남은 일수 표시)
    private fun showAllExpiryDetails() {
        println("\n--- 유통기한 상세 조회 ---")
        val today = LocalDate.now()

        for (key in drinkStorage.keys) {
            val q = drinkStorage[key]
            if (q != null) {
                if (q.isEmpty()) continue
            }

            println("\n[$key]")
            var index = 1
            if (q != null) {
                for (d in q) {
                    val daysLeft = ChronoUnit.DAYS.between(today, d.expiry)
                    var warning = ""
                    if (daysLeft <= 0) warning = " (만료)"
                    else if (daysLeft <= 7) warning = " ⚠(7일 이하 임박)"
                    else if (daysLeft <= 30) warning = " (30일 이내)"
                    println("  " + index + ". " + d.expiry + " (" + daysLeft + "일 남음)" + warning)
                    index++
                }
            }
        }
    }

    // 관리자 모드-음료 추가
    private fun addNewDrinkByAdmin() {
        print("추가할 음료 이름: ")
        val name = scanner.next()
        print("제조사: ")
        val maker = scanner.next()
        print("가격: ")
        val price = scanner.nextInt()
        print("온도 (Hot/Cold): ")
        val temp = scanner.next()
        print("캔 형태 (뚱캔/얇은캔): ")
        val canType = scanner.next()
        print("캔 색상: ")
        val color = scanner.next()
        print("수량: ")
        val count = scanner.nextInt()

        addDrink(name, maker, price, temp, name, canType, color, count)
        println("새 음료 '" + name + "'이(가) " + count + "개 추가되었습니다.")
    }

    // 관리자 모드-만료 및 임박 음료 제거
    private fun removeExpiredDrinks() {
        val today = LocalDate.now()
        var removed = 0

        for (key in drinkStorage.keys) {
            val q = drinkStorage[key]
            val tempQueue: Queue<Drink> = LinkedList()

            if (q != null) {
                while (!q.isEmpty()) {
                    val d = q.poll()
                    val daysLeft = ChronoUnit.DAYS.between(today, d.expiry)

                    // 30일 이하 남은 음료 제거
                    if (daysLeft > 30) tempQueue.offer(d)
                    else removed++
                }
            }
            drinkStorage[key] = tempQueue
        }

        println("만료되었거나 유통기한 30일 이하인 음료 " + removed + "개가 제거되었습니다.")
    }

    // 음료 목록 표시
    fun displayDrinks() {
        println("\n--- 판매 중인 음료 ---")
        var index = 1
        for (key in drinkStorage.keys) {
            val q = drinkStorage[key]
            if (q != null) {
                if (q.isEmpty()) continue
            }
            val sample = q?.peek()
            if (sample != null) {
                println(index.toString() + ". " + sample.name + " (" + sample.temp + ", " + sample.canType + ", " + sample.color + ") - " + sample.price + "원 [재고: " + q.size + "개]")
            }
            index++
        }
        println("----------------------------------------")
    }

    // 돈 입력
    fun insertMoney() {
        print("돈을 넣어주세요: ")
        val money = scanner.nextInt()
        this.insertedMoney += money
        println("현재 투입된 금액: " + this.insertedMoney + "원")
    }

    // 음료 선택
    fun selectDrink() {
        val drinkNames: List<String> = ArrayList(drinkStorage.keys)
        print("원하시는 음료의 번호를 선택하세요 (0: 취소): ")
        val choice = scanner.nextInt()

        if (choice == 0) {
            println("구매를 취소합니다.")
            return
        }
        if (choice < 1 || choice > drinkNames.size) {
            println("잘못된 번호입니다. 다시 선택해주세요.")
            return
        }

        val selectedKey = drinkNames[choice - 1]
        val queue = drinkStorage[selectedKey]

        if (queue == null || queue.isEmpty()) {
            println("죄송합니다. 해당 음료는 품절되었습니다.")
            return
        }

        val selectedDrink = queue.peek()
        if (selectedDrink.expiry?.isBefore(LocalDate.now()) == true) {
            println("죄송합니다. 해당 음료는 유통기한이 만료되었습니다.")
            queue.poll()
            return
        }

        if (insertedMoney >= selectedDrink.price) {
            dispenseDrink(queue.poll())
            // ^ 인수 ‘queue.poll()’이(가) null일 수 있습니다. < 이 경고문의 경우, 품절 확인 조건문을 통해
            // 이미 조건 검증이 완료된 상태이므로 신경 안 써도 되는 상태
            insertedMoney -= selectedDrink.price
        } else {
            println("잔액이 부족합니다. 돈을 더 넣어주세요.")
        }
    }

    // 음료 제공
    private fun dispenseDrink(drink: Drink) {
        println("'" + drink.name + "' 음료(" + drink.canType + ", " + drink.color + ")가 나왔습니다! 맛있게 드세요!")
    }

    // 거스름돈 반환
    fun returnChange() {
        if (insertedMoney > 0) {
            println("거스름돈 " + insertedMoney + "원을 반환합니다.")
            insertedMoney = 0
        }
    }
}