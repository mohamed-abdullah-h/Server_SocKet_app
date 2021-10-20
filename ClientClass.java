package dummy;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(InetAddress.getLocalHost(),5555);
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        Scanner sc = new Scanner(System.in);
        new Thread(()->{
            while (true) {

                try {
                    String line = sc.nextLine();
                    writer.write("Client : ");
                    writer.write(line,0,line.length());
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        while(true) {
            String line2 = reader.readLine();
            System.out.println(line2);
        }
    }
}
