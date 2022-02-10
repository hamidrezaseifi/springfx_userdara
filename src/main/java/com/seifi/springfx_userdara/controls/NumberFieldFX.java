package com.seifi.springfx_userdara.controls;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class NumberFieldFX extends TextField {
    public NumberFieldFX() {
        this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            public void handle( KeyEvent t ) {
                char ar[] = t.getCharacter().toCharArray();
                char ch = ar[t.getCharacter().toCharArray().length - 1];
                if (!(ch >= '0' && ch <= '9')) {
                    System.out.println("The char you entered is not a number");
                    t.consume();
                }
            }
        });
    }

    public int getValue(){
        String val = getText();
        if((val == null) ||(val.isEmpty())){
            return 0;
        }

        try{
            int intVal = Integer.parseInt(val);
            return intVal;
        }
        catch (Exception e){
            return 0;
        }

    }

    public void setValue(Integer value){
        this.setText(value.toString());
    }
}
