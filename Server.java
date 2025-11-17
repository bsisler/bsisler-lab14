import java.util.*;
import java.net.*;
import java.io.*;
import java.time.*;

public class Server {
    int port;
    ArrayList<LocalDateTime> connectedTimes;
    ServerSocket serverSocket;

    //Constructor
    public Server(int port) {
        this.port = port;
        try {
        serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectedTimes = new ArrayList<LocalDateTime>();
    }

    private class Clients implements Runnable {
        private Socket clientSocket;
        
        public Clients(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                connectedTimes.add(LocalDateTime.now());
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                String handshake = input.readLine();
                if (!"12345".equals(handshake)) {
                    output.println("couldn't handshake");
                    clientSocket.close();
                    return;
                }
                String numberString = input.readLine();
                try {
                    int number = Integer.parseInt(numberString);
                    int factors = 0;
                    for (int i = 1; i <= number; i++) {
                        if (number % i == 0) {
                            factors++;
                        }
                    }
                    output.println("The number " + number + " has " + factors + " factors");
                } catch (Exception e) {
                    output.println("There was an exception on the server");
                }
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void serve(int totalClients) {
        try {
            for (int i = 0; i < totalClients; i++) {
                Socket clientSocket = serverSocket.accept();
                Thread t = new Thread(new Clients(clientSocket));
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        return connectedTimes;
    }
}
