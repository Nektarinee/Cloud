package server;
import java.io.IOException;
import java.util.logging.*;


public class Server
{
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private static Handler fileHandler;
    private static final int mainPort = 8189;

    public static void main(String[] args)
    {
        int port = mainPort;

        if (args.length != 0)
        {
            port = Integer.parseInt(args[0]);
        }

        try {
            fileHandler = new FileHandler("log.txt", 10*1024, 20, true);
            fileHandler.setFormatter(new SimpleFormatter());

            new ClientConnection(port  ,
                    fileHandler).startConnection();
        } catch (IOException e) {
            logger.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        }


    }
}
