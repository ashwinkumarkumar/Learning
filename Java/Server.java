import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        System.out.println("Waiting for client to connect...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                String plaintext = in.readLine();
                if (plaintext == null) {
                    break;
                }
                String ciphertext = alphabeticalToNumerical(plaintext);
                out.println(ciphertext);
            }
        } finally {
            clientSocket.close();
            serverSocket.close();
        }
    }

    public static String alphabeticalToNumerical(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                int asciiOffset = Character.isUpperCase(c) ? 64 : 96;
                int numericalValue = c - asciiOffset + 1;
                ciphertext.append(numericalValue);
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }
}