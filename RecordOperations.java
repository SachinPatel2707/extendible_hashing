import java.util.*;

public class RecordOperations {
    public void insertRecord (String record, int globalDepth, Map<String, Integer> hashMap, Bucket[] simSecMem)
    {
        HashOperations hashOp = new HashOperations();

        String[] recordItems = record.split(" ");

        String hashVal = hashOp.getHashValue(Integer.parseInt(recordItems[0]), globalDepth);
        int bucketNo = hashMap.get(hashVal);
        Bucket bucket = simSecMem[bucketNo];

        if (simSecMem[bucketNo].nextEmptySlotIndex >= 0)
        {
            Record newRecord = new Record(Integer.parseInt(recordItems[0]), Integer.parseInt(recordItems[1]), recordItems[2], Integer.parseInt(recordItems[3]));
            
            bucket.recordArr[bucket.nextEmptySlotIndex] = newRecord;
            bucket.nextEmptySlotIndex = (bucket.nextEmptySlotIndex == bucket.bucketSize-1) ? -1 : bucket.nextEmptySlotIndex+1;
        }
        else if (bucket.localDepth < globalDepth)
        {
            // increase localDepth by 1
            // rehash the values in the bucket to see if they move to the new bucket
            // try reinserting the new record
        }
        else if (bucket.localDepth == globalDepth)
        {
            // increase global Depth once
            // rehash all data
            // try reinserting the new record
        }
    }
}
