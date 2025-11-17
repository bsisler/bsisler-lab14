import java.util.*;
import java.net.*;
import java.io.*;

public class Client {
    String ip;
    int port;

    public Client (String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void handshake() {

    }

    public String request(String p) {
        return "";
    }

    public void disconnect() {

    }


    //Getters

    public Socket getSocket() {
        
    }

    public int getLocalAddress() {
        return 0;
    }

    public int getPort() {
        return 0;
    }
}
