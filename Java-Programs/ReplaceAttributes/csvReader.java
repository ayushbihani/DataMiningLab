import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class csvReader {
	String fileName;
	String dilimiter;
	ArrayList<String[]> data;
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
            if(labels == 1)
            {
                String[] columnnames = line.split(this.dilimiter);
                for(String c:columnnames)
                    column.add(c);
                labels =0;
                continue;
            }
			String[] singleLine = line.split(this.dilimiter);
			data.add(singleLine);
		}
	}
    public ArrayList<String[]> returnData()
    {
        return data;
    }
    public ArrayList<String> returnColumns()
    {
        return column;
    }
	void printCsv(){
        for(String c:column)
        {
            System.out.print(c+", ");
        }
        System.out.println();
		for (String[] line:data){
			for(String word:line)	{
				System.out.print(word + "\t");
			}
			System.out.println();
		}
	}
	
}