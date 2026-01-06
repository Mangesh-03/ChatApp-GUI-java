# 💬Chat Messenger

## 📌 Abstract
This project implements a **Client–Server Chat Messenger** using **Java Swing**, **TCP socket programming**, and **multithreading**.  
It allows two-way real-time text communication between a server and a client through a graphical user interface.  
The system ensures safe communication using `DataInputStream` and `DataOutputStream`, maintains a client-side chat log, and supports graceful termination using a predefined `"bye"` protocol.

This project demonstrates core networking concepts, GUI development, file handling, and concurrent programming in Java.

---

## 🚀 Features

- ✔ **Two-way real-time chat**
- ✔ **Java Swing GUI** with text area + input field
- ✔ Uses **TCP sockets (port 8080)**
- ✔ Separate background thread for network handling
- ✔ Clean `"bye"` protocol without duplicate responses
- ✔ Proper closing of all sockets & streams
- ✔ Client-side chat logging with timestamps
- ✔ Graceful shutdown behavior
- ✔ Lightweight & easy to understand structure


---
## UML Daigram
               +---------------------------------------------------+
               |                ChatApplication                    |
               +---------------------------------------------------+
               |                                                   |
               +---------------------------------------------------+
               |         + main(String[] args) : void              |
               +---------------------------------------------------+
                            | creates
                            |-----------------------------------|
                            v                                   v
               +-------------------------------+   +--------------------------------+
               |          ChatServer           |   |          ChatClient            |
               +-------------------------------+   +--------------------------------+
               | - fobj : JFrame               |   | - fobj : JFrame                |
               | - sendbtn : JButton           |   | - sendbtn : JButton            |
               | - text : JTextField           |   | - text : JTextField            |
               | - youLabel : JLabel           |   | - youLabel : JLabel            |
               | - chatArea : JTextArea        |   | - chatArea : JTextArea         |
               |                               |   |                                |
               | - ssobj : ServerSocket        |   | - sobj : Socket                |
               | - sobj : Socket               |   | - dis : DataInputStream        |
               | - dis : DataInputStream       |   | - dos : DataOutputStream       |
               | - dos : DataOutputStream      |   |                                |
               +-------------------------------+   +--------------------------------+
               | + ChatServer()                |   | + ChatClient()                 |
               | + actionPerformed(ActionEvent)|   | + actionPerformed(ActionEvent)|
               | - runServerTask() : void      |   | - runClientTask() : void       |
               +-------------------------------+   +--------------------------------+
                         ^ implements                         ^ implements
                         |                                    |
                  ActionListener                        ActionListener

---
## Work Flowchart
      Start Server
           |
           v
      Create ServerSocket
           |
           v
      Wait for Client (accept)
           |
           v
      Client Connected
           |
           v
      Create Data Streams
           |
           v
      Read Message from Client
           |
           v
      Is message "bye"?
         /     \
       No       Yes
       |         |
       v         v
      Display   Close Streams
      Message   Close Socket
       |         |
       v         v
      Repeat   Stop Server

---

## 📡 How It Works

### 🟦 Server Side
1. Starts a `ServerSocket(8080)`
2. Waits for the client to connect
3. Receives messages in a background thread
4. Displays client messages in JTextArea
5. Sends messages via Send button
6. Closes everything when either side sends `"bye"`
7. Create Log File.

### 🟩 Client Side
1. Connects to server using `Socket("localhost", 8080)`
2. Starts a read thread
3. Displays all server messages
4. Sends messages through GUI button
5. Closes cleanly when `"bye"` is detected

---

## ⚙️ Technologies Used

- **Java 21+**  
- **Java Swing (GUI)**  
- **Sockets (ServerSocket, Socket)**  
- **DataInputStream / DataOutputStream**  
- **Multithreading**  
- **VS Code** for development

---

## 📂 Project Structure

Before Compile : 

ChatGUI/<br>
 ├── ChatApplication.java   (imports above two packages)<br>
 ├── ChatServer.java<br>
 ├── ChatClient.java<br>
 ├── README.md<br>
 └── .gitignore<br>

After Compile **ChatServer.java** & **ChatClient.java** : 

ChatGUI/<br>
 ├── utilx/<br>
 │    ├── ServerGUI/<br>
 │    │     └── ChatServer.class   (package utilx.ServerGUI)<br>
 │    └── ClientGUI/<br>
 │          └── ChatClient.class   (package utilx.ClientGUI)<br>
 ├── ChatApplication.java   (imports above two packages)<br>
 ├── ChatServer.java<br>
 ├── ChatClient.java<br>
 ├── README.md<br>
 └── .gitignore<br>

---

## 📝 Logging

### Log File Format

- **Naming Convention**: `LogFiledd-MM-yyyy_HH-mm-ss.txt`
- **Location**: Project root directory
- **Encoding**: UTF-8
- **Format**: Plain text with timestamps

### Log Entry Structure

```
Client says: [message content]
Server says: [message content]
```

### Sample Log File

```
Client says: hello
Server says: what are you doing
Client says: nothing

```

### Log Management

- **Automatic Creation**: Logs created on server session end
- **Real-time Writing**: Messages written immediately
- **File Rotation**: New log file per server session
- **Cleanup**: Manual cleanup required

# 🔒 Proper Closing of Sockets

Both sides close:

Socket<br>
ServerSocket<br>
DataInputStream<br>
DataOutputStream<br>

When "bye" arrives.

---

# 🎨 Possible Future Enhancements

Add timestamps in GUI frame<br>
Display usernames<br>
Multiple clients (multi-threaded server)<br>
Use Colors in GUI (client messages = blue, server = green)<br>

Add emojis support

---

# 🙋 Author

Mangesh Bedre.
