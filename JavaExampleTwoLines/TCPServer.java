    import java.io.*;
    import java.net.*; // import the Java classes supporting TCP/IP

    class TCPServer {
       public static void main(String argv[]) throws Exception
          {
             String clientSentence;
             String capitalizedSentence;

             //create an instance of ServerSocket and specify the port
             //number where the server will accept connection requests

             ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(argv[0]));

             while(true) {
                System.out.println("The server is ready to receive");

                //wait here for a client to initiate a connection
                //when a client requests a TCP connection, a new Socket of a
                //different type is returned from the accept() method to
                //provide the connection data streams
 
                Socket connectionSocket = welcomeSocket.accept();

                //TCP provides bidirectional data streams on a socket
                //create a BufferedReader on the receive stream

                BufferedReader inFromClient =
                   new BufferedReader(new InputStreamReader(
                      connectionSocket.getInputStream()));
                
                //create a DataOutputStream on the send stream

                DataOutputStream outToClient =
                   new DataOutputStream(
                      connectionSocket.getOutputStream());

                //read text lines from the client
                //the readLine() method deletes line termination
                //characters (\n or \r\n)
                //a line containing only \n or \r\n results in 
                //an empty (null) string

                clientSentence = inFromClient.readLine();

                capitalizedSentence = 
                      clientSentence.toUpperCase() + '\n';

                //the writeBytes() method sends all byte values in
                //a string including the line ending characters

                outToClient.writeBytes(capitalizedSentence);
                outToClient.writeBytes(capitalizedSentence);

                //this server closes the socket after one request/respone
                //exchange with the client
 
                connectionSocket.close();
             }
          }
    }
