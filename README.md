# 💬Chat  

A simple and interactive **Client–Server Chat Application** built using **Java Swing**, **TCP Sockets**, and **Multithreading**.  
This project demonstrates real-time messaging between a Server and a Client using Java's `DataInputStream` and `DataOutputStream`.

Both applications include a clean GUI, safe socket handling, and proper shutdown logic when either side sends **"bye"**.

---

## 🚀 Features

- ✔ **Two-way real-time chat**
- ✔ **Java Swing GUI** with text area + input field
- ✔ Uses **TCP sockets (port 8080)**
- ✔ Separate background thread for network handling
- ✔ Clean `"bye"` protocol without duplicate responses
- ✔ Proper closing of all sockets & streams
- ✔ Graceful shutdown behavior
- ✔ Lightweight & easy to understand structure

---

---

## 📡 How It Works

### 🟦 Server Side
1. Starts a `ServerSocket(8080)`
2. Waits for the client to connect
3. Receives messages in a background thread
4. Displays client messages in JTextArea
5. Sends messages via Send button
6. Closes everything when either side sends `"bye"`

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

## 🔒 Proper Closing of Sockets

Both sides close:

Socket

ServerSocket

DataInputStream

DataOutputStream

When "bye" arrives.

---

### 🎨 Possible Future Enhancements

Add timestamps

Display usernames

Multiple clients (multi-threaded server)

Save chat history

Use Colors in GUI (client messages = blue, server = green)

Add emojis support

---

### 🙋 Author

Mangesh Bedre.