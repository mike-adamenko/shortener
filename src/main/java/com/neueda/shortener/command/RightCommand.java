package com.neueda.shortener.command;

import com.neueda.shortener.model.Direction;
import com.neueda.shortener.model.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public class RightCommand extends Command {
    private final Logger log = LoggerFactory.getLogger(RightCommand.class);

    @Override
    public void execute(Robot robot) {
        if (!robot.isOnTable()) {
            log.debug("Right command is ignored");
        } else {
            switch (robot.getDirection()) {
                case NORTH:
                    robot.setDirection(Direction.EAST);
                    break;
                case SOUTH:
                    robot.setDirection(Direction.WEST);
                    break;
                case EAST:
                    robot.setDirection(Direction.SOUTH);
                    break;
                case WEST:
                    robot.setDirection(Direction.NORTH);
                    break;
            }
            log.debug("The robot is rotating 90 degree to " + robot.getDirection());

        }
    }
}
