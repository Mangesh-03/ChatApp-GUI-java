package utilx.ServerGUI;

import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatServer extends JFrame implements ActionListener
{
    public JFrame fobj ;
    public JButton sendbtn;
    public JTextField text;
    public JLabel youLabel;
    public JTextArea chatArea;

    public ServerSocket ssobj;
    public Socket sobj ;
    public DataInputStream dis;
    public DataOutputStream dos;

    public ChatServer()
    {
        fobj = new JFrame("Marvellous Server");
        fobj.setLayout(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane sp = new JScrollPane(chatArea);
        sp.setBounds(20, 20, 330, 100);
        fobj.add(sp);

        youLabel = new JLabel("You :");
        youLabel.setBounds(20, 140, 50, 30);
        fobj.add(youLabel);

        text = new JTextField();
        text.setBounds(70, 140, 250, 30);
        fobj.add(text);

        sendbtn = new JButton("Send");
        sendbtn.setBounds(150, 200, 100, 35);
        sendbtn.addActionListener(this);
        fobj.add(sendbtn);

        fobj.setSize(400, 300);
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fobj.setVisible(true);

        //  Lambda Thread
        Thread th = new Thread(() -> runServerTask());
        th.start();
    }

    //  Send button action
    public void actionPerformed(ActionEvent aobj)
    {
        if(aobj.getSource() == sendbtn)
        {
            try
            {
                String msg = text.getText();
                chatArea.append("You : " + msg + "\n");

                dos.writeUTF(msg);
                text.setText("");
            }
            catch(Exception eobj)
            {
                chatArea.append("error : " + eobj.getMessage() + "\n");
            }
        }
    }
    
    //  Server listening task in single method
    private void runServerTask()
    {
        try 
        {
            ssobj = new ServerSocket(8080);
            chatArea.append("Server started. Waiting for client...\n");

            sobj = ssobj.accept();
            chatArea.append("Client connected.\n");

            dis = new DataInputStream(sobj.getInputStream());
            dos = new DataOutputStream(sobj.getOutputStream());

            String str = "";

            while(true)
            {
                str = dis.readUTF();

                if(str.equals("bye"))
                {
                    chatArea.append("Client : " + str + "\n");
                    chatArea.append("Server : bye.\n");

                    sobj.close();
                    dis.close();
                    dos.close();
                    ssobj.close();
                    break;
                }

                chatArea.append("Client : " + str + "\n");
                
            }
        }
        catch(Exception eobj)
        {
            chatArea.append("Client : bye\n");
        }
        finally
        {
            saveChatToFile(); 
            fobj.dispose();
        }

    }
    public void saveChatToFile()
    {
        FileWriter fw = null;
        BufferedWriter bw = null;
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

        String TimeStamp = now.format(formatter);

        String FileName = "LogFile" +TimeStamp+ ".txt";

        try
        {
            fw = new FileWriter(FileName, true); // append mode
            bw = new BufferedWriter(fw);

            bw.write("------ Chat Session ------\n");
            bw.write(chatArea.getText());
            bw.write("\n--------------------------\n\n");

            bw.flush();
        }
        catch(Exception e)
        {
            System.out.println("Failed to save chat: " + e.getMessage());
        }
        finally
        {
            try { if(bw != null) bw.close(); } catch(Exception e){}
            try { if(fw != null) fw.close(); } catch(Exception e){}
        }
    }
}
