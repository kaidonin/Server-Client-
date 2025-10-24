import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
public class clientGui extends  JFrame {


    
    static JTextArea textArea = new JTextArea(33,85);
    public  Client client ;
 
    public static  JTextField inputField = new JTextField(40);
    public static String messagetosend = "" ;
    static boolean left = false ;
    boolean firstime= true ;
    String inputusername = null ;
    int sendtimes = 0 ;
    
 clientGui(){
    int width = 1300 ;
    int heith = 800 ;
    this.setSize(width ,heith);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    ImageIcon image = new ImageIcon("ana.png");
   this.setIconImage(image.getImage());
  
    


    JPanel npanel =  new JPanel();
    JPanel epanel =  new JPanel();
    JPanel wpanel =  new JPanel();
    JPanel spanel =  new JPanel();
    JPanel cpanel =  new JPanel();

    npanel.setBackground(new Color(50 ,58 ,56));
    epanel.setBackground(new Color(50 ,58 ,56));
    wpanel.setBackground(new Color(50 ,58 ,56));
    spanel.setBackground(new Color(50 ,58 ,56));
    cpanel.setBackground(new Color(255 ,255 ,255));
     
    npanel.setPreferredSize(new Dimension(100,140));
    epanel.setPreferredSize(new Dimension(250,100));
    wpanel.setPreferredSize(new Dimension(250,100));
    spanel.setPreferredSize(new Dimension(100,100));
    cpanel.setLayout(new BorderLayout());
    cpanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
   
     
   
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setMargin(new Insets(10,10,10,10));
  
    JScrollPane Scroll = new JScrollPane(textArea);
    Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    
    
   
    JButton joinBtn = new JButton(" JOIN CHAT");
    JButton leaveBtn = new JButton(" LEAVE ");
    JButton soundboard= new JButton("~975BCE");
    JButton sendBtn = new JButton("SEND");
    sendBtn.setFocusable(false);
    sendBtn.setBackground(new Color(10, 200, 50));
    sendBtn.setForeground(Color.WHITE);
    sendBtn.setPreferredSize(new Dimension(200 , 25));
    
    sendBtn.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            sendBtn.setBackground(new Color(10, 150, 10)); 
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            sendBtn.setBackground(new Color(10, 200, 50)); 
        }
    });

    leaveBtn.setVisible(false);

  for (JButton button : new JButton[]{joinBtn,leaveBtn,soundboard}) {
    button.setFocusable(false);
    button.setBackground(new Color(102, 0, 204));
    button.setForeground(Color.WHITE);
    button.setPreferredSize(new Dimension(200 , 50));
    

    
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(153, 51, 255)); 
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(102, 0, 204)); 
        }
    });
  }
  
  joinBtn.setEnabled(false);
  joinBtn.addActionListener(e -> {joinBtn.setVisible(false);leaveBtn.setVisible(true);client = new Client(inputusername);textArea.append("                                              ***************************  Welcome to the chat  ************************\n"); });
  
  leaveBtn.addActionListener(e -> {
    textArea.setText("                                              -----------------------------------Enter your username to begin the chat-----------------------------------\n");
    reset();
    leaveBtn.setVisible(false);
    joinBtn.setEnabled(false);
    joinBtn.setVisible(true);}); 
    
    
   clientGui.textArea.append("                                              -----------------------------------Enter your username to begin the chat-----------------------------------\n");
  sendBtn.addActionListener(e->{ 
    
    if( !inputField.getText().equals("")){
     if(firstime){inputusername=inputField.getText().trim();textArea.append("Hello "+inputusername +"\n");inputField.setText(""); joinBtn.setEnabled(true);firstime=false ;client.turnOn=true;} 
     else{
       sendtimes++;
       if(sendtimes==3 & joinBtn.isVisible() ){textArea.append("Are u Dump,Join the Chat!.\n");}else{
     messagetosend = inputField.getText();
     textArea.append("You: "+inputField.getText()+"\n");
     inputField.setText("");}}
     }
   });
  soundboard.addActionListener(e->{File file = new File("975BCE.wav"); startsound(file);});
  cpanel.add(Scroll);
  spanel.add(inputField);
  spanel.add(sendBtn);
  npanel.add(leaveBtn);
  npanel.add(joinBtn);
  wpanel.add(soundboard);
 
 





    this.add(npanel,BorderLayout.NORTH);
    this.add(epanel,BorderLayout.EAST);
    this.add(wpanel,BorderLayout.WEST);
    this.add(spanel,BorderLayout.SOUTH);
    this.add(cpanel,BorderLayout.CENTER);


    this.setVisible(true);
}
public void startsound(File file){
     
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
            clip.setMicrosecondPosition(0);
            clip.start();

        } catch (UnsupportedAudioFileException ex) {
        } catch (IOException ex) {
        } catch (LineUnavailableException ex) {
        }

}
public void reset(){
    firstime=true ;
    left = true ;
    System.out.println("bye");
    client.turnOn=false;

}
}

