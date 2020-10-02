import java.io.*;
import java.net.*; // import the Java classes supporting TCP/IP
import java.util.Scanner;
import java.util.*;
import java.io.*;



public class TCPServer{
	public static void main (String argv[]) throws Exception{
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

		
			//this is where I should call method
			

			method(clientSentence);

			//the writeBytes() method sends all byte values in
			//a string including the line ending characters

			outToClient.writeBytes(capitalizedSentence);
			outToClient.writeBytes(capitalizedSentence);

			//this server closes the socket after one request/respone
			//exchange with the client

			connectionSocket.close();



		}


	}

public static String method(String in){
		boolean validInput = false;
		String output = "";
		Scanner input = new Scanner(in);
		//      meant to be: String[] globalToks;
		while (input.hasNextLine()) {

			String request = input.nextLine();
			System.out.println(request + "\r");
			//meant to be: StringTokenizer multiTokenizer = new StringTokenizer(input.nextLine(), "      ");
			String[] tokens = request.split(" ");
			//             meant to be:  globalToks = Arrays.copyOf(tokens, tokens.length);
			//             meant to be:    System.out.println("these are my tokens " + Arrays.toString(tokens));
			for(int i = 0; i < 3; i++){
				//meant to be: System.out.println(tokens[0]);
				if(i == 0 && tokens[0].equals("GET")){
					// meant to be: System.out.println("Method = GET");
				}else if(i == 0){
					//System.out.println("ERROR -- Invalid Method token."); break;
					output.concat("ERROR -- Invalid Method token.\n"); break;
				}
				if(i == 1 && tokens.length >1){
					//System.out.println("path valid");

					String reg = "^[a-zA-Z0-9\\.\\_\\/]+$";
					if (!tokens[1].matches(reg)) {
						//System.out.println("ERROR -- Invalid Absolute-Path token."); break;
						output.concat("ERROR -- Invalid Absolute-Path token.\n"); break;
					} else {
						// meant to be: System.out.println("Request-URL = " + tokens[1]);
					}
				}
				if(i == 2 && tokens.length >= 3){
					if(tokens[2].contains("/")){
						String parts[] = tokens[2].split("\\/");
						if(parts[0].equals("HTTP")){
							if(parts[1].contains(".")){
								String[] nums = parts[1].split("\\.");
								String numreg = "\\b\\d+\\b";
								if(nums[0].matches(numreg) && nums[1].matches(numreg)){
									// System.out.println("HTTP-Version = " + tokens[2]);
									validInput = true;
								}else{
									//System.out.println("ERROR -- Invalid HTTP-Version token."); break;
									output.concat("ERROR -- Invalid HTTP-Version token.\n"); break;	
								}
							}else{
								//System.out.println("ERROR -- Invalid HTTP-Version token."); break;
								output.concat("ERROR -- Invalid HTTP-Version token.\n"); break;
							}
						}else{
							//System.out.println("ERROR -- Invalid HTTP-Version token."); break;
							output.concat("ERROR -- Invalid HTTP-Version token.\n"); break;
						}
					}else{
						//System.out.println("ERROR -- Invalid HTTP-Version token."); break;
						output.concat("ERROR -- Invalid HTTP-Version token.\n"); break;
					}
				}else if (i == 2 && tokens.length == 2){
					//System.out.println("ERROR -- Invalid Absolute-Path token."); break;
					 output.concat("ERROR -- Invalid Absolute-Path token.\n"); break;
				}
				if(i == 2 && tokens.length > 3){ 
					//System.out.println("ERROR -- Spurious token before CRLF.");validInput = false; break;
					output.concat("ERROR -- Spurious token before CRLF.\n");validInput = false; break;
				}

			}
			//System.out.println(validInput);
			if(validInput == true){
				System.out.println("Method = GET");
				System.out.println("Request-URL = " + tokens[1] );
				System.out.println("HTTP-Version = " + tokens[2] );

				output.concat("Method = GET\n");
				output.concat("Request-URL = " + tokens[1] +"\n");
				output.concat("HTTP-Version = " + tokens[2] + "\n");

				if( tokens[1].contains(".") && (tokens[1].substring(tokens[1].length() - 4).toLowerCase().equals(".txt") || tokens[1].substring(tokens[1].length() - 4).toLowerCase().equals(".htm") || tokens[1].substring(tokens[1].length() - 5).toLowerCase().equals(".html"))){
					//System.out.println("woohoo!");

					String fileName = tokens[1].substring(1);
					try{
						File f = new File(fileName);
						boolean exists = f.exists();
						// if(exists){
						Scanner file = new Scanner(f);
						//	System.out.println("valid input is" + validInput);
						validInput = false;
						while (file.hasNextLine()){

							System.out.println(file.nextLine());
						}
						//}
					}
					catch (FileNotFoundException ex){
						System.out.println("404 Not Found: " + tokens[1] );
					}
				}else{
					System.out.println("501 Not Implemented: " + tokens[1] );
				}
			}
		}
	return "return";





	}

}
