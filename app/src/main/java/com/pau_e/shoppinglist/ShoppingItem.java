package com.pau_e.shoppinglist;

/**
 * Created by pau_e on 08/03/2018.
 */

public class ShoppingItem {

    private boolean checked;
    private String text;

    public ShoppingItem(boolean checked, String text) {

        this.checked = checked;
        this.text = text;
    }

    public ShoppingItem(String text) {
        this.text = text;
        this.checked = false;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public void toggleChecked() {
        this.checked = !this.checked;
    }
}
