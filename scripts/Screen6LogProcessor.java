import java.io.*;

/**
 * @author Ilya Pimenov
 */
public class Screen6LogProcessor {

	private static String PROCESSED_FILE_SEP = ",";
	private static String SEPARATOR = "\"-\"";
	private static String INITIAL_RECORS_SEP = " ";
	private static int CLIENT_STRING_OFFSET = 5;
	private static String URL = "http://scripts.adrcdn.com/000394/scripts/screenad_launch_9.4.0_scrambled.js";	

	private static String CLIENT_MOBILE = "Mobile";
	private static String CLIENT_TABLET = "Tablet";
	private static String CLIENT_TV = "TV";

	private enum DeviceType{
		MOBILE, TABLET, TV, PC
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null && s.length() != 0){
			if (!s.contains(URL)){
				continue;
			}
			StringBuilder result = new StringBuilder();

			int middle = s.indexOf(SEPARATOR);
			String[] records = s.substring(0, middle).split(INITIAL_RECORS_SEP);

			result.append(records[0]).append(PROCESSED_FILE_SEP).append(records[2]).append(PROCESSED_FILE_SEP);

			String clientString = s.substring(middle + CLIENT_STRING_OFFSET, s.indexOf("\"", middle + CLIENT_STRING_OFFSET));

			result.append(detectDeviceType(clientString)).append(PROCESSED_FILE_SEP).append(clientString.hashCode());

			System.out.println(result.toString());
		}
	}

	private static DeviceType detectDeviceType(String clientString){
		if (clientString.contains(CLIENT_MOBILE)){
			return DeviceType.MOBILE;
		}else if (clientString.contains("Tablet")){
			return DeviceType.TABLET;
		}else if (clientString.contains("TV")){
			return DeviceType.TV;
		}
		return DeviceType.PC;
	}

}