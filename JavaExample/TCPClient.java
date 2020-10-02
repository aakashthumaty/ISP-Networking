    import java.io.*;
    import java.net.*; // import the Java classes supporting TCP/IP
    class TCPClient {
     public static void main(String argv[]) throws Exception
     {
      String sentence;
      String modifiedSentence;
      BufferedReader inFromUser =
       new BufferedReader(
         new InputStreamReader(System.in));
      
      System.out.println("Client ready for input"); 


      while ((sentence = inFromUser.readLine()) != null) {

	   //the client will initiate a new connection to the server
           //for each request/response exchange with the server
           //creatge an instance of Socket to provide the data streams
           //specify the server host name and port

           Socket clientSocket = new Socket("comp431afa19", 6666);

           //TCP provides bidirectional data streams on a socket
           //create a BufferedReader on the receive stream

           BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(
                     clientSocket.getInputStream()));

           //create a DataOutputStream on the send stream

           DataOutputStream outToServer =
                new DataOutputStream(
                    clientSocket.getOutputStream());

           //the writeBytes() method sends all byte values in
           //a string including the line ending characters

           outToServer.writeBytes(sentence + '\n');

           //read text lines from the server
           //the readLine() method deletes line termination
           //characters (\n or \r\n)
           //a line containing only \n or \r\n results in 
           //an empty (null) string

           modifiedSentence = inFromServer.readLine();

           System.out.println(modifiedSentence);

           //this client closes the socket after one request/respone
           //exchange with the server
 
           clientSocket.close();
      }
     }
    }
