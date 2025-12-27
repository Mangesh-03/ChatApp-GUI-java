package utilx.ClientGUI;

import javax.swing.*;
import java.awt.event.*;
import java.net.*; 
import java.io.*;

public class ChatClient extends JFrame implements ActionListener
{
    public JFrame fobj;
    public JButton sendbtn;
    public JTextField text;
    public JLabel youLabel;
    public JTextArea chatArea;

    public Socket sobj;
    public DataInputStream dis;
    public DataOutputStream dos;

    public ChatClient()
    {
        fobj = new JFrame("Marvellous Client");
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

        // ✔ Lambda Thread
        Thread th = new Thread(() -> runClientTask());
        th.start();
    }

    // ✔ ActionListener (Send button)
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
            catch (SocketException se)
            {
                if(se.getMessage().equals("Socket closed"))
                {
                    chatArea.append("Connection closed.\n");
                }
                else
                {
                    chatArea.append("Connection lost unexpectedly.\n");
                }
            }
            catch(Exception eobj)
            {
                chatArea.append("error : " + eobj.getMessage() + "\n");
            }
        }
    }
    
    // ✔ Entire client receiving logic in one method
    private void runClientTask()
    {
        try
        {
            chatArea.append("Connecting to server...\n");
            sobj = new Socket("localhost", 8080);

            chatArea.append("Connected to server.\n");

            dis = new DataInputStream(sobj.getInputStream());
            dos = new DataOutputStream(sobj.getOutputStream());

            String str = "";

            while (true)
            {
                str = dis.readUTF();

                if(str.equals("bye"))
                {
                    chatArea.append("Server : " + str + "\n");
                    chatArea.append("Client : bye.\n");
                    sobj.close();
                    dis.close();
                    dos.close();
                    break;
                }

                chatArea.append("Server : " + str + "\n");
            }
        }
        catch(Exception eobj)
        {
            chatArea.append("Sever : bye\n");
        }
        finally
        {
            fobj.dispose();
        }
    }
}
