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

	     }
	  }
    }
