package com.neueda.shortener.command;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public class CommandFactory {
    private static CommandType commandType;

    public static Command getCommand(String commandString) {

        String[] commandStr = commandString.split(" ");
        if (isValid(commandStr)) {
            switch (commandType) {
                case POSITION:
                    String[] argsP = {commandStr[1], commandStr[2], commandStr[3]};
                    return new PositionCommand(argsP);
                case LEFT:
                    return new LeftCommand();
                case RIGHT:
                    return new RightCommand();
                case FORWARD:
                    String[] argsF = {commandStr[1]};
                    return new ForwardCommand(argsF);
                case TURNAROUND:
                    return new TurnAroundCommand();
                case WAIT:
                    return new WaitCommand();
            }
        }
        return null;
    }

    private static boolean isValid(String[] commandStr) {
        try {
            commandType = CommandType.valueOf(commandStr[0]);
        } catch (IllegalArgumentException iae) {
            return false;
        }
        return true;
    }
}
