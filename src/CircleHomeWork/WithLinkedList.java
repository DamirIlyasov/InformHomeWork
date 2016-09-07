package CircleHomeWork;/*  Created by Ilyasov Damir on 07.09.2016. */

import java.util.LinkedList;
import java.util.Scanner;

public class WithLinkedList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long startTime = System.currentTimeMillis();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            list.add("Person number " + (i + 1));
        }
        checkpiont:
        while (list.size() != 1) {
            if (counter % 2 == 1) {
                counter = 1;
            } else {
                counter = 0;
            }
            for (int i = 0; i < n; i++) {
                counter++;
                if (i == list.size()) {
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
