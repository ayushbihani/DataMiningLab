import java.io.*;
import java.util.*;

class naiveBayes
{
    ArrayList<String[]> dataset, testdataset, trainDataset;
    HashMap<ArrayList<String[]>> data2class;
    
    naiveBayes()
    {
        dataset = new ArraList<>();
        testdataset = new ArraList<>();
        trainDataset = new ArraList<>();
        data2class = new HashMap<>();
    }
    
    public void splitDataset(ArrayList<String[]> dataset, int split)
    {
        int length = dataset.size();
        traindataset = dataset.subList(0,(int) split*length);
        testDataset = dataset.subList((int)split*length, length-1);
    }
    
    public void classifyClass(ArrayList<String[]> dataset)
    {
        for(int i =0 ;i< dataset.size();i++)
        {
            String[] temp = dataste.get(i);
            int label = Inteer.parseInt(temp[temp.length-1]);
            if(data2class.contains(label))
            {
                data2class.put(label, data2class.get(label).append(temp)); 
            }
        }        
    }
    
}