package com.seifi.springfx_userdara.controllers;

import com.seifi.springfx_userdara.controls.NumberFieldFX;
import com.seifi.springfx_userdara.entities.UserDataEntity;
import com.seifi.springfx_userdara.services.IUserDataService;
import com.seifi.springfx_userdara.services.IWeatherService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
@FxmlView("user-data-view.fxml")
public class UserFormViewController implements Initializable {

    private IUserDataService userDataService;

    @FXML
    private Label userAdditionInfoLabel;

    @FXML
    private TextField nameField;

    @FXML
    private NumberFieldFX ageField;

    @FXML
    private TextField locationField;

    @FXML
    private ListView usersList;


    @Autowired
    public UserFormViewController(IUserDataService userDataService) {
        this.userDataService = userDataService;
    }

    public void loadUserAdditionInfo(){
        String location = locationField.getText().trim();
        String name = nameField.getText().trim();
        int age = ageField.getValue();
        this.userAdditionInfoLabel.setText(this.userDataService.getUserAdditionInfo(name,
                                                                        age,
                                                                        location));
    }

    public void saveUser(){

    }

    public void loadUsers(){
        List<UserDataEntity> allUsers = userDataService.getSortedUserList();
        List<String> allUsersTitle = allUsers.stream().map(u -> u.getTitle()).collect(Collectors.toList());
        this.usersList.getItems().clear();
        this.usersList.getItems().addAll(allUsersTitle);

        if(allUsers.isEmpty()){
            this.usersList.getItems().add("No User!");
        }
        else{
            UserDataEntity lastUser = allUsers.get(0);
            this.nameField.setText(lastUser.getName());
            this.ageField.setText(lastUser.getAge().toString());
            this.locationField.setText(lastUser.getLocation());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loadUsers();
    }
}
