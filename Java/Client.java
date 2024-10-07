import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 2222);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Enter plaintext message: ");
                String plaintext = userInput.readLine();
                out.println(plaintext);
                String ciphertext = in.readLine();
                System.out.println("Ciphertext: " + ciphertext);
            }
        } finally {
            clientSocket.close();
        }
    }
}