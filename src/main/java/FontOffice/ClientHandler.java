package FontOffice;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers=new ArrayList<>();
    private Socket socket;

    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;

    private String clientName;







    public ClientHandler(Socket socket) {
        if (clientHandlers.size() < 6) {
            try {
                this.socket = socket;
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.clientName = bufferedReader.readLine();
                clientHandlers.add(this);

            } catch (IOException e) {
                closeAll(socket, bufferedReader, bufferedWriter);
            }
        }
        else {
            System.out.println("Больше 6");
        }
    }




    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()){
            try {
                messageFromClient=bufferedReader.readLine();
                broadCastMessage(messageFromClient);

            }
            catch (IOException e){
                closeAll(socket,bufferedReader,bufferedWriter);
                break;
            }
        }

    }

    public void broadCastMessage(String messageToSend){
        for (var clientHandler:
             clientHandlers) {
            try {
                if(!clientHandler.clientName.equals(clientName)){
                    clientHandler.bufferedWriter.write(messageToSend);

                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeAll(socket,bufferedReader,bufferedWriter);
            }
        }
    }


    public void closeAll(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){

        try {
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(bufferedWriter!=null){
                bufferedWriter.close();
            }
            if(socket!=null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientName() {
        return clientName;
    }


}
