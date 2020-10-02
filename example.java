import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Main {

   public static void main(String[] args) {
	boolean validInput = false;
	Scanner input = new Scanner(System.in);
//	String[] globalToks;
	while (input.hasNextLine()) {
		
		String request = input.nextLine();
		System.out.println(request);
		//StringTokenizer multiTokenizer = new StringTokenizer(input.nextLine(), " 	");
		String[] tokens = request.split(" ");
//		globalToks = Arrays.copyOf(tokens, tokens.length);
		System.out.println("these are my tokens " + Arrays.toString(tokens)); 
    		for(int i = 0; i < 3; i++){
			//System.out.println(tokens[0]);	
			if(i == 0 && tokens[0].equals("GET")){
				System.out.println("Method = GET");
			}else if(i == 0){
				System.out.println("ERROR -- Invalid Method token.");
			}
			if(i == 1 && tokens.length >1){
				//System.out.println("path valid");
			
				String reg = "^[a-zA-Z0-9\\.\\_\\/]+$";	
				if (!tokens[1].matches(reg)) { 
    					System.out.println("ERROR -- Invalid Absolute-Path token.");
				} else {
    					System.out.println("Request-URL = " + tokens[1]);
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
								System.out.println("HTTP-Version = " + parts[1]);
								validInput = true;
							}
						}else{
							System.out.println("ERROR -- Invalid HTTP-Version token.");
						}
					}else{
						System.out.println("ERROR -- Invalid HTTP-Version token.");
					}						
				}else{
					System.out.println("ERROR -- Invalid HTTP-Version token.");
				}
			}else if (i == 2 && tokens.length == 2){
				System.out.println("ERROR -- Invalid Absolute-Path token.");	
			}
			if(i == 2 && tokens.length > 3){ System.out.println("ERROR -- Spurious token before CRLF.");} 

        	}

		if(validInput){

                	if( tokens[1].contains(".") && (tokens[1].substring(tokens[1].length() - 4).equals(".txt") || tokens[1].substring(tokens[1].length() - 4).equals(".htm") || tokens[1].substring(tokens[1].length() - 5).equals(".html"))){
                        	System.out.println("woohoo!");
				
				String fileName = tokens[1].substring(1);
				try{
				File f = new File(fileName);
				boolean exists = f.exists();
				if(exists){
					Scanner file = new Scanner(f);

					while (file.hasNextLine()){

   						System.out.println(file.nextLine());
					}
				}
				}
				catch (FileNotFoundException ex){

    				}
                	}else{
				System.out.println("501 Not Implemented: " + tokens[1]);
			}
        	}
	}

   }
}
