
import java.awt.*;


import javax.swing.*;

import java.awt.event.*;



public class serverGui  extends JFrame{
    server server ;
    public static boolean stoped = false ;
    static JTextArea textArea = new JTextArea(33,85);
   
serverGui(){
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
    epanel.setPreferredSize(new Dimension(155,100));
    wpanel.setPreferredSize(new Dimension(155,100));
    spanel.setPreferredSize(new Dimension(100,60));
    cpanel.setLayout(new BorderLayout());
    cpanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    
     
   
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setMargin(new Insets(10,10,10,10));
  
    JScrollPane Scroll = new JScrollPane(textArea);
    Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
  
    cpanel.add(Scroll);
    JButton startBtn = new JButton(" START ");
    JButton stopBtn = new JButton(" STOP  ");
    stopBtn.setEnabled(false);

  for (JButton button : new JButton[]{startBtn, stopBtn}) {
    button.setFocusable(false);
    button.setBackground(new Color(102, 0, 204));
    button.setForeground(Color.WHITE);
    button.setPreferredSize(new Dimension(100 , 50));
    

    
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(153, 51, 255)); 
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(102, 0, 204)); 
        }
    });
  }
  
    startBtn.addActionListener(e-> {server = new server() ;server.startserver();startBtn.setEnabled(false);stopBtn.setEnabled(true);});
       
  

    new Thread (new Runnable(){
      
    @Override
    public void run(){
       stoped = true ;
    stopBtn.addActionListener( e-> {server.CloseServerSocket(stoped); stopBtn.setEnabled(false);startBtn.setEnabled(true);} ); 
    }
  }).start();
  

  npanel.add(stopBtn);
  npanel.add(startBtn);
 





    this.add(npanel,BorderLayout.NORTH);
    this.add(epanel,BorderLayout.EAST);
    this.add(wpanel,BorderLayout.WEST);
    this.add(spanel,BorderLayout.SOUTH);
    this.add(cpanel,BorderLayout.CENTER);


    this.setVisible(true);
}
}
