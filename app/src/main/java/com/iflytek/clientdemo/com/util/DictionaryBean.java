package com.iflytek.clientdemo.com.util;

/**
 * Created by pc on 2017/09/05.
 */

public class DictionaryBean {
    String id;
    String name;
    String value;
    String  is_select="0";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIsSelect() {
        return is_select;
    }

    public void setIsSelect(String is_select) {
        this.is_select = is_select;
    }
}
