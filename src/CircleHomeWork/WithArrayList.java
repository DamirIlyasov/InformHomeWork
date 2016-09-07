package CircleHomeWork;

import java.util.ArrayList;
import java.util.Scanner;

public class WithArrayList {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long startTime = System.currentTimeMillis();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            list.add("Person number " + (i + 1));
        }
        checkpiont:
        while (list.size() != 1) {
            if (counter % 2 == 1){
                counter = 1;
            } else {
                counter = 0;
            }
            for (int i = 0; i < n; i++) {
                counter++;
                if (i == list.size()){
                    continue checkpiont;
                }
                if (counter % 2 == 0 & counter != 0) {
                    list.remove(i);
                    i--;
                }
            }
        }
        long spendedTime = System.currentTimeMillis() - startTime;
        System.out.println(spendedTime);
    }

}
