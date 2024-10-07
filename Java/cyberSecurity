import java.io.*;
import java.net.*;

public class CaesarCipherServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started. Listening for incoming connections...");

        Socket socket = serverSocket.accept();
        System.out.println("Incoming connection from " + socket.getInetAddress());

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        String message;
        while ((message = reader.readLine()) != null) {
            if (message.startsWith("ENCRYPT")) {
                String plaintext = message.substring(7);
                String ciphertext = encrypt(plaintext, 3); // Shift by 3
                writer.println("ENCRYPTED:" + ciphertext);
            } else if (message.startsWith("DECRYPT")) {
                String ciphertext = message.substring(7);
                String plaintext = decrypt(ciphertext, 3); // Shift by 3
                writer.println("DECRYPTED:" + plaintext);
            }
        }

        socket.close();
        serverSocket.close();
    }

    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                ciphertext.append((char) ((c - base + shift) % 26 + base));
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, 26 - shift);
    }
}