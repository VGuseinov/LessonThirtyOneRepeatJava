package com.company;

import java.awt.*;
import java.io.Serializable;

public class Deal implements Serializable {

    public String name;
    public Status status;

    public Deal(String name) {
        this.name = name;
        this.status = Status.NOT_DONE;
    }

    @Override
    public String toString() {
        char c = ' ';
        if (this.status == Status.DONE) {
            c ='X';
        }
        return String.format("[%c] %s", c, this.name);
    }
}
