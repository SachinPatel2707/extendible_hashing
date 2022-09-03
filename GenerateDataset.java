import java.io.*;
import java.util.*;

public class GenerateDataset 
{
    /*
     * generates 50,000 random records and stores them in a single text file
     */
    public void generateInitialData () throws IOException
    {
        File original = new File("original.txt");
        original.createNewFile();
        if (original.exists())
        {
            Random random = new Random();
            FileWriter fwrite = new FileWriter(original);
            Set<Integer> idSet = new HashSet<>();

            for (int index = 1; index <= 100; index++)
            {
                int id = random.nextInt(60001);
                if (idSet.contains(id))
                {
                    index -= 1;
                    continue;
                }
                int saleAmount = random.nextInt(60001);
                String customerName = generateRandomString();
                int category = random.nextInt(1501);

                fwrite.write(id + " " + saleAmount + " " + customerName + " " + category + "\n");
            }
            fwrite.close();
        }
    }

    public String generateRandomString ()
    {
        StringBuilder buffer = new StringBuilder(3);
        int alphabetStart = 97;
        int alphabetEnd = 122;
        for (int i = 0; i < 3; i++)
        {
            int randomInt = (int) (Math.random() * (alphabetEnd - alphabetStart + 1)) + alphabetStart;
            buffer.append((char) randomInt);
        }

        return (buffer.toString());
    }
}
