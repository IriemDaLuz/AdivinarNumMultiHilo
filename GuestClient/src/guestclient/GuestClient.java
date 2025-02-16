package guestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GuestClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 54321;

        try (Socket socket = new Socket(host, port);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Entrada por teclado
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor '" + host + "' en el puerto " + port);
            
            boolean stop = false;
            while (!stop) {
                System.out.println("Introduce un número: ");
                int myNumber = Integer.parseInt(stdInput.readLine());

                try {
                    output.println(myNumber);
                    String answer = input.readLine();
                    System.out.println(answer);

                    if ("Acierto".equals(answer)) {
                        stop = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El número no es válido");
                }
            }
        } catch (IOException ex) {
            System.err.println("Error. " + ex.getMessage());
        }
    }
}