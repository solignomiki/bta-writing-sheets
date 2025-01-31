package solignomiki.writingsheets;

public class Utilities {
	private static int startId = WritingSheets.CONFIG.getInt("StartId");

	public static int nextID(){
		return startId++;
	}
}
