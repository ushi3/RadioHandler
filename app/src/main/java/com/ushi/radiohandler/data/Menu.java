package com.ushi.radiohandler.data;


public enum Menu {

    SIMPLE("Simple"),

    COMPLEX_LAYOUT("Complex layout"),

    GROUP("Grouping"),

    REVERTIBLE("Revert unchecked"),

    CUSTOM("Custom Checkable");

    public final String name;

    Menu(String name) {
        this.name = name;
    }
}
