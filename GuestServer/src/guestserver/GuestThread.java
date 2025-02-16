package guestserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GuestThread implements Runnable {
    private Socket socket;

    public GuestThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Cliente conectado: " + socket.getInetAddress() + " con el hilo " + Thread.currentThread().getName());

            int secretNumber = 1 + new Random().nextInt(100);
            System.out.println("Número secreto para el hilo " + Thread.currentThread().getName() + ": " + secretNumber + "\n");
            
            boolean stop = false;
            while (!stop) {
                int guessInput = Integer.parseInt(input.readLine());
                if (guessInput < secretNumber) {
                    output.println("Mayor");
                } else if (guessInput > secretNumber) {
                    output.println("Menor");
                } else {
                    output.println("Acierto");
                    stop = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error. " + e.getMessage());
        }
    }
}