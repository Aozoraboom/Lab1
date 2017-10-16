package lab1;

import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class io {

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
	  public static void WriteFile(String filename,String filec) 
	  {
	        try {  
	            File file = new File(filename);  
	            PrintStream ps = new PrintStream(new FileOutputStream(file));  
	            ps.println(filec);// ���ļ���д���ַ���  
	           
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	  }
	  public static void main(String[] args) {
		   
		Graph G= new Graph(readFileByChars("dd.txt").length); 
		G.creat(readFileByChars("dd.txt"));
				Scanner sc=new Scanner(System.in);
		while (true){
		System.out.print("ѡ���ܣ�0.�˳� 1.��ӡͼ  2.��ѯ�ŽӴ� 3���������ı� 4.��ѯ���·�� 5���������\n");
		int a=sc.nextInt();
		if(a==0)
			break;
		else if(a==1)
		{
			G.showDirectedGraph();

		}
		else if(a==2)
		{
		System.out.print("��ѯ�ŽӴʣ�\n");
		String word1=new String();
		String word2=new String();
		System.out.print("Word1��");
		word1=sc.next();
		System.out.print("Word2��");
		word2=sc.next();
		G.printBridgeWords( word1, word2,G.queryBridgeWords(word1,word2));
		}
		else if(a==3)
		{sc.nextLine();
		String str=new String();
		System.out.print("�����ı���");
		str=sc.nextLine();
		G.generateNewText(str);
		System.out.print("\n");
		}
		else if(a==4)
		{
		System.out.print("��ѯ���·����");
		System.out.print("Word1��");
		String word3=new String();
		word3=sc.next();
		System.out.print("Word2��");
		String word4=new String();
		word4=sc.next();
		G.calcShortestPath(word3, word4);
		}
		else if(a==5)
		{
				System.out.print("������ߣ�");
		WriteFile("out.txt",G.randomWalk()); 
		System.out.print("\n");
		}
		else
		{
			System.out.print("��������\n");
			
		}
		}
		
    }

}

