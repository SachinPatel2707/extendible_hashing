import java.io.*;
import java.util.*;

public class ExtendibleHashing_2022CSM1012
{
    static HashOperations hashOp = new HashOperations();

    public static void main (String[] args) throws IOException
    {
        GenerateDataset data = new GenerateDataset();
        // data.generateInitialData();

        System.out.println(hashOp.getHashValue(1024, 6));
    }
}