package RegularExpressionsHW;/*  Created by Ilyasov Damir on 12.09.2016. */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextLine();
        }
        Pattern pattern = Pattern.compile("(0*)|(1*)|((10)*1*)|((01)*0*)");
        Matcher matcher;
        for (int i = 0; i < n; i++) {
            matcher = pattern.matcher(array[i]);
            if (matcher.matches()) {
                System.out.print(i + 1 + " ");
            }
        }
    }
}
