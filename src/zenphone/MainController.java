package zenphone;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MainController {

    private Button Activetab;
    boolean isDataChanged = false;

    Pane ActivePane;

    ContactManager cm = new ContactManager("data.csv");
    MainController mc = this;
    @FXML
    private Pane view;

    @FXML
    private Button contactsTabBtn;

    @FXML
    private Button groupsTabBtn;

    @FXML
    private Button messagingTabBtn;

    @FXML
    private Button settingsTabBtn;

    Pane AddContactPane;
    Pane AddGroupPane;
    Pane GroupPane;
    Pane ContactsPane;
    Pane SettingsPane;
    Pane MessagingPane;

    AddContactPaneController acpc;
    AddGroupPaneController agpc;
    ContactsPaneController cpc;
    GroupPaneController gpc;
    SettingsPaneController spc;
    MessagingPaneController mpc;

    @FXML
    void ContactTabClicked(ActionEvent event) {
        switchPane(ContactsPane);
        switchTab(contactsTabBtn);
    }

    @FXML
    void GroupsTabClicked(ActionEvent event) {
        switchPane(GroupPane);
        switchTab(groupsTabBtn);
    }

    @FXML
    void messagingTabClicked(ActionEvent event) {
        switchPane(MessagingPane);
        switchTab(messagingTabBtn);
    }

    @FXML
    void settingsTabClicked(ActionEvent event) {
        switchPane(SettingsPane);
        switchTab(settingsTabBtn);
    }

    @FXML
    void initialize() {
        try {
            // load all views to memory
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactsPane.fxml"));
            ContactsPane = loader.load();
            cpc = loader.getController();
            cpc.init(cm, mc);

            loader = new FXMLLoader(getClass().getResource("AddContactPane.fxml"));
            AddContactPane = loader.load();
            acpc = loader.getController();
            acpc.init(cm, mc);

            loader = new FXMLLoader(getClass().getResource("AddGroupPane.fxml"));
            AddGroupPane = loader.load();
            agpc = loader.getController();
            agpc.init(cm, mc);

            loader = new FXMLLoader(getClass().getResource("GroupPane.fxml"));
            GroupPane = loader.load();
            gpc = loader.getController();
            gpc.init(cm, mc);

            loader = new FXMLLoader(getClass().getResource("MessagingPane.fxml"));
            MessagingPane = loader.load();
            mpc = loader.getController();

            loader = new FXMLLoader(getClass().getResource("SettingsPane.fxml"));
            SettingsPane = loader.load();
            spc = loader.getController();

            Activetab = contactsTabBtn;
//            switchPane(ContactsPane);
            view.getChildren().setAll(ContactsPane);
            contactsTabBtn.getStyleClass().add("active");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void switchTab(Button tabbtn) {
        Activetab.getStyleClass().remove("active");
        tabbtn.getStyleClass().add("active");
        Activetab = tabbtn;
    }

    void switchPane(Pane targetpane) {
        view.getChildren().clear();
        view.getChildren().setAll(targetpane);
    }

    public void switchToAddContact() {
        switchPane(AddContactPane);
    }

    public void switchToGroup() {
        switchPane(GroupPane);
    }

    public void switchToAddGroup() {
        switchPane(AddGroupPane);
    }

    void Quit() {
        if (isDataChanged) {
            cm.saveData();
        }
        System.exit(0);

    }

}
