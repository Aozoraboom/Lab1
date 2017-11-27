package lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	protected  Graph G;
	 public Controller ()
	 {
		 G= new Graph(readFileByChars("dd.txt").length); 
		 G.creat(readFileByChars("dd.txt"));
	 }
	 
	public String text(String str)
	{
		return G.generateNewText(str);
	}
	public Graph show() throws IOException 
	{
		return G;
	}
	public String shortpath(String word1, String word2) throws IOException
	{ 
		
		return G.calcShortestPath(word1, word2);
	}
	
	public String randompath() 
	{
		String result =G.randomWalk();
		WriteFile("out.txt",result); 
		return result;
	}
	public String  printBridgeWords(String word1, String word2) {      
		String result = null;
		String r=G.queryBridgeWords(word1, word2);
		   if (r=="error") {
	     	   result="Input error!";
		         
	        } else if (r=="noword1") {
	        	result ="No "+word1+" in the graph!";
	        }else if (r.equals("noword2")) {
	 	       result ="No "+word2+" in the graph!";
	        } else if (r=="noboth") { 
	 	      result="No "+word1+" and "+word2+" in the graph!";
	 	    }else if (r=="nobrige" ) {
	 	       result="No bridge words from "+word1+ " to "+word2+" !";
	 	     } else {
	 	       String[] t = r.toLowerCase().split(" ");        
	 	       if (t.length == 1) {
	 	    	  result = "The bridge words from "+word1+" to "+word2+" is: "+t[0];
	 	       } else if (t.length > 1) {
	 	    	  result="The bridge words from "+word1+ " to "+word2+" are:";
	 	       for (int i = 0;i < t.length - 2;i++) {
	 	         result+="“" + t[i] + "”,";
	 	       }
	 	      result+="“" + t[t.length - 2] + "”" + " and " + "“" + t[t.length - 1] + "”" ;
	 	       }}
		   return result;
		   
	}

	protected static String[] readFileByChars ( String fileName) {  
        File file = new File(fileName);  
        Reader reader = null;  
        try {   
            reader = new InputStreamReader(new FileInputStream(file));  
            int tempchar;  
            char temp[][] = new char[1000][20];  
            
            int i= 0,j=0;
            while ((tempchar = reader.read()) != -1) {  
              
                if ( tempchar>='a' && tempchar<='z' ) {  
                  
                    temp[j][i]= (char) tempchar;
                    i++;
                }  
                else if(tempchar>='A' && tempchar<='Z')
                {
                	tempchar+=32;
                
                	 temp[j][i]=(char)tempchar;
	                 i++;
                }
                else 
                {	
                j++;
                i=0;}
              
            }  
              
            reader.close();  
            return change(temp,j);
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return null;  }
  protected static String[] change ( char x[][],int n ){
	String[] a=new String[n];
	List<String> l=new ArrayList<String>();
	  for (int i=0;i<n;i++)
	{   
		a[i]=String.valueOf(x[i]);
		a[i]=a[i].trim();
	}
    for (int i=0;i<n;i++)
    {
    	if(!a[i].isEmpty())
    		l.add(a[i]);
    }
    
    String[] b=new String[l.size()];
    l.toArray(b);

    return b;
  }
  protected static void WriteFile(String filename,String filec) 
  {
        try {  
            File file = new File(filename);  
            PrintStream ps = new PrintStream(new FileOutputStream(file));  
            ps.println(filec);// 往文件里写入字符串  
           
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  }	
  

}
