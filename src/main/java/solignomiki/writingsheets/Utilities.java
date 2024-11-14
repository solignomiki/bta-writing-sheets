package solignomiki.writingsheets;

public class Utilities {
	private static int startId = 20000;

	public static int nextID(){
		return startId++;
	}
}
