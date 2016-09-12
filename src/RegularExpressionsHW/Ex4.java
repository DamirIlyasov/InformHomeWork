package RegularExpressionsHW;/*  Created by Ilyasov Damir on 12.09.2016. */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        Pattern pattern = Pattern.compile("(<a href=.*?>)");
        Matcher matcher = pattern.matcher(string);
        string = matcher.replaceAll("");
        pattern = Pattern.compile("</a>");
        matcher = pattern.matcher(string);
        string = matcher.replaceAll("");
        System.out.println(string);
    }
}
