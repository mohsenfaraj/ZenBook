package zenphone;

public class Contact {

    String name, gender, phone, email, address;
    int id;
    Contact next ;
    Contact prev ;

    public Contact(int id, String name, String gender, String phone, String email, String address) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getContactAsData() {
        String data;
        data = name + " ; " + gender + " ; " + phone + " ; " + email + " ; " + address;
        return data;
    }
}
