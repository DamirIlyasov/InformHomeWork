package RegularExpressionsHW;/*  Created by Ilyasov Damir on 12.09.2016. */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        Pattern pattern = Pattern.compile("((0(1|2|3|4|5|6|7|8|9))|(1(1|2|3|4|5|6|7|8|9))|(2(1|2|3|4))):(0|1|2|3|4|5)(1|2|3|4|5|6|7|8|9)");
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.matches());
    }
}
