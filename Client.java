import java.util.*;
import java.net.*;
import java.io.*;

public class Client {
    String ip;
    int port;
    Socket socket;
    PrintWriter output;
    BufferedReader input;

    //Constructor
    public Client (String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            socket = new Socket(ip, port);
            output = new PrintWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handshake() {
        try {
            output.println("12345");
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String request(String number) {
        try {
            output.println(number);
            output.flush();
            return input.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Getters

    public Socket getSocket() {
        return socket;
    }

    public int getLocalAddress() {
        return 0;
    }

    public int getPort() {
        return socket.getPort();
    }
}
