contacts = [] 
def add_contact():
    name = input("Enter name: ")
    phone = input("Enter phone number: ")
    email = input("Enter email: ")
    address = input("Enter address: ")
    contacts.append({"name": name, "phone": phone, "email": email, "address": address})
    print("Contact added successfully!\n")
def view_contacts():
    if not contacts:
        print("No contacts found.\n")
        return
    print("\n=== Contact List ===")
    for idx, contact in enumerate(contacts, start=1):
        print(f"{idx}. {contact['name']} - {contact['phone']}")
    print()
def search_contact():
    search = input("Enter name or phone to search: ").lower()
    found = False
    for contact in contacts:
        if search in contact['name'].lower() or search in contact['phone']:
            print(f"\nName: {contact['name']}")
            print(f"Phone: {contact['phone']}")
            print(f"Email: {contact['email']}")
            print(f"Address: {contact['address']}\n")
            found = True
    if not found:
        print("Contact not found.\n")
def update_contact():
    search = input("Enter the name of the contact to update: ").lower()
    for contact in contacts:
        if contact['name'].lower() == search:
            print("Leave blank to keep existing value.")
            new_name = input(f"New name ({contact['name']}): ") or contact['name']
            new_phone = input(f"New phone ({contact['phone']}): ") or contact['phone']
            new_email = input(f"New email ({contact['email']}): ") or contact['email']
            new_address = input(f"New address ({contact['address']}): ") or contact['address']
            contact.update({"name": new_name, "phone": new_phone, "email": new_email, "address": new_address})
            print("Contact updated successfully!\n")
            return
    print("Contact not found.\n")
def delete_contact():
    search = input("Enter the name of the contact to delete: ").lower()
    for contact in contacts:
        if contact['name'].lower() == search:
            contacts.remove(contact)
            print("Contact deleted successfully!\n")
            return
    print("Contact not found.\n")
def main():
    while True:
        print("=== Contact Management System ===")
        print("1. Add Contact")
        print("2. View Contact List")
        print("3. Search Contact")
        print("4. Update Contact")
        print("5. Delete Contact")
        print("6. Exit")
        choice = input("Enter your choice: ")
        if choice == '1':
            add_contact()
        elif choice == '2':
            view_contacts()
        elif choice == '3':
            search_contact()
        elif choice == '4':
            update_contact()
        elif choice == '5':
            delete_contact()
        elif choice == '6':
            print("Exiting... Goodbye!")
            break
        else:
            print
main()
