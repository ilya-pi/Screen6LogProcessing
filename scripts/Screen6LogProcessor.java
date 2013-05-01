import java.io.*;

public class Screen6LogProcessor {

	private static String SEPARATOR = "\"-\"";
	private static String URL = "http://scripts.adrcdn.com/000394/scripts/screenad_launch_9.4.0_scrambled.js";	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null && s.length() != 0){
			if (!s.contains(URL)){
				continue;
			}
			StringBuilder result = new StringBuilder("");

			int middle = s.indexOf(SEPARATOR);
			String[] partOne = s.substring(0, middle).split(" ");

			result.append(partOne[0]).append(" ").append(partOne[2]).append(" ");

			int clientIndex = s.indexOf("\"", middle + 5);
			String client = s.substring(middle + 5, clientIndex);

			if (client.contains("Mobile")){
				result.append("MOBILE");
			}else if (client.contains("Tablet")){
				result.append("TABLET");
			}else if (client.contains("TV")){
				result.append("TV");
			}else{
				result.append("PC");
			}

			result.append(" ").append(client.hashCode());

			System.out.println(result.toString());
		}
		// An empty line or Ctrl-Z terminates the program
	}
}