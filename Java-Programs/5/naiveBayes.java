import java.io.*;
import java.util.*;

class naiveBayes
{
    naiveBayes(){}
    public void classProb(ArrayList<Integer> classes)
    {
        HashMap<Integer,Double> cls = new HashMap<>();
        for(int c:classes)
        {
            if(!cls.containsKey(c))
                cls.put(c,1.0);
            else
                cls.put(c,cls.get(c)+1.0);    
        }
        cls.replaceAll((k,v) -> v/classes.size());
    }

    public static void main(String[] args){
        naiveBayes nb = new naiveBayes();
    }
}