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
import java.util.Scanner;
import java.util.logging.Logger;

public class Io {
  static Logger log = Logger.getLogger(Io.class.getName());

  /**
    * set default mock parameter.������˵����
    * @return data manager(����ֵ˵��)
    * @throws Exception if has error(�쳣˵��)
    */
  public static String[] readFileByChars(String fileName) throws IOException {
    File file = new File(fileName);  
    Reader reader = null;  
    try {
      reader = new InputStreamReader(new FileInputStream(file));  
      int tempchar;  
      char[][] temp = new char[1000][20];
      int i = 0;
      int j = 0;
      while ((tempchar = reader.read()) != -1) {                   
        if (tempchar >= 'a' && tempchar <= 'z') {
          temp[j][i] = (char) tempchar;
          i++;
        } else if (tempchar >= 'A' && tempchar <= 'Z') {
          tempchar += 32;
          temp[j][i] = (char)tempchar;
          i++;
        } else {    
          j++;
          i = 0;
        } 
      }                    
      reader.close();  
      return change(temp,j);
    } catch (Exception e) {  
      e.printStackTrace();  
    }
    return null;  
  }
  
  /**
   * set default mock parameter.������˵����
   * @return data manager(����ֵ˵��)
   * @throws Exception if has error(�쳣˵��)
   */
  public static String[] change(char[][] x,int n) throws IOException {
    String[] a = new String[n];
    List<String> l = new ArrayList<String>();
    for (int i = 0;i < n;i++) {
      a[i] = String.valueOf(x[i]);
      a[i] = a[i].trim();
    }
    for (int i = 0;i < n;i++) {
      if (!a[i].isEmpty()) {
        l.add(a[i]);
      }
    }   
    String[] b = new String[l.size()];
    l.toArray(b);
    return b;
  }
  
  /**
   * set default mock parameter.������˵����
   * @throws Exception if has error(�쳣˵��)
   */
  public static void writeFile(String filename,String filec) throws IOException {
    try {
      File file = new File(filename);  
      PrintStream ps = new PrintStream(new FileOutputStream(file));  
      ps.println(filec);// ���ļ���д���ַ���                
    } catch (FileNotFoundException e) {  
      // TODO Auto-generated catch block  
      e.printStackTrace();  
    }  
  }
  
  /**
   * set default mock parameter.������˵����
   * @throws Exception if has error(�쳣˵��)
   */
  public static void main(String[] args) throws IOException {         
    Graph chart = new Graph(readFileByChars("dd.txt").length); 
    chart.creat(readFileByChars("dd.txt"));
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.print("ѡ���ܣ�0.�˳� 1.��ӡͼ  2.��ѯ�ŽӴ� 3���������ı� 4.��ѯ���·�� 5���������\n");
      int a = sc.nextInt();
      if (a == 0) {
        break;
      } else if (a == 1) {
        chart.showDirectedGraph();
      } else if (a == 2) {
        System.out.print("��ѯ�ŽӴʣ�\n");
        System.out.print("Word1��");
        String word1 = sc.next();
        System.out.print("Word2��");
        String word2 = sc.next();
        chart.printBridgeWords(word1, word2,chart.queryBridgeWords(word1,word2));
      } else if (a == 3) {
        sc.nextLine();
        System.out.print("�����ı���");
        final String str = sc.nextLine();
        chart.generateNewText(str);
        System.out.print("\n");
      } else if (a == 4) {
    //    System.out.print("��ѯ���·����");
        log.fine("��ѯ���·����");
        System.out.print("Word1��");
        String word3 = sc.next();
        System.out.print("Word2��");
        String word4 = sc.next();
        chart.calcShortestPath(word3, word4);
      } else if (a == 5) {
        System.out.print("������ߣ�");
        writeFile("out.txt",chart.randomWalk()); 
        System.out.print("\n");
      } else {
        System.out.print("��������\n");    
      }
    }       
  }
}