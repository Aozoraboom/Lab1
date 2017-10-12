package lab1;



777

import java.io.File;
import java.util.*;
public class Graph{
	protected static int m;
	protected static String[] list;  
	protected static int[][] edge; 
	static final int maxWeight=-1;
	protected static int[][] path;   
	protected static int[][] dist; 
	private List<String> re=new ArrayList< String>();
	public Graph(int n) {

		list=new String[n];
		edge=new int[n][n];
		for (int i=0;i<n;i++)
		{
			for (int j=0;j<n;j++)
			{
				edge[i][j]= (i == j) ? 0 : maxWeight; 
			}
		}
	} 
	public void creat( String[] t)
	{ int j=0;
		for(int i=0;i<t.length-1;i++) {
			int a=search(t[i]),b=search(t[i+1]);
		   if(a<0 && b<0)
		   {list[j]=t[i];
			if(edge[j][j+1]<0)
			{edge[j][j+1]=1;}
			else edge[j][j+1]++;
			j++;}
		   else if(a>=0 && b<0)
		   {
				if(edge[a][j]<0)
				{edge[a][j]=1;}
				else edge[a][j]++;
		   }
		   else if(a>=0 && b>=0)
		   {
				if(edge[a][b]<0)
				{edge[a][b]=1;}
				else edge[a][b]++;
		   }
		   else if(a<0 && b>=0)
		   {    list[j]=t[i];
			   if(edge[j][b]<0)
				{edge[j][b]=1;}
				else edge[j][b]++;
			   j++;
		   }
		}
		   int b = search(t[t.length-1]);
		   if(b<0)
		   {   list[j]=t[t.length-1];
		       m=j+1;
		       return;
		   }
		   else 
			   {m=j;return;}
	}


	public int  search (String t)
	{
		for (int i=0;i<list.length;i++)
		{
				if(t.equals(list[i]))
				{
					return i;
				}
					
		}
		return -1;
	}
	
	public void showDirectedGraph()
	
