package com.neueda.shortener.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public class CommandHolder {

    private List<String> commands = new ArrayList<>();

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
