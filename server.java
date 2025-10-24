
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;


public class server {

    ServerSocket serversocket = null ;
   
     int port = 12345 ;

public server () {
    try{
    this.serversocket= new ServerSocket(port);
    System.out.println("ServerSocket is active.");
    serverGui.textArea.append("✅ ServerSocket is active.\n");
    }catch(IOException e){
        System.out.println("Hehe, Something Went Wrong. ");
        serverGui.textArea.append("Hehe, Something Went Wrong.\n");
        CloseServerSocket(serverGui.stoped);
    }

}
public void startserver(){
   
new Thread (new Runnable(){
    public Socket socket;

    @Override
    public void run(){
        if(!serversocket.isClosed()){
            System.out.println("The Server is listening ...");
             serverGui.textArea.append("🟢 The Server is listening ...\n");
        }
          while(!serversocket.isClosed()){
    try{
        
        this.socket = serversocket.accept();
        System.out.println("A friend has joined !");
        serverGui.textArea.append(" 📢 : A Friend has joined!\n");
        ClientHandler Clienthandler = new ClientHandler(socket);
        Thread thread = new Thread(Clienthandler);
        thread.start();

    }
    catch(IOException e){
        
        closesocket();
    }


  }
    }

public void closesocket(){
try {
    if (this.socket != null){
    this.socket.close();
} 
}catch(IOException e){
    e.printStackTrace();
}

}
}).start();



}
public void CloseServerSocket(boolean stoped){
try {
    if(stoped){
        if (this.serversocket != null){
    System.out.println("ServerSocket has Stopped.");
     serverGui.textArea.append("⛔ ServerSocket has Stopped.\n");
    this.serversocket.close();}
}
    if (this.serversocket != null){
    this.serversocket.close();
} 
}catch(IOException e){
    e.printStackTrace();
}

}



  
}