	{
		String str="";
		GraphViz Gr=new GraphViz();
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				if (edge[i][j]>0)
					{
						str=str+list[i];
						str=str+"->";
						str=str+list[j];
						str=str+"[label=\"";
						str=str+edge[i][j];
						str=str+"\"]";
						str=str+"\n";
					}
			}
		}
		Gr.addln(Gr.start_graph());
		Gr.add(str);
		Gr.addln(Gr.end_graph());
		String type="jpg";
		File out=new File("graphFile"+"."+type);
		Gr.writeGraphToFile(Gr.getGraph(Gr.getDotSource(),type),out);
		System.out.println("创建成功！");
	}

	
	public void printBridgeWords(String word1, String word2,String result)
	{  
	   
		int t1=search(word1);
		int t2=search(word2);
		if(t1<0 || t2<0)
	    {if(t1<0 && t2>=0)
		System.out.printf("No “%s” in the graph!\n",word1);
	    else if(t1>=0 &&t2<0)
	    	System.out.printf("No “%s” in the graph!\n",word2);
	    else System.out.printf("No “%s” or “%s” in the graph!\n",word1,word2);}
		else if(result==null)
		{System.out.printf("No bridge words from “%s” to “%s”!\n",word1,word2);
		}
		    else{String[] t=result.toLowerCase().split(" ");
	
	    
	     if(t.length==1)
		System.out.printf("The bridge words from “%s” to “%s” is:“%s”\n",word1,word2,t[0]);
     	else if(t.length>1)
		{System.out.printf("The bridge words from “%s” to “%s” is:",word1,word2);
		 for (int i=0;i<t.length-2;i++)
			 System.out.print("“"+t[i]+"”,");
		 System.out.print("“"+t[t.length-2]+"”"+" and "+"“"+t[t.length-1]+"”"+"\n");}
		 }
	}

	public static String listToString(List<String> list){
	      if(list==null){
	      return null;
	   }
	   StringBuilder result = new StringBuilder();
	   boolean first = true;
	   for(String string :list) {
	      if(first) {
	         first=false;
	      }else{
	         result.append(" ");
	      }
	      result.append(string);
	   }
	   return result.toString();
	}
	public String queryBridgeWords (String word1, String word2)
	{   List<String> l=new ArrayList<>();
		String result =new String();
		int num=0;
		int t1=search(word1);
		int t2=search(word2);
		if(t1<0 || t2<0)
		{   
			result= null;
		}
		else if(t1>=0 && t2>=0 ) {
		for (int i=0;i<=m;i++)
		{
			if(edge[t1][i]>0 && edge[i][t2]>0)
				{l.add(list[i]);
			    num++;}
		}
		if(num<=0)
			{
			result= null;}

		else if(num>=1)

		{result=listToString(l);
		}
		}
		return result;
		
	}
	public String generateNewText (String inputText)
	{   if(inputText!=null)
		{List<String> l=new ArrayList<>();
		String[] text=inputText.toLowerCase().split(" ");
		for (int i=0;i<text.length-1;i++)
		{   l.add(text[i]);
			if(queryBridgeWords(text[i],text[i+1])!=null)
			{
				String[] t=queryBridgeWords(text[i],text[i+1]).split(" ");
				l.add(t[(int) (Math.random()*(t.length))]);
			}
		}
		l.add(text[text.length-1]);
		String str=listToString(l);
		System.out.print(str);
		return str;}
	return null;}
	/*
	public String calcShortestPath(String word1, String word2)
	{
		 
		String result=new String();
		 path=new int[m][m];
		 dist=new int[m][m];
		floyd();   
		int t1=search(word1);
		int t2=search(word2);
		if(t1==t2)
		{System.out.print("两次相同");
		return null;}
		if(t1>=0&&t2>=0)
		{  findPath(t1,t2);
		if(edge[t1][t2]<=0 && re.isEmpty())
			{System.out.print("不可达!\n");
			re.clear();
			return null;}
		
		else re.add(0, word1); 
		if(dist[t1][t2]>edge[t1][t2]&&edge[t1][t2]>0)
		{System.out.print(word1+"->"+word2);
		System.out.print("\n"+"最短距离："+edge[t1][t2]+"\n");
		re.clear();
        return result;} 
		else {
        for (int i=0;i<re.size();i++)
         result+=(re.get(i)+"->");
		result+=word2;
		System.out.print(result);
		System.out.print("\n"+"最短距离："+dist[t1][t2]+"\n");
		re.clear();
        return result;
		}
		}

		
		else if(t1<0 && t2>=0)
		System.out.printf("No “%s” in the graph!\n",word1);
	    else if(t1>=0 &&t2<0)
	    	System.out.printf("No “%s” in the graph!\n",word2);
	    else System.out.printf("No “%s” or “%s” in the graph!\n",word1,word2);
		re.clear();
		return null;
	}
*/
	
	
	public String calcShortestPath(String word1, String word2)
	{
		 
		String result=new String();
		 path=new int[m][m];
		 dist=new int[m][m];
		floyd();   
		int t1=search(word1);
		int t2=search(word2);
		if(t1==t2)
		{System.out.print("两次相同");
		return null;
		}
		if(t1>=0&&t2>=0)
		{  findPath(t1,t2);
		if(edge[t1][t2]<=0 && re.isEmpty())
		{System.out.print("不可达!\n");
		re.clear();
		return null;}
		else re.add(0, word1); 
		
		
		
		String str="";
		GraphViz Gr=new GraphViz();
		
		
        for (int i=0;i<re.size();i++)
        {
        	result+=re.get(i);
        	result+=" ";
        	result+="[style=\"filled\",color=\"black\",fillcolor=\"chartreuse\"]";
        	result+=";";
        	result+="\n";
        }
        	result+=word2;
        	result+=" ";
        	result+="[style=\"filled\",color=\"black\",fillcolor=\"chartreuse\"]";
        	result+=";";
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++)
			{
				if (edge[i][j]>0)
					{
						str=str+list[i];
						str=str+"->";
						str=str+list[j];
						str=str+"[label=\"";
						str=str+edge[i][j];
						str=str+"\"]";
						str=str+"\n";
					}
			}
		}
		Gr.addln(Gr.start_graph());
		Gr.add(result);
		Gr.add(str);
		Gr.addln(Gr.end_graph());
		String type="jpg";
		File out=new File("graphFile"+"."+type);
		Gr.writeGraphToFile(Gr.getGraph(Gr.getDotSource(),type),out);
		System.out.print("最短距离："+dist[t1][t2]+"\n");
		re.clear();
        return result;
		}
		
		else if(t1<0 && t2>=0)
		System.out.printf("No “%s” in the graph!\n",word1);
	    else if(t1>=0 &&t2<0)
	    	System.out.printf("No “%s” in the graph!\n",word2);
	    else System.out.printf("No “%s” or “%s” in the graph!\n",word1,word2);
		return null;
	}
	
	protected  void floyd(){   
    	int inf=Integer.MAX_VALUE;
        for(int i=0;i< m;i++){   
            for(int j=0;j< m;j++){   
                path[i][j]=-1;   
                if(edge[i][j]<0)
                	{dist[i][j]=inf;}
                else {dist[i][j]=edge[i][j]; }  
            }   
        }         
        for(int k=0;k<m;k++){   
            for(int i=0;i< m;i++){   
                for(int j=0;j<m;j++){   
                    if(dist[i][k]!=inf&& dist[k][j]!=inf&&dist[i][k]+dist[k][j]<dist[i][j]){
                        dist[i][j]=dist[i][k]+dist[k][j];   
                        path[i][j]=k;   
                    }   
                }   
            }   
        }   

    }   

	  protected void findPath(int i,int j){   
	        int k=path[i][j];   
	        if(k==-1)return ;   
	        findPath(i,k);  
	        re.add(list[k]);   
	        findPath(k,j); 
	        
	    }  
    
    public String randomWalk()
    {  
       int[][] p=new int[m][m];
       for(int i=0;i<m;i++)
    	   for(int j=0;j<m;j++)
    		   p[i][j]=-1;
    	String result=new String();
     	int a=(int)(Math.random()*m);
     	result+=list[a];

    	while (ran(a)!=-1 )
    	{int b=ran(a);
    		if(p[a][b]==1)
    		break;
    		else {
    		result+=(" "+list[b]);
    		p[a][b]=1;
    		a=b;}
    	
    	}
    	System.out.print(result);
		return result;
    	
    }
    
    protected int ran(int x)
    {
    	int[] l = new int[m];
    	int j=0;
    	for (int i=0;i<m;i++)
    	{
    	 if(edge[x][i]>0)
    	 {l[j]=i;
    	 j++;}
    	}
    	if(j>1)
		return l[(int)(Math.random()*j)];
    	else if (j==1)
    		return l[0];
    	else return -1;
    }
}
