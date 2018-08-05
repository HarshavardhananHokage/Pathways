package harshtheory.com.pathways.util;

public class GeneralUtils {

    public static final String TAG = "GeneralUtils";

    public static int[] getIntArrayFromString(String item)
    {
        String[] tokens = item.split(",");

        int[] intArray = new int[tokens.length];

        int i = 0;
        for(String token: tokens)
        {
            intArray[i] = Integer.parseInt(token);
            i++;
        }
        return intArray;
    }
}
