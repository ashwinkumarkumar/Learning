# Client code
import socket
import rsa

def client_program():
    host = socket.gethostname()
    port = 8080

    client_socket = socket.socket()
    client_socket.connect((host, port))

    # Receive public key from server
    public_key = rsa.PublicKey.load_pkcs1(client_socket.recv(1024))

    # Send encrypted message to server
    message = "Hello, server!".encode()
    encrypted_message = rsa.encrypt(message, public_key)
    client_socket.send(encrypted_message)

    # Receive encrypted response from server
    encrypted_response = client_socket.recv(1024)

    # Decrypt response using public key
    decrypted_response = rsa.decrypt(encrypted_response, public_key).decode()
    print("Decrypted response: " + decrypted_response)

    client_socket.close()

if __name__ == '__main__':
    client_program()