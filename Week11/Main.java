package Week11;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        int year=1;
        Student a = new Student(2025014, "이민규", 20, true);
        Student b = new Student(2021011, "홍길동", 80, true);
        Student c = new Student(2025014, "이민규", 35, true);
        System.out.println("a, b를 직접적으로 출력");
        System.out.println(a);
        System.out.println(b);
        System.out.println("\n---------------\n");

        students.add(a);
        students.add(b);

        System.out.println("첫 번째 요소를 꺼내는 함수: "+students.getFirst());
        System.out.println("마지막 요소를 꺼내는 함수:  "+students.getLast());
        System.out.println("students 내 요소의 개수를 반환하는 함수: "+students.size());
        System.out.println("컬렉션 내에 객체 a가 포함되어 있는지를 검사하는 함수: "+students.contains(a));
        System.out.println("students 리스트 안에서 객체 b가 처음 등장하는 인덱스를 반환하는 함수:  "+students.indexOf(b));
        students.set(year, b);

        System.out.println("\n---------------\n");

        HashSet<Student> set=new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        System.out.println("set 자체 출력: "+set);
        System.out.println("set의 모든 요소들을 하나씩 꺼내서 println으로 출력하는 함수: "); set.iterator().forEachRemaining(System.out::println);
        System.out.println("set의 요소의 개수를 반환하는 함수:: " + set.size());
        System.out.println("set 내에 객체 a가 포함되어 있는지를 검사하는 함수: " + set.contains(a));
        set.removeIf(s -> s.age > 30);
        System.out.println("age>30 인 요소들을 제거 (removeIf 사용): " + set);
        System.out.println("set이 비어 있는지를 확인하는 함수: " + set.isEmpty());
        set.clear();
        System.out.println("set의 모든 요소를 clear 한 후 비었는지 다시 한 번 확인: " + set.isEmpty());

        System.out.println("\n---------------\n");

        HashMap<Integer, Student> map=new HashMap<>();
        map.put(2025014, a);
        map.put(2021011, b);
        map.put(2025015, c);
        System.out.println("기존 2021011(b) 값: "+map.get(2021011));
        map.replace(2021011, c);
        System.out.println("2021011(b)를 c의 value로 replace 한 후 map 출력: "+map);
        System.out.println("map 내에 2025014라는 키가 포함되어 있는지를 검사하는 함수: " + map.containsKey(2025014));
        System.out.println("map 내에 a 객체가 포함되어 있는지 검사하는 함수: " + map.containsValue(a));
        System.out.println("2021011라는 키에 해당하는 객체를 반환하는 함수: " + map.get(2021011));
        // ^ 변경된 것 확인 가능
        System.out.println("map의 모든 key-value를 하나씩 꺼내어 출력하는 함수:");
        map.forEach((key, value) -> System.out.println("key=" + key + ", value=" + value));
    }
}

class Student{
    int no;
    String name;
    int age;
    boolean isMale;

    public Student(int no, String name, int age, boolean isMale) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    public String toString(){
        return "[no:" + no + ", name:" + name + ", age:" + age + ", isMale:" + isMale+"]";
    }
}