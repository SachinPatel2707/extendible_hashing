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

        for (int i = 0; i < 500001; i++)
        {
            Bucket newBucket = new Bucket();
            Data.setBucket(i, newBucket);
        }

        Data.addEntryHashMap("", 0);

        File fObj = new File ("original.txt");
        Scanner fRead = new Scanner (fObj);
        List<String> records = new ArrayList<>();

        while (fRead.hasNextLine())
        {
            records.add(fRead.nextLine());
        }
        fRead.close();

        for (int i = 0; i < records.size(); i++)
        {
            Data.setIsGlobalDepthExpanded(false);
            String[] recordItems = records.get(i).split(" ");
            Record newRecord = new Record(
                    Integer.parseInt(recordItems[0]),
                    Integer.parseInt(recordItems[1]),
                    recordItems[2],
                    Integer.parseInt(recordItems[3]));
            recordOp.insertRecord(newRecord);
            print();
        }
        // print();
    }

    
    static void print ()
    {
        for (Map.Entry<String, Integer> entry : Data.getHashMap().entrySet())
        {
            System.out.println();
            System.out.print("{ " + entry.getKey() + " } ");
            int bktIdx = entry.getValue();
            
            while (bktIdx != -1)
            {
                Bucket bkt = Data.getSimSecMem()[bktIdx];

                System.out.print("-> [ ");
                for (Record record : bkt.recordArr)
                {
                    if (record != null)
                        System.out.print(record.id + " ");
                }
                System.out.print("]");
                bktIdx = bkt.nextBucket;
            }
        }
        System.out.println();
    }
}