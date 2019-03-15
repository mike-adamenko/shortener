package com.neueda.shortener.command;

import com.neueda.shortener.model.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
public class WaitCommand extends Command {
    private final Logger log = LoggerFactory.getLogger(WaitCommand.class);

    @Override
    public void execute(Robot robot) {
        if (!robot.isOnTable()) {
            log.debug("Wait command is ignored");
        }
        log.debug("The robot is waiting");
    }
}
