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
    
    // change this as per requirement
    int bucketSize = 2;
    int nextEmptySlotIndex = 0;

    Record[] recordArr = new Record[bucketSize];

    int nextBucket = -1;
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
        recordOp.insertRecord(record[0]);
        recordOp.insertRecord(record[1]);
        System.out.println();
    }
}