package com.neueda.shortener.command;

import com.neueda.shortener.model.Direction;
import com.neueda.shortener.model.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public class LeftCommand extends Command {

    private final Logger log = LoggerFactory.getLogger(LeftCommand.class);

    public void execute(Robot robot) {

        if (!robot.isOnTable()) {
            log.debug("Left command is ignored");
        } else {
            switch (robot.getDirection()) {
                case NORTH:
                    robot.setDirection(Direction.WEST);
                    break;
                case SOUTH:
                    robot.setDirection(Direction.EAST);
                    break;
                case EAST:
                    robot.setDirection(Direction.NORTH);
                    break;
                case WEST:
                    robot.setDirection(Direction.SOUTH);
                    break;
            }
            log.debug("The robot is rotating 90 degree to " + robot.getDirection());
        }

    }
}
