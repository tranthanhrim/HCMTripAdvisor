package com.example.tranthanhrim1995.hcmtripadvisor.ItemAdapter;

/**
 * Created by tranthanhrim1995 on 11/27/2016.
 */

public class ItemMainScreenMenu {
    private String label;
    private Integer icon;

    public ItemMainScreenMenu() {}

    public ItemMainScreenMenu(String label, Integer icon) {
        setLabel(label);
        setIcon(icon);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}
