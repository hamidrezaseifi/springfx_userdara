package com.seifi.springfx_userdara;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.seifi.springfx_userdara.controllers.UserFormViewController;
import javafx.application.Application;

public class UserDataJavaFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(SpringfxUserdaraApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(UserFormViewController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
