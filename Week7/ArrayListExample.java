package Week7;
import java.util.ArrayList;
import java.util.Collections;

public class ArrayListExample {
    public static void main(String[] args) {
        // ArrayList 생성
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("Initial list: " + list);

        // 요소 추가
        list.add(1, "Durian"); // 특정 위치에 요소 추가
        System.out.println("After adding Durian at index 1: " + list);

        // 요소 수정
        list.set(2, "Orange"); // 특정 위치의 요소 변경
        System.out.println("After changing index 2 to Orange: " + list);

        // 요소 삭제
        list.remove("Banana"); // 특정 요소 삭제
        System.out.println("After removing Banana: " + list);

        list.remove(0); // 특정 위치의 요소 삭제
        System.out.println("After removing element at index 0: " + list);

        // 요소 검색
        System.out.println("Contains 'Cherry': " + list.contains("Cherry")); // 특정 요소 포함 여부 확인
        System.out.println("Index of 'Cherry': " + list.indexOf("Cherry")); // 특정 요소의 첫 번째 인덱스
        System.out.println("Last index of 'Cherry': " + list.lastIndexOf("Cherry")); // 특정 요소의 마지막 인덱스

        // 리스트 크기 확인
        System.out.println("List size: " + list.size());

        // 특정 위치의 요소 가져오기
        if (!list.isEmpty()) {
            System.out.println("Element at index 0: " + list.get(0));
        }

        // 정렬
        list.add("Blueberry");
        list.add("Mango");
        System.out.println("Unsorted list: " + list);
        Collections.sort(list); // 오름차순 정렬
        System.out.println("Sorted list (Ascending): " + list);

//        Collections.sort(list, Collections.reverseOrder()); // 내림차순 정렬
        list.sort(Collections.reverseOrder()); // 내림차순 정렬
        System.out.println("Sorted list (Descending): " + list);
    }
}