import java.io.*;
import java.util.*;

// ===== Objects =====

class Record 
{
    int id;
    int saleAmount;
    String name;
    int category;

    Record () {}

    Record (int id, int saleAmount, String name, int category)
    {
        this.id = id;
        this.saleAmount = saleAmount;
        this.name = name;
        this.category = category;
    }
}

class Bucket
{
    int localDepth = 0;
    int nextEmptySlotIndex = 0;
    int nextBucket = -1;
    Record[] recordArr = new Record[Data.bucketSize];
}

// ===== ===== ===== =====

public class ExtendibleHashing_2022CSM1012
{
    static HashOperations hashOp = new HashOperations();
    static RecordOperations recordOp = new RecordOperations();

    public static void main (String[] args) throws IOException
    {
        // GenerateDataset data = new GenerateDataset();
        // data.generateInitialData();

        for (int i = 0; i < 100001; i++)
        {
            Bucket newBucket = new Bucket();
            Data.setBucket(i, newBucket);
        }

        Data.addEntryHashMap("", 0);

        String[] record = {"49987 20123 fnx 489", "16106 3422 dmk 1140"};
        for (int i = 0; i < record.length; i++)
        {
            Data.setIsGlobalDepthExpanded(false);
            String[] recordItems = record[i].split(" ");
            Record newRecord = new Record(
                    Integer.parseInt(recordItems[0]),
                    Integer.parseInt(recordItems[1]),
                    recordItems[2],
                    Integer.parseInt(recordItems[3]));
            recordOp.insertRecord(newRecord);
        }
        System.out.println();
    }
}