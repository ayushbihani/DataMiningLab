import java.io.*;
import java.util.*;
class Kmeans
{
    static int k,maxIters;
    static ArrayList<double[]> centroids,dataset;
    static int featureSize;
    static HashMap<Integer,ArrayList<double[]>> clusterToFeature;
    Kmeans(int k,int maxIters)
    {
        this.k = k ;
        this.maxIters = maxIters;
        centroids = new ArrayList<>();
    }
    public double calcDistance(double[] first, double[] second){
        double distance = 0;
        for(int i=0;i<first.length;i++)
        {
            distance+=Math.pow(first[i]-second[i],2);
        }
        return Math.sqrt(distance);
    }
    public void initCentroids(){
        for(int i =0;i<k;i++)
        {
            centroids.add(dataset.get(i));
        }
    }
    public ArrayList<Double> findDistanceFromAllCentroid(double[] test)
    {
        ArrayList<Double> distance = new ArrayList<>();
        for(int i=0;i<k;i++)
        {
            double dist = calcDistance(test,centroids.get(i));
            distance.add(dist);
        }
        return distance;
    }
    public int findCluster(ArrayList<Double> distanceFromCentroid)
    {
        int index = -1;
        double min = 32767;
        for(int i=0;i<distanceFromCentroid.size();i++)
        {
            if(min>distanceFromCentroid.get(i))
            {
                min = distanceFromCentroid.get(i);
                index = i;
            }
        }
        return index;
    }

    public void recomputeCentroid(HashMap<Integer,ArrayList<double[]>> clusters){
        
        for(Map.Entry<Integer, ArrayList<double[]>> entry:clusters.entrySet())
        {
            ArrayList<double[]> cluster = entry.getValue();
            int index = entry.getKey();
            double[] newCentroid = new double[featureSize];
            for(double[] cls:cluster)
            {
                for(int k=0;k<cls.length;k++)
                {
                    newCentroid[k]+=cls[k]/cluster.size();
                }
            }
            centroids.add(index,newCentroid);
        }
    }
    public void printClusters()
    {
        for(Map.Entry<Integer, ArrayList<double[]>> entry:clusterToFeature.entrySet())
        {
            System.out.print(entry.getKey()+":");
            ArrayList<double[]> temp = entry.getValue();
            for(int i=0;i<temp.size();i++)
            {
                System.out.print(Arrays.toString(temp.get(i)) +", ");
            }
            System.out.println();
        }
    }
    public void findCentroids(){
        for(int i =0;i<maxIters;i++)
        {
            HashMap<Integer,ArrayList<double[]>> clusters = new HashMap<>();
            for(int j=0;j<k;j++)
            {
                clusters.put(j,new ArrayList<double[]>());
            }
            for(int m=0;m<dataset.size();m++)
            {
                ArrayList<Double> distanceFromCentroid = findDistanceFromAllCentroid(dataset.get(m));
                int cluster = findCluster(distanceFromCentroid);
                ArrayList<double[]> temp = clusters.get(cluster);
                temp.add(dataset.get(m));
                clusters.put(cluster, temp);
            }
            recomputeCentroid(clusters);
            clusterToFeature = clusters;
        }   
    }
    public void predict(double[] test)
    {
        ArrayList<Double> distances = findDistanceFromAllCentroid(test);
        System.out.println("Datapoint belongs to cluster:"+findCluster(distances));
    }
    public static void main(String[] args)throws IOException
    {
        Kmeans means = new Kmeans(3,100);
        csvReader reader = new csvReader("input.txt",",");
        dataset = reader.returnData();
        means.featureSize = dataset.get(0).length;
        means.initCentroids();
        means.findCentroids();
        means.printClusters();
        double[] test = {2.0,3.0,4.5};
        means.predict(test);
    }
    
}
