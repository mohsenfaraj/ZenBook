/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zenphone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddGroupPaneController {
  
    MainController mc ;
    ContactManager cm ;
    
    public void init(ContactManager cm , MainController mc){
        this.cm = cm ;
        this.mc = mc ;
    }
    
    @FXML
    void goBackGroupsPane(ActionEvent event) {
        mc.switchToGroup();
    }
    
}
