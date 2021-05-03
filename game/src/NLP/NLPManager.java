package NLP;

import java.io.*;

public class NLPManager {

	public static String startNLP(String userInput) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("python", "cleaning_and_parsing.py", userInput);
		Process p = pb.start();
 
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String output = new String(in.readLine());
		return output;
	}

}
