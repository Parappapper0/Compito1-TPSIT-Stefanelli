package com.itismeucci.stefanelli;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AppClient {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Client client = new Client();

        String ip;
        int port;

        String textInput;

        System.out.println("Inserisci l'ip");
        ip = scanner.nextLine();
        System.out.println("Inserisci la porta");
        port = scanner.nextInt();

        client.start(ip, port);
        

        System.out.println(client.receive());
        client.send("Connessione riuscita");

        //START OF CODE
        while(true) {

            textInput = scanner.nextLine();

            if(textInput.toLowerCase().equals("stop")) break;
            
            if(textInput.isBlank()) continue;

            client.send(textInput);

            System.out.println(client.receive());
        }

        client.send("stop");
        System.out.println(client.receive());
        //END OF CODE
        client.close();
        scanner.close();
    }
}
