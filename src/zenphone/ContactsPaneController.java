package zenphone;

import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.octicons.OctIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContactsPaneController {
    
    ContactManager cm ;
    MainController mc ;
    
    History history = new History() ;
    Queue<Contact> searchedContacts = new LinkedList<>() ;

            
    @FXML
    private VBox contactList;
    
    @FXML
    private TextField searchbox;
    
    public void init(ContactManager cm , MainController mc) {
        this.cm = cm ;
        this.mc = mc ;
        updateContactList(cm.getAll());
    }
    
    @FXML
    void searchContact() {
        String textToSearch = searchbox.getText();
        searchedContacts = cm.searchContact(textToSearch);
        List<Contact> list = new ArrayList<Contact>(searchedContacts);
        updateContactList((ArrayList<Contact>) list);
        history.add(textToSearch);
    }
    
    void updateContactList(ArrayList<Contact> contacts) {
        try {
            contactList.getChildren().clear();
            for (Contact contact : contacts) {
                HBox row = new HBox();
                row.setAlignment(Pos.CENTER);
                FileInputStream file = new FileInputStream("user.png");
                Image img = new Image(file);
                ImageView imageView = new ImageView(img);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                VBox innervbox = new VBox();
                Label namelbl = new Label();
                namelbl.getStyleClass().add("contactrow-title");
                namelbl.setText(contact.name);
                Label phonelbl = new Label();
                phonelbl.setText(contact.phone);
                OctIconView smsico = new OctIconView(OctIcon.MAIL);
                smsico.setSize("30");
                OctIconView emailico = new OctIconView(OctIcon.MENTION);
                emailico.setSize("30");
                innervbox.getChildren().addAll(namelbl, phonelbl);
                row.getChildren().addAll(imageView, innervbox, smsico, emailico);
                row.getStyleClass().add("contactrow");
                contactList.getChildren().add(row);

                row.setOnMouseClicked(e -> {
                    mc.switchToAddContact();
                    mc.acpc.EditContact(contact);
                });
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
            @FXML
    void openAddContacts(ActionEvent event) {
        mc.acpc.openAddContact();
        mc.switchToAddContact();
    }
    
    @FXML
    void lastContactHistory() {
        String text = history.get();
        searchbox.setText(text);
    }
    
    @FXML
    void saveSearchedContacts() {
        if (!searchedContacts.isEmpty()){
            DataWriter searchedWriter = new DataWriter();
            List<Contact> list = new ArrayList<Contact>(searchedContacts);
            searchedWriter.writeArrayintoFile((ArrayList<Contact>) list, "searched.csv");
            
        }
    }
        
}
