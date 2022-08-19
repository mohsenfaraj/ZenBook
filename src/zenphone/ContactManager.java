package zenphone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactManager {

    int lastID;
    ArrayList<Contact> contacts = new ArrayList<>();

    public ContactManager(String filename) {
        try {
            File input = new File(filename);
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                String chunk = reader.nextLine();
                String[] chunkArr = chunk.split(";");
                addContact(chunkArr[0].trim(), chunkArr[1].trim(), chunkArr[2].trim(),
                        chunkArr[3].trim(), chunkArr[4].trim());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addContact(String name, String gender, String phone, String email, String address) {
        contacts.add(new Contact(lastID, name, gender, phone, email, address));
        lastID++;
    }

    public void removeContact(int ContactID) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).id == ContactID) {
                contacts.remove(i);
                break;
            }
        }
    }

    public void updateContact(int ContactID, String name, String gender, String phone, String email, String address) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).id == ContactID) {
                contacts.get(i).name = (name == null) ? contacts.get(i).name : name;
                contacts.get(i).gender = (gender == null) ? contacts.get(i).gender : gender;
                contacts.get(i).phone = (phone == null) ? contacts.get(i).phone : phone;
                contacts.get(i).email = (email == null) ? contacts.get(i).email : email;
                contacts.get(i).address = (address == null) ? contacts.get(i).address : address;
                break;
            }
        }
    }

    public Queue<Contact> searchContact(String input) {
//        ArrayList<Contact> match = new ArrayList<>();
        Queue<Contact> match = new LinkedList<>() ;
        int size = contacts.size();
        // reverse keyword:
        if (input.equals("#rev")){
            for (int i = size - 1 ; i >= 0 ; i--){
                match.add(contacts.get(i));
            }
            return match ;
        }
        else if(input.equals("#sort")){
            Collections.sort(contacts,(o1, o2) -> o1.name.compareTo(o2.name));
            return (Queue<Contact>) contacts ;
        }
        else if (input.equals("#save")){
            saveData();
            return (Queue<Contact>) contacts ;
        }
        else {
        for (int i = 0; i < size; i++) {
            Contact contact = contacts.get(i);
            Pattern p = Pattern.compile(input, Pattern.CASE_INSENSITIVE);
            Matcher matcher = p.matcher(contact.name);

            if (matcher.find()) {
                match.add(contact);
            }
        }
        return match;
        }
    }

    public ArrayList<Contact> getAll() {
        return contacts;
    }

    public void saveData() {
        DataWriter dw = new DataWriter();
        dw.writeArrayintoFile(contacts, "data.csv");
    }
}
