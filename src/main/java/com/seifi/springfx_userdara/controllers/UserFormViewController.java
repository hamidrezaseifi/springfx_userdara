package com.seifi.springfx_userdara.controllers;

import com.seifi.springfx_userdara.services.IUserDataService;
import com.seifi.springfx_userdara.services.IWeatherService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("user-data-view.fxml")
public class UserFormViewController {

    private IUserDataService userDataService;

    @FXML
    private Label testLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField locationField;


    @Autowired
    public UserFormViewController(IUserDataService userDataService) {
        this.userDataService = userDataService;
    }

    public void loadTestData(){
        String location = locationField.getText().trim();
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        this.testLabel.setText(this.userDataService.getUserAdditionInfo(name,
                                                                        age,
                                                                        location));
    }

}
