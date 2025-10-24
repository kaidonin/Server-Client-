import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Client {
private static Socket socket= null; 
private static BufferedReader bufferedReader = null;
private static BufferedWriter bufferedWriter = null ;
private static String username ;
public static boolean turnOn = true ;

public  Client (String username){
    try {
        
        this.socket = new Socket("localhost",12345);
        this.username=username;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            this.ReadThemessages();
            this.SendAMessage();
        
    } catch (IOException e) {
        clientGui.textArea.append("Error : could not connect to the server.\n");
        closeEveryThing(socket , bufferedWriter , bufferedReader);

    }
}

public void SendAMessage(){
    new Thread(new Runnable() {
    @Override
public void run(){
 while(!socket.isClosed() & turnOn ) {
       try{ 
        String messagetosend = clientGui.messagetosend ;
        if(!messagetosend.equals("")){
        bufferedWriter.write(username + ": "+ messagetosend+"\n");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        clientGui.messagetosend ="";
    }
      
        
      } catch (IOException e) {
         closeEveryThing(socket , bufferedWriter , bufferedReader);
      }
    }
    closemsg();

}}).start();
      
}
        

public void ReadThemessages (){
    new Thread(new Runnable() {
        @Override
        public void run (){
           
                String messageFromChat = "";
                while (!socket.isClosed()) {
                     try {
                     messageFromChat=bufferedReader.readLine();
                     if (messageFromChat.equals("\n")) {
                         continue;
                     }else{
                        clientGui.textArea.append("\n"+messageFromChat);
                     }
                     
                    } catch (IOException e) {
                     closeEveryThing(socket , bufferedWriter , bufferedReader);
  
                    }
      
                }
            
        }
    }).start();

}
public void closeEveryThing(Socket socket , BufferedWriter bufferedWriter , BufferedReader bufferedReader) {
try {
    
    if (socket != null) {
        socket.close();        
    }
    
    if (bufferedWriter != null) {
        bufferedWriter.close();        
    }
    if (bufferedReader != null) {
        bufferedReader.close();        
    }
    if (clientGui.left) {
        clientGui.textArea.append("You left the chat !.\n"); 
        clientGui.left = false ;
    }else{
    clientGui.textArea.append("Try to rejoin later ,rah server tafi hhhh.\n");
    }
    
    
} catch (Exception e) {
    e.printStackTrace();
    System.out.println("here");
}
}
public  void closemsg(){
    try {
        bufferedWriter.write("ADMINPOWER.LEAVE");
        bufferedWriter.newLine();
        bufferedWriter.flush();
    } catch (IOException e) {
       closeEveryThing(socket, bufferedWriter, bufferedReader);
    }
        
}

}
