package lab1;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


public class Graph {
  protected static int m;
  protected static String[] list;  
  protected static int[][] edge; 
  protected final int maxWeight = -1;
  protected static int[][] path;   
  protected static int[][] dist; 
  private List<String> re = new ArrayList<String>();
 
  /**
   * set default mock parameter.（方法说明）
   * @throws Exception if has error(异常说明)
   */ 
  
  public Graph(int n) {
    list = new String[n];
    edge = new int[n][n];
    for (int i = 0;i < n;i++) {
      for (int j = 0;j < n;j++) {
        edge[i][j] = (i == j) ? 0 : maxWeight; 
      }
    }
  } 
  public int getm()
  {
	  return m;
  }
  public String[] getlist()
  {
	  return list;
  }
  public int[][] getedge()
  {
	  return edge;
  }
  /**
   * set default mock parameter.（方法说明）
   * @throws Exception if has error(异常说明)
   */ 
  public void creat(String[] t) { 
    int j = 0;
    for (int i = 0;i < t.length - 1;i++) {
      int a = search(t[i]);
      int b = search(t[i + 1]);
      if (a < 0 && b < 0) {
        list[j] = t[i];
        if (edge[j][j + 1] < 0) {
          edge[j][j + 1] = 1;
        } else {
          edge[j][j + 1]++;
        }          
        j++;
      } else if (a >= 0 && b < 0) {
        if (edge[a][j] < 0) {
          edge[a][j] = 1;
        } else {
          edge[a][j]++;
        }
      } else if (a >= 0 && b >= 0) {
        if (edge[a][b] < 0) {
          edge[a][b] = 1;
        } else {
          edge[a][b]++;
        }
      } else if (a < 0 && b >= 0)  {   
        list[j] = t[i];
        if (edge[j][b] < 0) {
          edge[j][b] = 1;
        } else {
          edge[j][b]++;
        }
        j++;
      }
    }
    int b = search(t[t.length - 1]);
    if (b < 0) {
      list[j] = t[t.length - 1];
      m = j + 1;
      return;
    } else {
      m = j;
      return;
    }
  }

  /**
   * set default mock parameter.（方法说明）
   * @throws Exception if has error(异常说明)
   */ 
  protected int  search(String t) {
    for (int i = 0;i < list.length;i++) {
      if (t.equals(list[i])) {
        return i;
      }                  
    }
    return -1;
  }
  


  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */  
  protected static String listToString(List<String> list) {
    if (list == null) {
      return null;
    }
    StringBuilder result = new StringBuilder();
    boolean first = true;
    for (String string :list) {
      if (first) {
        first = false;
      } else {
        result.append(" ");
      }
      result.append(string);
    }
    return result.toString();
  }
  
  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */  
  public String queryBridgeWords(String word1, String word2) {
	  boolean k1= word1.matches("[a-zA-Z]+");
 	  boolean k2= word1.matches("[a-zA-Z]+");
 	  if(k1!=true ||k2!=true) {
 	    return "error";
 	  }
	     List<String> l = new ArrayList<>();
	      String result = null;
	      int num = 0;
	      int t1 = search(word1);
	      int t2 = search(word2);
	      if (t1 < 0 || t2 < 0) {
	  	    if (t1 < 0 && t2 >= 0) {
	       	   result="noword1";
	  	         
	          } else if (t1 >= 0 && t2 < 0) {
	   	       result ="noword2";
	          } else {
	   	      result="noboth";
	   	    }}
	  	    else if (t1 >= 0 && t2 >= 0) {
	        for (int i = 0;i <= m;i++) {
	          if (edge[t1][i] > 0 && edge[i][t2] > 0) {
	            l.add(list[i]);
	            num++;
	          }
	        }
	        if (num <= 0) {
	          result =  "nobrige";
	        } else if (num >= 1) {
	          result = listToString(l);
	        }
	      }
	      return result;       
	   }
 
  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */  
  public String generateNewText(String inputText) {
    if (inputText != null) {
      List<String> l = new ArrayList<>();
      String[] text = inputText.toLowerCase().split(" ");
      for (int i = 0;i < text.length - 1;i++) {
        l.add(text[i]);
        String z=queryBridgeWords(text[i],text[i + 1]);
        if ( (z!="nobrige") && (z!="error")&&(z!="noword1")&&(z!="noword2")&&(z!="noboth")) {
          String[] t = queryBridgeWords(text[i],text[i + 1]).split(" ");
          l.add(t[(int) (Math.random() * (t.length))]);
        }
      }
      l.add(text[text.length - 1]);
      String str = listToString(l);
      return str;
    }
    return null;
  }

    
  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */   
  public String calcShortestPath(String word1, String word2) throws IOException {        
    String result = new String();
    path = new int[m][m];
    dist = new int[m][m];
    floyd();   
    int t1 = search(word1);
    int t2 = search(word2);
    if (t1 == t2) {
      result="error";
      return result;
    }
    if (t1 >= 0 && t2 >= 0) {  
      findPath(t1,t2);
      if (edge[t1][t2] <= 0 && re.isEmpty()) {
        result="不可达！";
        re.clear();
        return result ;
      } else {
        re.add(0, word1); 
      }  
      for (int i = 0;i < re.size();i++) {
        result += re.get(i)+" ";
      }
      result +=  word2;
      result+= " "+dist[t1][t2];
      re.clear();
      return result;
    } else if (t1 < 0 && t2 >= 0) {
      result="noword1";
      return result;
    } else if (t1 >= 0 && t2 < 0) {
     result="noword2";
     return result;
    } else {
      result ="noboth";
      return result;
    }
  }
    
  protected  void floyd() {   
    int inf = Integer.MAX_VALUE;
    for (int i = 0;i < m;i++) {   
      for (int j = 0;j < m;j++) {   
        path[i][j] = -1;   
        if (edge[i][j] < 0) {
          dist[i][j] = inf;
        } else {
          dist[i][j] = edge[i][j]; 
        }
      }   
    }         
    for (int k = 0;k < m;k++) {
      for (int i = 0;i < m;i++) {   
        for (int j = 0;j < m;j++) {   
          if (dist[i][k] != inf && dist[k][j] != inf && dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];   
            path[i][j] = k;   
          }   
        }   
      }   
    }   
  }   

  protected void findPath(int i,int j) {
    int k = path[i][j];   
    if (k == -1) {
      return ;   
    }
    findPath(i,k);  
    re.add(list[k]);   
    findPath(k,j);            
  }
      
  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */
  public String randomWalk() {
    int[][] p = new int[m][m];
    StringBuffer buf = new StringBuffer();
    for (int i = 0;i < m;i++) {
      for (int j = 0;j < m;j++) {
        p[i][j] = -1;
      }
    }
    int a = (int)(Math.random() * m);
    buf.append(list[a]);
    while (ran(a) != -1) {
      int b = ran(a);
      if (p[a][b] == 1) {
        break;
      } else {
        buf.append(" ");
        buf.append(list[b]);
        p[a][b] = 1;
        a = b;
      }     
    }
    String result = buf.toString();
    return result;     
  }
    
  protected int ran(int x) {
    int[] l = new int[m];
    int j = 0;
    for (int i = 0;i < m;i++) {
      if (edge[x][i] > 0) {
        l[j] = i;
        j++;
      }
    }
    if (j > 1) {
      return l[(int)(Math.random() * j)];
    } else if (j == 1) {
      return l[0];
    } else {
      return -1;
    }
  }
}


