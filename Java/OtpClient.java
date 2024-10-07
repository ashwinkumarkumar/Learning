import java.net.*;
import java.io.*;

public class OtpClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        // Create a socket object
        Socket clientSocket = new Socket("localhost", 12345);

        System.out.println("Connected to the OTP server. Waiting for OTP...");

        // Receive the OTP from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String otp = in.readLine();

        System.out.println("Received OTP: " + otp);

        // Close the connection
        clientSocket.close();
    }
}
