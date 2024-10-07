import java.net.*;
import java.io.*;
import java.util.Random;

public class OtpServer {
    public static void main(String[] args) throws IOException {
        // Create a socket object
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("OTP Server started. Listening for incoming connections...");

            while (true) {
                // Establish a connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Got a connection from " + clientSocket.getInetAddress());

                // Generate a random OTP
                Random random = new Random();
                int otp = random.nextInt(900000) + 100000;

                // Send the OTP to the client
                try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    out.println(otp);
                }

                // Close the connection
                clientSocket.close();
            }
        }
    }
}