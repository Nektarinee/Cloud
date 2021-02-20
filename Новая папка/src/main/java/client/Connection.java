package client;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
public class Connection
{
    private String serverAddress;
    private int usePort;
    private SocketChannel SocketChannel;
    private SocketChannel clientSocket;


    public Connection(String serverAddress, int usePort) {
        this.serverAddress = serverAddress;
        this.usePort = usePort;
    }

    public void openConnection() {
        try {
            SocketChannel = SocketChannel.open();
            SocketChannel.connect(new InetSocketAddress(serverAddress, usePort));
            writeMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessage () throws IOException {
        String newData = "Здравствуйте, уважаемый";
        ByteBuffer bbf = ByteBuffer.allocate(100);
        bbf.clear();
        bbf.put(newData.getBytes());
        while (bbf.hasRemaining()) {
            SocketChannel.write(bbf);
        }
        bbf.clear();
    }
}
