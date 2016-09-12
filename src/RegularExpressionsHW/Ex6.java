package RegularExpressionsHW;/*  Created by Ilyasov Damir on 12.09.2016. */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        Pattern pattern = Pattern.compile("(http|https)://(.*?\\..*?/.*?)(:(.*))*");
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println("Protocol = " + matcher.group(1));
            System.out.println("Hostname = " + matcher.group(2));
            if (matcher.group(3) == null) {
                System.out.println("No port");
            } else {
                System.out.println("Port = " + matcher.group(3));
            }
        }
    }
}
