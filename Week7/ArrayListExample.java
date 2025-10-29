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

        // 서플
        Collections.shuffle(list);
        System.out.println("Sorted list (Ascending): " + list);

        // 리스트 복사
        ArrayList<String> copiedList = new ArrayList<>(list);
        System.out.println("Copied list: " + copiedList);

        // 리스트 클리어
        copiedList.clear(); // 모든 요소 제거
        System.out.println("Cleared copied list: " + copiedList);

        // 반복문을 통한 출력
        System.out.println("List iteration using for-each:");
        for (String fruit : list) {
            System.out.println(fruit);
        }

        System.out.println("List iteration using for loop:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index " + i + ": " + list.get(i));
        }

        // 리스트를 배열로 변환
        String[] array = list.toArray(new String[0]);
        System.out.println("Converted to array: ");
        for (String element : array) {
            System.out.println(element);
        }

        // 리스트가 비어 있는지 확인
        System.out.println("Is the list empty? " + list.isEmpty());
    }
}
