package com.neueda.shortener.command;

import com.neueda.shortener.model.Direction;
import com.neueda.shortener.model.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public class PositionCommand extends Command {
    private final Logger log = LoggerFactory.getLogger(PositionCommand.class);


    public PositionCommand(String[] args) {
        super(args);
    }

    @Override
    public void execute(Robot robot) {
        Integer x;
        Integer y;
        try {
            x = Integer.parseInt(getArgs()[0].trim());
            y = Integer.parseInt(getArgs()[1].trim());

            if (x <= Robot.MAX_POSITION && x >= Robot.MIN_POSITION
                    && y <= Robot.MAX_POSITION && y >= Robot.MIN_POSITION) {
                robot.setX(x);
                robot.setY(y);
                robot.setDirection(Direction.valueOf(getArgs()[2].trim()));
                log.debug("Robot is placed at " + robot.getCurrentStatus());
            } else {
                log.debug("Position command is ignored");
            }
        } catch (IllegalArgumentException iae) {
            log.debug("Position command is ignored");
        }
    }
}
