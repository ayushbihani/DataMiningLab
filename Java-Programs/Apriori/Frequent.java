import java.io.IOException;
import java.util.Scanner;
import java.util.*;
class Frequent
{
    static double support;
    static double confidence;
    static ArrayList<HashSet<String>> powerset;
    static ArrayList<HashSet<String>> AllItemSets;
    static Scanner sc = new Scanner(System.in);
    static String[] freqItemset;
    Frequent()throws IOException
    {
        AllItemSets = new ArrayList<>();
        freqItemset = new String[4];
        powerset = new ArrayList<>();
        csvReader reader= new csvReader("input.csv",",");
        AllItemSets = reader.returnData();
    } 
    public ArrayList<HashSet<String>> powerSet(String[] itemset)
    {
        ArrayList<HashSet<String>> set = new ArrayList<>();
        int n = itemset.length;
        for(int i = 0;i< (1<<n);i++)
        {
            HashSet<String> hash = new HashSet<>();
            for(int j =0;j<n;j++)
            {
                if((i&(1<<j))>0)
                {
                    hash.add(itemset[j]);
                }
            }
            set.add(hash);
        }
        return set;
    }
    @SuppressWarnings("unchecked")
    public void generate(HashSet<String> set)
    {
        double supportY = countSupport(set);
        ArrayList<HashSet<String>> newItems = powerSet(set.toArray(new String[set.size()]));
        for(int i =0;i< newItems.size()-1;i++)
        {
            HashSet<String> x = newItems.get(i);
            double supportX = countSupport(x);
            if(supportX >= support && supportY >=support)
            {                                                                                     
               double confidenceScore = getConfidence(supportY, supportX);   
               if(confidenceScore > confidence)
               {
                Iterator it = x.iterator();
                System.out.print("{");    
                while(it.hasNext())
                {
                    System.out.print(it.next()+",");
                }
                System.out.print("}->{");
                Iterator it2 = set.iterator();
                while(it2.hasNext())
                {
                   String temp = it2.next().toString();
                   if(!x.contains(temp))
                   System.out.print(temp+","); 
                }
                System.out.print("}"+"\t"+supportX +"\t"+ supportY+"\t"+confidenceScore);
                System.out.println();
               }
            }
        }
    }
    @SuppressWarnings("unchecked")
    public double countSupport(HashSet<String> itemset){
        double count = 0;
        for(HashSet i:AllItemSets)
        {
            if(i.containsAll(itemset))
                count++;
        }
        return count;
    }

    public double getConfidence(double support1,double support2)
    {
        return (support1/support2);
    }

    @SuppressWarnings("unchecked")
    public void rules(ArrayList<HashSet<String>> set)
    {
        for(HashSet x:set)
        {
            if(x.size()>=2)
            {
                Iterator it =x.iterator();
                generate(x);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args)throws IOException
    {
        Frequent f = new Frequent();
        System.out.println("Enter the 4 frequent itemset");
        for(int i =0 ;i<4;i++)
        {
            f.freqItemset[i] = sc.nextLine();        
        }
        System.out.println("Enter support and confidence thresholds");
        f.support = sc.nextDouble();
        f.confidence = sc.nextDouble();
        f.powerset=f.powerSet(freqItemset);
        f.rules(f.powerset);
    }
}