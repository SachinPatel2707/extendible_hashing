import java.util.*;

public class RecordOperations {
    public void insertRecord (Record record)
    {
        HashOperations hashOp = new HashOperations();

        String hashVal = hashOp.getHashValue(record.id, Data.getGlobalDepth());
        int bucketIdx = Data.getHashMap().get(hashVal);
        Bucket bucket = Data.getSimSecMem()[bucketIdx];

        if (bucket.nextEmptySlotIndex >= 0)
        {            
            bucket.recordArr[bucket.nextEmptySlotIndex] = record;
            bucket.nextEmptySlotIndex = (bucket.nextEmptySlotIndex == Data.bucketSize-1) ? -1 : bucket.nextEmptySlotIndex+1;
            return;
        }
        else if (bucket.nextBucket != -1)
        {
            Bucket overflowBucket = Data.getSimSecMem()[bucket.nextBucket];
            while (overflowBucket.nextEmptySlotIndex == -1 && overflowBucket.nextBucket != -1)
            {
                overflowBucket = Data.getSimSecMem()[overflowBucket.nextBucket];
            }

            if (overflowBucket.nextEmptySlotIndex != -1)
            {
                overflowBucket.recordArr[overflowBucket.nextEmptySlotIndex] = record;
                overflowBucket.nextEmptySlotIndex = (overflowBucket.nextEmptySlotIndex == Data.bucketSize-1) ? -1 : overflowBucket.nextEmptySlotIndex+1;
                return;
            }
        }
        
        if (bucket.localDepth < Data.getGlobalDepth())
        {
            List<Record> tempRecordArr = new ArrayList<>();
            
            for (Record x : bucket.recordArr)
                tempRecordArr.add(x);
            
            if (bucket.nextBucket != -1)
            {
                Bucket ofBucket = Data.getSimSecMem()[bucket.nextBucket];
                for (Record x : ofBucket.recordArr)
                    tempRecordArr.add(x);
                
                while (ofBucket.nextBucket != -1)
                {
                    ofBucket = Data.getSimSecMem()[ofBucket.nextBucket];
                    for (Record x : ofBucket.recordArr)
                        tempRecordArr.add(x);
                }
            }

            bucket.nextBucket = -1;
            bucket.nextEmptySlotIndex = 0;
            bucket.localDepth += 1;
            Arrays.fill(bucket.recordArr, null);

            int newBucketIdx = Data.nextAvailableBucket++;
            Bucket newBucket = Data.getSimSecMem()[newBucketIdx];
            newBucket.localDepth = bucket.localDepth;

            List<String> hashValuesToModify = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : Data.getHashMap().entrySet())
            {
                if (entry.getValue() == bucketIdx)
                    hashValuesToModify.add(entry.getKey());
            }

            int length = hashValuesToModify.size();

            for (int i = length/2; i < length; i++)
            {
                Data.addEntryHashMap(hashValuesToModify.get(i), newBucketIdx);
            }
            
            for (Record x : tempRecordArr)
            {
                insertRecord(x);
            }

            insertRecord(record);
        }
        else if (bucket.localDepth == Data.getGlobalDepth())
        {
            if (!Data.isGlobalDepthExpanded() && Data.getGlobalDepth() < 16)
            {
                hashOp.expandGlobalDepth();
                insertRecord(record);
            }
            else
            {
                while (bucket.nextBucket != -1)
                {
                    bucket = Data.getSimSecMem()[bucket.nextBucket];
                }

                bucket.nextBucket = Data.nextAvailableOverflowBucket--;
                Bucket newBucket = Data.getSimSecMem()[bucket.nextBucket];
                newBucket.recordArr[newBucket.nextEmptySlotIndex] = record;
                newBucket.nextEmptySlotIndex = (newBucket.nextEmptySlotIndex == Data.bucketSize-1) ? -1 : newBucket.nextEmptySlotIndex+1;
                return;
            }
        }
    }
}
