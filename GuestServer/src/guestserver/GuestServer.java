package guestserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GuestServer {

    public static void main(String[] args) {
        int port = 54321;

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                Socket socket = server.accept();
                Thread guessThread = new Thread(new GuestThread(socket));
                guessThread.start();
            }
        } catch (IOException e) {
        }
    }
}
