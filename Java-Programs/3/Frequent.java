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
    static HashMap<String,Integer> items;
    static int noOfTransactions;
    static String[] freqItemset;
    Frequent()throws IOException
    {
        AllItemSets = new ArrayList<>();
        items = new HashMap<>();
        freqItemset = new String[4];
        powerset = new ArrayList<>();
        csvReader reader= new csvReader("input.csv",",");
        AllItemSets = reader.returnData();
        reader.printCsv();
        noOfTransactions = AllItemSets.size(); 
    } 

    public ArrayList<HashSet<String>> powerSet(HashSet<String> itemset)
    {
        ArrayList<HashSet<String>> set = new ArrayList<>();
        int n = itemset.size();
        for(int i = 0;i< (1<<n);i++)
        {
            HashSet<String> hash = new HashSet<>();
            for(int j =0;j<n;j++)
            {
                if((i&(1<<j))>0)
                {
                    hash.add(freqItemset[j]);
                }
            }
            set.add(hash);
        }
        return set;
    }

    public void generateRulesItemset(HashSet<String> itemset)
    {
        Iterator it = itemset.iterator();
        HashSet<String> itemsetCopy = new HashSet<>(itemset);
        while(it.hasNext())
        {
            String item = it.next().toString();
            itemsetCopy.remove(item);
            ArrayList<HashSet<String>> newItems = powerSet(itemsetCopy);
            generateRules(item, newItems);
            itemsetCopy.add(item);    
        }
    }
    @SuppressWarnings("unchecked")
    public void generateRules(String lhs,ArrayList<HashSet<String>> newItems)
    {
        for(HashSet x :newItems)
        {
            int supportY = countSupport(x);
            int supportX = items.get(lhs);
            if((supportY > support )&& (supportX>support))
            {
                double confidence = getConfidence(supportY, supportX);
                System.out.print("{"+lhs+"}->");
                Iterator it = x.iterator();
                while(it.hasNext())
                {
                    System.out.print(it.next()+",");
                }
                System.out.print("\t"+supportX +"\t"+ supportY+"\t"+confidence);
                System.out.println();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public int countSupport(HashSet<String> itemset){
        int count = 0;
        for(HashSet i:AllItemSets)
        {
            if(i.containsAll(itemset))
                count++;
        }
        return count;
    }

    public double getConfidence(int support1,int support2)
    {
        return (support1/support2);
    }

    public void printPowerSet()
    {
        System.out.println("Frequent ItemSets are");
        for(int i =0;i<powerset.size();i++)
        {
            HashSet<String> temp = powerset.get(i);
            Iterator iterator = temp.iterator();
            while(iterator.hasNext())
            {
                System.out.print(iterator.next()+", ");
            }
            System.out.println(); 
        }
    }
    @SuppressWarnings("unchecked")
    public void rules()
    {
        for(HashSet x:powerset)
        {
            if(x.size()>=2)
            {
                Iterator it =x.iterator();
                while(it.hasNext())
                {
                    System.out.print(it.next()+",");
                }
                System.out.println();
                //generateRulesItemset(x);
            }
        }
    }

    public int getNoItems()
    {
        for(int i =0;i<AllItemSets.size();i++)
        {
            HashSet<String> itemset = AllItemSets.get(i);
            Iterator it = itemset.iterator();
            while(it.hasNext())
            {
                String item = it.next().toString();
                if(!items.containsKey(item))
                    items.put(item,1);
                else
                {
                    items.put(item,items.get(item)+1);
                }    
            }
        }
        return items.size();
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
        f.getNoItems();
        f.powerset=f.powerSet(new HashSet(Arrays.asList(freqItemset)));
        f.printPowerSet();
       
        f.rules();
    }
}