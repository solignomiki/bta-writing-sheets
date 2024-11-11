package solignomiki.writingsheets;

public class Utilities {
	private static int startId = 20000;

	// Mod Items ID generation (automatically creates a valid item id without worrying about it)
	public static int nextID(){
		return startId++;
	}
}
