import java.util.*;
import java.net.*;
import java.io.*;

public class Client {
    String ip;
    int port;
    Socket socket;

    //Constructor
    public Client (String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            socket = new Socket(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handshake() {

    }

    public String request(String p) {
        return "";
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
