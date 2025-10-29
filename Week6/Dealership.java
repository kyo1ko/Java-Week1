public class Dealership {
    public static void main(String[] args) {
        // 자동차 객체 생성
        Car tesla = new Car("Tesla", "Model Y", "StealthGrey", "Electric");
        Car bmw = new Car("BMW", "M3", "Black", "Gasoline");
        Car benz = new Car("Benz", "S-Class", "Silver", "Diesel");

        System.out.println("차량 목록:");
        System.out.println("1. " + tesla.maker + " " + tesla.model);
        System.out.println("2. " + bmw.maker + " " + bmw.model);
        System.out.println("3. " + benz.maker + " " + benz.model);

        System.out.println("\n에어컨을 켜는 시연:");

        // 각각의 자동차에서 에어컨을 켜는 기능 실행
        tesla.turnOnAirConditioner();
        bmw.turnOnAirConditioner();
        benz.turnOnAirConditioner();
    }
}

class Car {
    String maker;
    String model;
    String color;
    String fuelType;
    boolean isEngineOn = false;  // 시동 상태 (기본값은 꺼짐)

    // 생성자: 자동차의 속성을 초기화
    Car(String maker, String model, String color, String fuelType) {
        this.maker = maker;
        this.model = model;
        this.color = color;
        this.fuelType = fuelType;
    }

    // 기능: 시동을 켬
    void startEngine() {
        if (!isEngineOn) {
            System.out.println(maker + " " + model + "의 시동을 켭니다.");
            isEngineOn = true;
        } else {
            System.out.println(maker + " " + model + "의 시동이 이미 켜져 있습니다.");
        }
    }

    // 기능: 에어컨을 켬
    void turnOnAirConditioner() {
        if (maker.equals("Tesla")) {
            System.out.println(maker + " " + model + "의 에어컨을 바로 켭니다.");
        } else {
            if (!isEngineOn) {
                startEngine();  // 시동이 꺼져 있으면 먼저 시동을 켜고
            }
            System.out.println(maker + " " + model + "의 에어컨을 켭니다.");
        }
    }
}
