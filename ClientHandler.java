import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
     private Socket socket;
    
    private BufferedReader bufferedReader;
     private BufferedWriter bufferedWriter;
    private String clientUsername ;
   
   
    public ClientHandler (Socket socket){
        try {
            this.socket = socket ;
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = bufferedReader.readLine(); 
            clientHandlers.add(this);
            onlineclient();
            broadcastMessage("Server: " + clientUsername + " has joined the chat.\n");
          
           

        } catch (Exception e) {
            closeEveryThing(socket , bufferedReader, bufferedWriter);
        }

    }
    @Override
    public void run() {
        String messageFromClient;
   
     
        while (socket.isConnected()) {
            
            try {
             

              
                messageFromClient = bufferedReader.readLine();
                if(messageFromClient.equals("ADMINPOWER.LEAVE")){
                    closeEveryThing(socket, bufferedReader, bufferedWriter);
                    break;
                }else{
                broadcastMessage(messageFromClient);
                }
                
            } catch (IOException e) {
                closeEveryThing(socket, bufferedReader,bufferedWriter);
                break;
            }
            
        }


     }
     public  void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandler :clientHandlers ){
            try {
                if (!clientHandler.clientUsername.equals(this.clientUsername)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                    
                }
                
            } catch (IOException e) {
              closeEveryThing(socket, bufferedReader,bufferedWriter);
            }

        }


      }
      public  void removeClientHandler(){
       clientHandlers.remove(this);
        onlineclient();
        System.out.println("Server: " + clientUsername + " has Left the chat");
         serverGui.textArea.append(" Server: " + clientUsername + " has Left the chat.\n");
           broadcastMessage("Server: " + clientUsername + " has left the chat.\n");


      }
      public void onlineclient(){
        System.out.println("Online: " + clientHandlers.size() );
         serverGui.textArea.append("Online: " + clientHandlers.size() + ".\n");
      }
      public void closeEveryThing(Socket socket ,BufferedReader bufferedReader , BufferedWriter bufferedWriter){
     
        removeClientHandler();
        try {
            if (socket!=null) {
                socket.close();
            }
            if (bufferedReader!=null) {
                bufferedReader.close();
            }if (bufferedWriter!=null) {
                bufferedWriter.close();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
       
      
      }


 


}
