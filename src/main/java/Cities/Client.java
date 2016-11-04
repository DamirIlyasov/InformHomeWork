package Cities;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] ar) throws IOException {
        // IP-адрес компьютера, где исполняется наша серверная программа.
        String address = "127.0.0.1";
        // Можем передать address или null, что тоже самое в нашем случае,
        // Если null в getByName(), получаем
        // специальный IP адрес "локальной заглушки"
        // для тестирования на машине без сети:
        InetAddress ipAddress = InetAddress.getByName(address);
        // создаем сокет используя IP-адрес и порт сервера.
        Socket socket = new Socket(ipAddress, Server.PORT);
        System.out.println("Отлично, мы подключились к серверу!");
        // Помещаем все в блок try-finally, чтобы
        // быть уверенным, что сокет закроется:
        try {
            // Берем входной и выходной потоки сокета,
            // теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип,
            // чтобы легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(new BufferedInputStream(sin));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sout));

            // Создаем поток для чтения с клавиатуры.
            //Для удобства обернем стандартный поток ввода в BufferedReader
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Введите город и нажмите enter");
            System.out.println();

            while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то
                // и нажмет кнопку Enter.
                System.out.println("Отправляем данные на сервер");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("Сервер ответил : " + line);
                System.out.println("Вы можете писать.");
                System.out.println();
            }
        } finally {
            socket.close();
        }
    }
}
