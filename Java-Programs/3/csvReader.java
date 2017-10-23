import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class csvReader {
	String fileName;
	String dilimiter;
	ArrayList<HashSet<String>> data;
	BufferedReader br;
    int labels = 1;
    ArrayList<String> column;
	csvReader(String fileName,String dilimiter) throws IOException{
		this.fileName = fileName;
		this.dilimiter = dilimiter;
		data = new ArrayList<>();
        column = new ArrayList<>();
		br = new BufferedReader(new FileReader(new File(this.fileName)));
		String line;
		while ((line = br.readLine()) !=null) {
           
			String[] singleLine = line.split(this.dilimiter);
            HashSet<String> temp = new HashSet<>();
            for(String str:singleLine)
            {
                temp.add(str);
            }
			data.add(temp);
		}
	}
    public ArrayList<HashSet<String>> returnData()
    {
        return data;
    }
    public ArrayList<String> returnColumns()
    {
        return column;
    }
	@SuppressWarnings("unchecked")
    public void printCsv(){

		for (HashSet<String> x: data){
			Iterator it = x.iterator();
            while(it.hasNext())
            {
                System.out.print(it.next()+", ");
            }
            System.out.println();
		}
	}
	
}