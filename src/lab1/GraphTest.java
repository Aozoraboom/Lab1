package lab1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryBridgeWords() {
		Graph G= new Graph(readFileByChars("dd.txt").length); 
		G.creat(readFileByChars("dd.txt"));
		String result = G.queryBridgeWords("&&", "you");
		Assert.assertEquals("ÓÐÎÊÌâ", "Input error!", result);


	}


	 public static String[] readFileByChars ( String fileName) {  
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
	  public static String[] change ( char x[][],int n ){
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

}
