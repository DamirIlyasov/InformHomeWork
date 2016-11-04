package Cities;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static final int PORT = 8080;
    // случайный порт(может быть любое число от 1025 до 65535)

    public static void main(String[] ar) throws IOException {
        // создаем сокет сервера и привязываем его к вышеуказанному порту
        ServerSocket ss = new ServerSocket(PORT);
        System.out.println("Ждём клиента...");
        try {
            // Блокируем выполнение до тех пор, пока не возникнет соединение - ждем клиента
            Socket socket = ss.accept();
            //выводим сообщение когда кто-то связался с сервером
            System.out.println("Клиент найден, подключение разрешено!");
            System.out.println();
            try {
                // Берем входной и выходной потоки сокета,
                // теперь можем получать и отсылать данные клиенту.
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();

                // Конвертируем потоки в другой тип,
                // чтобы легче обрабатывать текстовые сообщения.
                DataInputStream in = new DataInputStream(new BufferedInputStream(sin));
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sout));
                char last='/';
                char first;
                String line = null;
                String line1 = null;
                ArrayList<String> cities = new ArrayList<String>();

                while (true) {
                    line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                    line1 = line.toUpperCase();
                    System.out.println("Клиент отправил такой город : " + line);

                        if (last == '/'){
                            System.out.println("Всё хорошо, работаем...");
                            last = line1.charAt(line.length()-1);
                            cities.add(line);
                            line = "Был введён город " + line + ", Вам на "+last;
                            out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                            out.flush(); // заставляем поток закончить передачу данных.
                            System.out.println("Ждём следующих данных...");
                            System.out.println();
                        }
                        else {
                            first = line.charAt(0);
                            if (first == last){
                                if (cities.contains(line)){
                                    System.out.println("Этот город уже называли");
                                    line = "Был введён город "+ line+ ", этот город уже был назван. Вспомните другой.";
                                    out.writeUTF(line);
                                    out.flush();
                                    System.out.println("Ждём следующих данных...");
                                    System.out.println();
                                }
                                else {
                                    cities.add(line);
                                    System.out.println("Всё хорошо, работаем...");
                                    last = line1.charAt(line.length() - 1);
                                    line = "Был введён город " + line + ", Вам на " + last;
                                    out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                                    out.flush(); // заставляем поток закончить передачу данных.
                                    System.out.println("Ждём следующих данных...");
                                    System.out.println();
                                }
                            }else {
                                System.out.println("Был введён неверный город");
                                line = "Был введён неверный город. Ваш город должен начинаться с "+ last;
                                out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                                out.flush(); // заставляем поток закончить передачу данных.
                                System.out.println("Ждём следующих данных...");
                                System.out.println();
                            }
                        }
                }
            } finally {
                socket.close();
            }
        } finally {
            ss.close();
        }
    }
}
