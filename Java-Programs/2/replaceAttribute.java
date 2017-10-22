import java.io.IOException;
import java.util.Scanner;
import java.util.*;

class replaceAttribute
{
    static HashMap<Integer,Integer> categorical;
    static ArrayList<String[]> data;
    static ArrayList<String> column;
    static Scanner sc = new Scanner(System.in);
    replaceAttribute(){
        categorical = new HashMap<>();
    };
    public void replaceByMean()
    {
        double mean;
        int sum=0;
        System.out.println("Enter the column number to replace missing attributes with mean");
        int cno = sc.nextInt();
        for(int i =0;i<data.size();i++)
        {
            String attribute = data.get(i)[cno];
            if(!attribute.equals(" "))
            {
                sum+= Integer.parseInt(attribute);
            }
        }
        mean = sum/data.size();
    for(int i = 0;i<data.size();i++)
    {
        String[] row = data.get(i);    
        for(int j=0;j<row.length;j++)
        {
            if(row[cno].equals(" "))
            {
                row[cno]=String.valueOf(mean);
            }
            System.out.print(row[j]+", ");
        }
        System.out.println();
    }
}
    public void replaceCategorical()
    {
        System.out.println("Enter the column number to replace missing categorical attributes");
        int cno = sc.nextInt();      
        System.out.println(data.get(1)[5]);
        for(int i =0;i<data.size();i++)
        {
            if(!data.get(i)[cno].equals(" "))
            {
                int attribute = Integer.parseInt(data.get(i)[cno]);
                if(!categorical.containsKey(attribute))
                {
                    categorical.put(attribute,1);
                }
                else
                {
                    categorical.put(attribute, categorical.get(attribute)+1);
                }
            }    
        } 
        int maxKey=0;
        int maxEntry=Integer.MIN_VALUE;
        for(Map.Entry<Integer,Integer>entry : categorical.entrySet())
        {
            int key = entry.getKey();
            int value = entry.getValue();
            if(value>maxEntry)
            {
                maxKey = key;
            }
        }
        
        for(int i = 0;i<data.size();i++)
        {
            String[] row = data.get(i);    
            for(int j=0;j<row.length;j++)
            {
                if(row[cno].equals(" "))
                {
                    row[cno]=String.valueOf(maxKey);
                }
                System.out.print(row[j]+", ");
             }
        System.out.println();
        }
    }
    
    public static void callme() throws IOException
    {
        csvReader reader = new csvReader("input.csv", ",");
        data = reader.returnData();
        column = reader.returnColumns();
        System.out.println("Data Format");
        reader.printCsv();
        replaceAttribute replace = new replaceAttribute();
        replace.replaceByMean();
        replace.replaceCategorical();
    }
}