# Server code
import socket
import rsa

def server_program():
    host = socket.gethostname()
    port = 8080

    server_socket = socket.socket()
    server_socket.bind((host, port))

    server_socket.listen(2)
    conn, address = server_socket.accept()
    print("Connection from: " + str(address))

    # Generate RSA keys
    (public_key, private_key) = rsa.newkeys(2048)

    # Send public key to client
    conn.send(public_key.save_pkcs1())

    # Receive encrypted message from client
    encrypted_message = conn.recv(1024)
    print("Received encrypted message: " + str(encrypted_message))

    # Decrypt message using private key
    decrypted_message = rsa.decrypt(encrypted_message, private_key).decode()
    print("Decrypted message: " + decrypted_message)

    # Send response back to client
    response = "Hello, client!".encode()
    encrypted_response = rsa.encrypt(response, public_key)
    conn.send(encrypted_response)

    conn.close()

if __name__ == '__main__':
    server_program()