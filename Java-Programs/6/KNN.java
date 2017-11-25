import java.io.*;
import java.util.*;

class KNN
{
    static ArrayList<double[]> featureDataset;
    int k;
    KNN()
    {
        featureDataset = new ArrayList<>();
        k=3;
    }
    public double calcDistance(double[] first, double[] second)
    {
        int length = first.length;
        double distance = 0;
        for(int i =0 ;i< length;i++)
        {
            distance += Math.pow((first[i]-second[i]),2);
        }
        return Math.sqrt(distance);
    }
    public ArrayList<double[]> getNeighbors(double[] test)
    {
        ArrayList<featureToDistance> distances = new ArrayList<>();    
        for(int i =0 ;i< featureDataset.size();i++)
        {
            double distance = calcDistance(featureDataset.get(i), test);
            featureToDistance d = new featureToDistance(featureDataset.get(i),distance);
            distances.add(d);  
        }
        
        Collections.sort(distances);
        ArrayList<double[]> nearestNeighbor = new ArrayList<>();
        for(int i=0;i<k;i++)
        {
            nearestNeighbor.add(distances.get(i).features);
        }
        return nearestNeighbor;
    }

    public double getPrediction(double[] test)
    {
        ArrayList<double[]> neighbors = getNeighbors(test);
        HashMap<Double, Integer> nearest = new HashMap<>();
        for(int i=0;i<k;i++)
        {
            double[] temp = neighbors.get(i);
            double label = temp[temp.length-1];
            if(!nearest.containsKey(label))
            {
                nearest.put(label,1);
            }
            else
            {
                nearest.put(label,nearest.get(label)+1);
            }
        }

        double key=-1;
        int max = -1;
        for (Map.Entry<Double, Integer> entry : nearest.entrySet())
        {
            if (entry.getValue()>max)
            {
                max = entry.getValue();
                key = entry.getKey();
            }
        }
        return key;
    }
    
    public static void main(String[] args)throws IOException
    {
        csvReader reader = new csvReader("input.txt", ",");
        KNN knn = new KNN();
        knn.featureDataset = reader.returnData();
        double[] arr ={6.0,3.5,4.1,1};
        System.out.println(knn.getPrediction(arr));
    }
}