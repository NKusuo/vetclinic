package command.executor;

import command.CommandType;

import java.io.IOException;
import java.sql.SQLException;

public interface CommandExecutor {

    int execute(String command) throws SQLException, IOException;

    CommandType getCommandType();
}
