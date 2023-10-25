package com.itismeucci.stefanelli;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AppServer 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        int port;
        String inputString;

        System.out.println("Inserisci la porta su cui aprire, 0 per generarla");
        port = scanner.nextInt();
        server.start(port, System.out);
        
        server.send("Connessione riuscita");

        System.out.println(server.receive());

        inputString = server.receive();

        System.out.println("Ricevuto: " + inputString);

        server.send(inputString.toUpperCase());

        scanner.close();
    }
}
