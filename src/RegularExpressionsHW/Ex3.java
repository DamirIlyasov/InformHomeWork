package RegularExpressionsHW;/*  Created by Ilyasov Damir on 12.09.2016. */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        Pattern pattern = Pattern.compile("//.*");
        Matcher matcher = pattern.matcher(string);

        System.out.println(matcher.replaceAll(""));
        pattern = Pattern.compile("/\\*(.*\r\n)*?\\*/");
        System.out.println(matcher.replaceAll(""));
    }
}
