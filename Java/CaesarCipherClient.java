import java.io.*;
import java.net.*;

public class CaesarCipherClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8000);
        System.out.println("Connected to server");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Enter message to encrypt (or 'q' to quit): ");
            String message = userInputReader.readLine();
            if (message.equals("q")) {
                break;
            }

            writer.println("ENCRYPT:" + message);
            String response = reader.readLine();
            System.out.println("Encrypted message: " + response.substring(9));

            System.out.print("Enter message to decrypt (or 'q' to quit): ");
            message = userInputReader.readLine();
            if (message.equals("q")) {
                break;
            }

            writer.println("DECRYPT:" + message);
            response = reader.readLine();
            System.out.println("Decrypted message: " + response.substring(9));
        }

        socket.close();
    }
}