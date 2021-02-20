package common;

public class Command
{
    private CommandTypes type;
    private Object data;

    public CommandTypes getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public static Command authCommand(String login, String password) {
        Command command = new Command();
        command.type = CommandTypes.AUTH;
        command.data = new AuthCommandService(login, password);
        return command;
    }

    public static Command authOkCommand(String username) {
        Command command = new Command();
        command.type = CommandTypes.AUTH_OK;
        command.data = new AuthOkCommandService(username);
        return command;
    }

    public static Command authErrorCommand(String authErrorMessage) {
        Command command = new Command();
        command.type = CommandTypes.AUTH_ERROR;
        command.data = new AuthErrorCommandService(authErrorMessage);
        return command;
    }

    public static Command message (String userName, String message) {
        Command command = new Command();
        command.type = CommandTypes.MESSAGE;
        command.data = new MessageCommandService(userName, message);
        return command;
    }
}
