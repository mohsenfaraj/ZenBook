/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zenphone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddContactPaneController {
    boolean addContactEditMode = false;
    Contact edc;
    ContactManager cm ;
    MainController mc ;
    
    public void init(ContactManager cm , MainController mc) {
        this.cm = cm ;
        this.mc = mc ;
    }
    
    @FXML
    void goBackContactsTab(ActionEvent event) {
        mc.switchPane(mc.ContactsPane);
    }
    
    @FXML
    private TextField ac_name;

    @FXML
    private TextField ac_phone;

    @FXML
    private RadioButton ac_male;

    @FXML
    private RadioButton ac_female;

    @FXML
    private TextField ac_email;

    @FXML
    private TextField ac_address;
    
    @FXML
    private Label editContactState;
    
    @FXML
    private Button deleteContactBtn ;
    

    void openAddContact() {
        clearAddContact();
        addContactEditMode = false;
        edc = null;
        editContactState.setText("Add Contact");
        deleteContactBtn.setDisable(true);
    }
    
    void EditContact(Contact contact) {
        edc = contact;
        editContactState.setText("Edit Contact");
        addContactEditMode = true;
        clearAddContact();
        ac_name.setText(contact.name);
        ac_phone.setText(contact.phone);
        ac_email.setText(contact.email);
        ac_address.setText(contact.address);
        if (contact.gender.equalsIgnoreCase("male")) {
            ac_male.setSelected(true);
        } else {
            ac_female.setSelected(true);
        }
        deleteContactBtn.setDisable(false);
    }

    void clearAddContact() {
        ac_address.clear();
        ac_email.clear();
        ac_female.setSelected(false);
        ac_male.setSelected(false);
        ac_name.clear();
        ac_phone.clear();
    }
    
    @FXML
    void deleteContact() {
        cm.removeContact(edc.id);
        mc.switchPane(mc.ContactsPane);
        mc.cpc.updateContactList(cm.getAll());
        mc.isDataChanged = true;
    }
        @FXML
    void saveContact() {
        String gender;
        if (ac_male.isSelected()) {
            gender = "male";
        } else {
            gender = "female";
        }

        if (addContactEditMode) {
            cm.updateContact(edc.id, ac_name.getText(), gender, ac_phone.getText(), ac_email.getText(),
                    ac_address.getText());
        } else {
            cm.addContact(ac_name.getText(), gender, ac_phone.getText(), ac_email.getText(),
                    ac_address.getText());
        }
        //after added/updated the Contact, go back and refresh contacts
        mc.switchPane(mc.ContactsPane);
        mc.cpc.updateContactList(cm.getAll());
        mc.isDataChanged = true;
    }
    
}
