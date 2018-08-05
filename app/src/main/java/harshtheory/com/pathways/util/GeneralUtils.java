package harshtheory.com.pathways.util;

import android.util.Log;

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

    public static String generateQueryPlaceHolders(int length)
    {
        StringBuilder placeHolder = new StringBuilder(length);
        placeHolder.append("?");

        for(int i = 1; i < length; i++)
        {
            placeHolder.append(",?");
        }
        Log.e(TAG, "Place Holder: " + placeHolder);
        return placeHolder.toString();
    }

    public static String[] convertIntArrToStrArr(int[] intArray)
    {
        int length = intArray.length;
        String[] strArray = new String[length];

        for (int i = 0; i < length; i++) {
            strArray[i] = String.valueOf(intArray[i]);

        }
        return strArray;
    }

    public static String orderByIds(int[] intArray, String tableName)
    {
        String orderBy = "CASE " + tableName;
        int i = 1;
        for(int id : intArray)
        {
            orderBy = orderBy.concat(" WHEN " + id + " THEN " + i );
            i++;
        }
        orderBy = orderBy.concat( " END");
        return orderBy;
    }
}
