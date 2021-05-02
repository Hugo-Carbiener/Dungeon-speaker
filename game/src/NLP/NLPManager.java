package NLP;

public class NLPManager {

	public static void startNLP(String userInput) {
		 ProcessBuilder pb = new ProcessBuilder("python", "cleaning_and_parsing.py", userInput);
		Process p = pb.start();
 
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String output = new String(in.readLine());
		return output;
	}

}
