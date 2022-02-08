package com.seifi.springfx_userdara.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("user-data-view.fxml")
public class UserFormViewController {


    @FXML
    private Label testLabel;

    public void loadTestData(){
        this.testLabel.setText("Test Data");
    }

}
