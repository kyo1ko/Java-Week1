package Week7;
import javax.crypto.spec.ChaCha20ParameterSpec;
import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 3);
        hashMap.put("Banana", 5);
        hashMap.put("Apple", 7); // 동일 키는 값 덮어쓰기
        System.out.println("HashMap: " + hashMap);

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Apple", 3);
        linkedHashMap.put("Banana", 5);
        linkedHashMap.put("Cherry", 1);
        System.out.println("LinkedHashMap (Insertion Order): " + linkedHashMap);

        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Banana", 5);
        treeMap.put("Apple", 3);
        treeMap.put("Cherry", 1);
        System.out.println("TreeMap (Sorted Order): " + treeMap);

        // 키와 값 접근
        System.out.println("Keys: " + treeMap.keySet());
        System.out.println("Values: " + treeMap.values());
        System.out.println("Entries: " + treeMap.entrySet());
        for(Map.Entry<String, Integer> set : treeMap.entrySet()) {
            System.out.println(set.getKey() + ": " + set.getValue());
        }
    }
}
