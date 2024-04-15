import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            out.println("Welcome to CO2 System. Please provide User ID, postcode, and CO2 concentration.");
            // Read input from client
            String userID = in.readLine();
            String postcode = in.readLine();
            String co2ConcentrationStr = in.readLine();

            if (isValidInput(userID, postcode, co2ConcentrationStr)) 
            {
                try {
                    double co2Concentration = Double.parseDouble(co2ConcentrationStr);
                    // Save data to CSV file
                    CSVWriter.getInstance().writeToCSV(userID, postcode, co2Concentration);
                    out.println("Data successfully recorded.");
                } catch (NumberFormatException e) {
                    out.println("Invalid co2Concentration value. Please check your input.");
                }
            }
            else
            {
                out.println("Invalid. Please check your input.");
            }

            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidInput(String userId, String postcode, String co2ConcentrationStr) {
        return userId != null && !userId.isEmpty() && postcode != null && !postcode.isEmpty() && co2ConcentrationStr != null && !co2ConcentrationStr.isEmpty();
    }
}
