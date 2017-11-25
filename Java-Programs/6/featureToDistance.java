import java.util.*;
import java.io.*;

class featureToDistance implements Comparable<featureToDistance>
{
    double[] features;
    double dist;
    featureToDistance(double[] features, double dist)
    {
        this.features = features;
        this.dist = dist;
    }
    @Override
    public int compareTo(featureToDistance d)
    {
        if(dist == d.dist)
            return 0;
        else if(dist > d.dist)
            return 1;
        else
            return -1;        
    }
}