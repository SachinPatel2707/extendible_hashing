public class RecordOperations {
    public void insertRecord (Record record)
    {
        HashOperations hashOp = new HashOperations();

        String hashVal = hashOp.getHashValue(record.id, Data.getGlobalDepth());
        int bucketNo = Data.getHashMap().get(hashVal);
        Bucket bucket = Data.getSimSecMem()[bucketNo];

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
            // increase localDepth by 1
            // rehash the values in the bucket to see if they move to the new bucket
            // try reinserting the new record
        }
        else if (bucket.localDepth == Data.getGlobalDepth())
        {
            if (!Data.isGlobalDepthExpanded())
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

                bucket.nextBucket = Data.nextAvailableOverflowBucket;
                Bucket newBucket = Data.getSimSecMem()[Data.nextAvailableOverflowBucket];
                newBucket.recordArr[newBucket.nextEmptySlotIndex] = record;
                newBucket.nextEmptySlotIndex = (newBucket.nextEmptySlotIndex == Data.bucketSize-1) ? -1 : newBucket.nextEmptySlotIndex+1;
                return;
            }
        }
    }
}
