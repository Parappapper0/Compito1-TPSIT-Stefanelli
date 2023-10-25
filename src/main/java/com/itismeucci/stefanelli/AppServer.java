package com.itismeucci.stefanelli;

import java.util.Scanner;

public class AppServer 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        int port;
        String inputString;
        
        String temp;

        String operator;
        double operand1;
        double operand2;

        System.out.println("Inserisci la porta su cui aprire, 0 per generarla");
        port = scanner.nextInt();
        server.start(port, System.out);
        
        server.send("[SERVER]: Connessione riuscita");

        System.out.println(server.receive());

        //START OF CODE
        while(true) {

            inputString = server.receive();

            System.out.println("[CLIENT]: " + inputString);

            if (inputString.equals("stop")) {server.send("[SERVER]: Chiusura della Connessione Effettuata"); break;}

            temp = inputString.split("\\+")[0];
            if(!temp.equals(inputString)) operator = "\\+";

            else {

                temp = inputString.split("-")[0];
                if(!temp.equals(inputString)) operator = "-";

                else {

                    temp = inputString.split("\\*")[0];
                    if(!temp.equals(inputString)) operator = "\\*";

                    else {

                        temp = inputString.split("/")[0];
                        if(!temp.equals(inputString)) operator = "/";

                        else {

                            server.send("[SERVER]: Input non valido - Operatore non valido o mancante");
                            continue;
                        }
                    }
                }
            }
            
            if(inputString.split(operator).length > 2) {server.send("[SERVER]: Input non valido - Troppi operandi passati"); continue;}

            try {
                operand1 = Double.parseDouble(inputString.split(operator)[0]);
                operand2 = Double.parseDouble(inputString.split(operator)[1]);
            } catch(NumberFormatException e) {server.send("[SERVER]: Input non valido - Uso di caratteri non numerici rilevato"); continue;}

            switch(operator) {

                case "\\+":
                    server.send("[SERVER]: " + Double.toString(operand1 + operand2));
                    break;
                case "-":
                    server.send("[SERVER]: " + Double.toString(operand1 - operand2));
                    break;
                case "\\*":
                    server.send("[SERVER]: " + Double.toString(operand1 * operand2));
                    break;
                case "/":
                    server.send("[SERVER]: " + Double.toString(operand1 / operand2));
                    break;
                default:
                    server.send("[SERVER]: Input non valido - Questo non dovrebbe mai succedere ma per sicurezza");
            }
        }
        //END OF CODE

        server.close();
        scanner.close();
    }
}
