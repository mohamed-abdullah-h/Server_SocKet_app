package dummy;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerClass {
    public static void main(String[] args) throws IOException {
        ServerSocket sc = new ServerSocket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(5555);
        sc.bind(inetSocketAddress);
        System.out.println("server socket is initialized at address "+sc.getInetAddress());
        System.out.println("Server is now waiting for clients ...... ");
        Socket socket = sc.accept();
        System.out.println("new client is connected now ");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);
        new Thread(()->{
          while (true) {
              String line = null;
              try {
                  line = reader.readLine();
                  System.out.println(line);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

        }).start();
        while (true) {
            String data = scanner.nextLine();
            writer.write("Server : ");
            writer.write(data);
            writer.newLine();
            writer.flush();
        }
    }
}
