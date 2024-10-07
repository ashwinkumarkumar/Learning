from cryptography.hazmat.primitives.asymmetric import rsa, padding
from cryptography.hazmat.primitives import serialization, hashes
import base64

# Generate RSA keys
def generate_keys():
    private_key = rsa.generate_private_key(
        public_exponent=65537,
        key_size=2048
    )
    public_key = private_key.public_key()
    return private_key, public_key

# Encrypt message using the public key
def encrypt_message(public_key, message):
    ciphertext = public_key.encrypt(
        message.encode(),
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None
        )
    )
    return base64.b64encode(ciphertext).decode()

# Decrypt message using the private key
def decrypt_message(private_key, encrypted_message):
    decrypted_message = private_key.decrypt(
        base64.b64decode(encrypted_message.encode()),
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None
        )
    )
    return decrypted_message.decode()

# Main function to demonstrate encryption and decryption
def main():
    # Generate RSA keys
    private_key, public_key = generate_keys()

    # Example message
    message = str(input("enter a message to encrypt:"))
                  

    # Encrypt the message
    encrypted_message = encrypt_message(public_key, message)
    print("Encrypted message:", encrypted_message)

    # Decrypt the message
    decrypted_message = decrypt_message(private_key, encrypted_message)
    print("Decrypted message:", decrypted_message)

# Execute the main function
if __name__ == "__main__":
    main()