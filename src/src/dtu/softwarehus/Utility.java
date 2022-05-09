package dtu.softwarehus;

public class Utility {

    public static boolean isInt(String message) {
        try {
            int intForTest = Integer.parseInt(message);
            return true;

        }catch(NumberFormatException e) {

        }

        return false;
    }
}
