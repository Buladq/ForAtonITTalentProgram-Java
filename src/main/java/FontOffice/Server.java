package FontOffice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;

    private List<String> messages = new ArrayList<>();

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){

        try{
            while ((!serverSocket.isClosed())){
             Socket socket=serverSocket.accept();


             ClientHandler clientHandler=new ClientHandler(socket);
             System.out.println(clientHandler.getClientName()+" подключился");


             Thread thread=new Thread(clientHandler);
             thread.start();



            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeServerSocket(){
        try {
            if(serverSocket!=null){
                serverSocket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket1=new ServerSocket(1234);
        Server server=new Server(serverSocket1);
        server.startServer();
    }
}
