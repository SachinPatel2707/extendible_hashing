import java.util.*;

public class Data {
    private static Bucket[] simSecMem = new Bucket[500001];
    private static HashMap<String, Integer> hashMap = new LinkedHashMap<>();
    private static int globalDepth;
    private static boolean isGlobalDepthExpanded;

    // change this as per requirement
    public static int bucketSize = 2;
    public static int nextAvailableBucket = 1;
    public static int nextAvailableOverflowBucket = 500000;
    
    static Bucket[] getSimSecMem ()
    {
        return simSecMem;
    }
    static void setSimSecMem (Bucket[] data)
    {
        simSecMem = data;
    }
    static void setBucket (int index, Bucket data)
    {
        simSecMem[index] = data;
    }
    static Bucket getBucket (int index)
    {
        return simSecMem[index];
    }

    static HashMap<String, Integer> getHashMap ()
    {
        return hashMap;
    }
    static void setHashMap (HashMap<String, Integer> data)
    {
        hashMap = data;
    }
    static void addEntryHashMap (String key, Integer data)
    {
        hashMap.put(key, data);
    }

    static int getGlobalDepth ()
    {
        return globalDepth;
    }
    static void setGlobalDepth (int data)
    {
        globalDepth = data;
    }

    static boolean isGlobalDepthExpanded ()
    {
        return isGlobalDepthExpanded;
    }
    static void setIsGlobalDepthExpanded (boolean data)
    {
        isGlobalDepthExpanded = data;
    }
}
