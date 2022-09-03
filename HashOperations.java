import java.util.*;

public class HashOperations {
    public String getHashValue (int id, int depth)
    {
        String binaryStr = getBinary(id);
        binaryStr = binaryStr.substring(0, depth);

        return binaryStr;
    }

    private String getBinary (int n)
    {
        StringBuilder binaryStr = new StringBuilder("0000000000000000");
        int i = binaryStr.length()-1;

        while (n > 0)
        {
            binaryStr.setCharAt(i--, (char)((n % 2) + '0'));
            n = n / 2;
        }

        return binaryStr.toString();
    }

    public void expandGlobalDepth ()
    {
        Map<String, Integer> newHM = new HashMap<>();
        Map<String, Integer> oldHM = Data.getHashMap();

        for (Map.Entry<String, Integer> entry : oldHM.entrySet())
        {
            newHM.put(entry.getKey() + "0", entry.getValue());
            newHM.put(entry.getKey() + "1", entry.getValue());
        }

        Data.setHashMap(newHM);
        Data.setGlobalDepth(Data.getGlobalDepth()+1);
        Data.setIsGlobalDepthExpanded(true);
    }
}
