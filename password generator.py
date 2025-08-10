import random
import string
print("=== Password Generator ===")
length = int(input("Enter desired password length: "))
print("\nChoose password complexity:")
print("1. Letters only")
print("2. Letters + Numbers")
print("3. Letters + Numbers + Special characters")
choice = int(input("Enter your choice (1-3): "))
if choice == 1:
    characters = string.ascii_letters
elif choice == 2:
    characters = string.ascii_letters + string.digits
elif choice == 3:
    characters = string.ascii_letters + string.digits + string.punctuation
else:
    print("Invalid choice. Using full mix.")
    characters = string.ascii_letters + string.digits + string.punctuation
password = []
if choice >= 1:
    password.append(random.choice(string.ascii_letters))
if choice >= 2:
    password.append(random.choice(string.digits))
if choice == 3:
    password.append(random.choice(string.punctuation))
while len(password) < length:
    password.append(random.choice(characters))
random.shuffle(password)
final_password = ''.join(password)
print("\nYour Generated Password is:", final_password)
