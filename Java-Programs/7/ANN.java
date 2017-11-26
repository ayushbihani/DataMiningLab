import java.io.*;
import java.util.*;

class ANN
{
    int epoch;
    double rate;
    int noFeatures;
    double weights[];
    static ArrayList<double[]> x;
    static ArrayList<Double>y;
    static ArrayList<double[]>dataset;
    Random rand;
    int max = 32767;
    int min = -32767;
    ANN(int epoch, double rate, int noFeatures)
    {
        this.epoch = epoch;
        this.rate =rate;
        this.noFeatures = noFeatures;
        rand = new Random();
        weights = new double[noFeatures+1];
        for(int i=0;i<noFeatures;i++)
            weights[i]= rand.nextInt(max-min)+min; 
        x = new ArrayList<>();
        y = new ArrayList<>();
    }

    public double activation(double x)
    {
        return (x>=0)?1:0;
    }

    public double predict(double[] x)
    {
        double sum=weights[0];
        for(int i=0;i<noFeatures;i++)
        {
            sum+=weights[i+1]*x[i];
        }
        return activation(sum);
    }

    public void trainWeights()
    {
        for(int i =0 ;i< epoch;i++)
        {
            for(int j=0;j<x.size()-1;j++)
            {
                double prediction = predict(x.get(j));
                double error = y.get(j)-prediction;
                weights[0]=weights[0]+rate*error;
                for(int k =0;k<noFeatures;k++)
                {
                    weights[k+1]=weights[k+1]+(rate*error*x.get(j)[k]);
                }
            }    
        }
    }

    public void perceptron(double[] test)
    { 
            System.out.println(predict(test));
    }

    public static void main(String[] args)throws IOException
    {
        csvReader csv = new csvReader("input.txt",",");
        dataset = csv.returnData();
        ANN ann = new ANN(5000,0.01,dataset.get(0).length-1);
        for(int i=0;i<dataset.size();i++)
        {
            double[] temp = dataset.get(i);
            int len = temp.length;
            x.add(Arrays.copyOfRange(temp,0,len-1));
            y.add(temp[len-1]);
        }     
        ann.trainWeights();
        double[] test = {1,0};
        ann.perceptron(test);
    }
}