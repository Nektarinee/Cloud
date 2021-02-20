package server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ClientConnection
{
    private static final Logger logger = Logger.getLogger(ClientConnection.class.getName());
    private final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    private SocketChannel clientSocket;




    public ClientConnection(int usePort, Handler fileHandler) throws IOException {
        logger.addHandler(fileHandler);
        serverSocketChannel.socket().bind(new InetSocketAddress(usePort));
        logger.setUseParentHandlers(false);
    }

    public void startConnection () {

        System.out.println("Сервер запущен");
        ExecutorService poolClient = Executors.newFixedThreadPool(10);
        while (true) {
            waitNewClientConnection(poolClient);
        }


    }

    private void waitNewClientConnection (ExecutorService poolClient) { // ожидание нового клиента и запуск процесса его подключения
        System.out.println("Ожидание подключения пользователя");
        logger.log(Level.INFO, "Ожидание подключения пользователя");

        try {

            clientSocket = serverSocketChannel.accept();
            logger.log(Level.INFO, "Клиент успешно подключен " + clientSocket.getRemoteAddress());
            System.out.println("Клиент успешно подключен " + clientSocket.getRemoteAddress());

            poolClient.execute(()-> {
                        chekAndSubscribeClientConnection(clientSocket);

                    }
            );

        } catch (IOException e) {
            System.out.println("Ошибка подключения нового клиента");
            logger.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.finishConnect();
            } catch (IOException e) {
                System.out.println("Ошибка закрытия неудачного соединеня");
                logger.log(Level.SEVERE, e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public void chekAndSubscribeClientConnection (SocketChannel clientSocket) {
        new ClientHandler(this, clientSocket).handle();
    }
}
