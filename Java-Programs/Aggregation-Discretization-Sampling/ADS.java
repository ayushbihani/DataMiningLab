import java.io.IOException;
import java.util.Scanner;
import java.util.*;

class ADS
{
    static ArrayList<String[]> data;
    static ArrayList<String> column;
    static int bins;
    static Scanner sc = new Scanner(System.in);
   
    public void aggregate()
    {
        System.out.println("Enter the start and end column for aggregation");
        int startColumn = sc.nextInt();
        int temp = startColumn;
        int endColumn = sc.nextInt();
        System.out.println("ID, "+"Name, "+"Sex, "+ "Age, "+"Salary");
        int len =data.get(1).length -(endColumn-startColumn+1);
        for(int i =0 ;i< data.size();i++)
        {
            String[] row = data.get(i);
            int totalSalary = 0;
            while(startColumn<=endColumn)
            {
                totalSalary+= Integer.parseInt(row[startColumn]);
                startColumn++; 
            }
            startColumn = temp;
            for(int j=0;j<temp;j++)
            {
                System.out.print(row[j]+", ");
            }
            System.out.print(totalSalary);
            System.out.println();
        }
    }

    public void discretize()
    {
        System.out.println("Please enter the number of bins for discretization and column number");
        bins = sc.nextInt();
        int cno = sc.nextInt();
        int maxAge = Integer.MIN_VALUE;
        int minAge = Integer.MAX_VALUE;
        for(int i =0;i<data.size();i++)
        {
            String[] row = data.get(i);
            int age = Integer.parseInt(row[cno]);
            if(age>maxAge)
            {
                maxAge=age;
            }
            if(age<minAge)
            {
                minAge = age;
            }
        }
        int diff = maxAge-minAge;
        int rem = diff % bins;
        diff+=(bins-rem);
        int binsize = diff/bins;
        int binCount = 1;
        int temp = minAge;
        System.out.println("Categorical Attributes are");
        while(temp<maxAge)
        {
            System.out.println(temp+"-"+(temp+binsize)+"->"+ binCount);
            temp+=binsize;
            binCount++;
        }
        temp = minAge;
        binCount=1;

        for(int i=0;i<data.size();i++)
        {
            String[] row = data.get(i);

            int age = Integer.parseInt(row[cno]);  
            while(age>(temp+binsize))
            {
                binCount++;
                temp+=binsize;
            }
            data.get(i)[cno] = String.valueOf(binCount);
            for(int j=0;j<row.length;j++)
            {
                System.out.print(row[j]+", ");
            }
            System.out.println();
            binCount = 1;
            temp=minAge;
        } 
    }
    public static void main(String[] args) throws IOException
    {
        csvReader reader = new csvReader("input.csv", ",");
        data = reader.returnData();
        column = reader.returnColumns();
        System.out.println("Data Format");
        reader.printCsv();
        ADS ads = new ADS();
        ads.discretize();
        ads.aggregate();
    }
}