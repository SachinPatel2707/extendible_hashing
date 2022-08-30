public class HashOperations {
    public String getHashValue (int id, int depth)
    {
        String binaryStr = getBinary(id);
        binaryStr = binaryStr.substring(0, depth);

        return binaryStr;
    }

    private String getBinary (int n)
    {
        StringBuilder binaryStr = new StringBuilder("0000000000000000");
        int i = binaryStr.length()-1;

        while (n > 0)
        {
            binaryStr.setCharAt(i--, (char)((n % 2) + '0'));
            n = n / 2;
        }

        return binaryStr.toString();
    }
}
