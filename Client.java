import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Client <server-ip> <port>");
            return;
        }

        String serverIP = args[0];
        int port = Integer.parseInt(args[1]);

        try (
                Socket socket = new Socket(serverIP, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String userID, postcode;
            String co2Concentration;

            System.out.println(in.readLine()); // Display server greeting
            System.out.println("Enter User ID:");
            userID = userInput.readLine();
            out.println(userID);
            System.out.println("Enter postcode:");
            postcode = userInput.readLine();
            out.println(postcode);
            System.out.println("Enter CO2 concentration:");
            co2Concentration = userInput.readLine();
            out.println(co2Concentration);
            System.out.println(in.readLine()); // Display server response
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

