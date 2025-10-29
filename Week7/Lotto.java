package Week7;

import java.util.ArrayList;
import java.util.Collections;

public class Lotto {
    public static void main(String[] args){
        ArrayList<Integer> lotto = new ArrayList<>();
        for (int i=1; i<=45; i++){
            lotto.add(i);
        }
        Collections.shuffle(lotto);
        System.out.print("로또 번호: ");
        for (int i=0; i<6; i++){
            System.out.print(lotto.get(i) + ", ");
        }
    }
}