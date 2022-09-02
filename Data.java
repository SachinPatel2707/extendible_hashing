import java.util.*;

public class Data {
    private static Bucket[] simSecMem = new Bucket[100001];
    private static Map<String, Integer> hashMap = new HashMap<>();
    private static int globalDepth;
    private static boolean isGlobalDepthExpanded;

    // change this as per requirement
    public static int bucketSize = 2;
    public static int nextAvailableBucket = 1;
    public static int nextAvailableOverflowBucket = 100000;
    
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

    static Map<String, Integer> getHashMap ()
    {
        return hashMap;
    }
    static void setHashMap (Map<String, Integer> data)
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
