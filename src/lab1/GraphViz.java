package lab1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
    
public class GraphViz {
  public static String tempDIR = "D:/temp"; // Windows
  private static String dotX = "D:\\Graphviz\\bin\\dot.exe"; // Windows
  private StringBuilder graph = new StringBuilder();    //graph
  
  public String getDotSource() {
    return graph.toString();
  }
  
  public void add(String line) {
    graph.append(line);
  }
  
  /**
  * @author 
  */
  public void addln(String line) {
    graph.append(line + "\n");
  }
  
  public void addln() {
    graph.append('\n');
  }
  
  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */  
  public byte[] getGraph(final String source, final String type) throws IOException {
    File dot;
    byte[] stream = null;    
    byte[] temp = null;
    try {
      dot = writeDotSourceToFile(source);
      if (dot != null) {
        stream = getImgStream(dot, type);
        if (dot.delete() == false) {
          System.err.println("Warning: " + dot.getAbsolutePath() + " could not be deleted!");
          
        }
        return stream;
      }
      return temp;
    } catch (java.io.IOException ioe) {
      return null; 
    }
  }
  
  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */ 
  public int writeGraphToFile(final byte[] img, final String file) throws IOException {
    final File input = new File(file);
    return writeGraphToFile(img, input);
  }

  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */ 
  public int writeGraphToFile(final byte[] img, final File input) throws IOException {
    int result = 1;
    try {
      final FileOutputStream fos = new FileOutputStream(input);
      fos.write(img);
      fos.close();
    } catch (java.io.IOException ioe) { 
      ioe.printStackTrace();
      result = -1;
    }
    return result;
  }

  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */ 
  private byte[] getImgStream(final File dot, final String type) throws IOException {
    File img;
    byte[] imgStream = null;
    try {
      img = File.createTempFile("graph_", "." + type, new File(GraphViz.tempDIR));
      final Runtime runt = Runtime.getRuntime();             
      final String[] args = {dotX, "-T" + type, dot.getAbsolutePath(), "-o", img.getAbsolutePath()};
      final Process p = runt.exec(args);  
      p.waitFor();
      final FileInputStream in = new FileInputStream(img.getAbsolutePath());
      imgStream = new byte[in.available()];
      in.read(imgStream);
      if (in != null) {
        in.close();
      }
      if (img.delete() == false) {
        System.err.println("Warning: " + img.getAbsolutePath() + " could not be deleted!");
      }
    } catch (java.io.IOException ioe) {
      System.err.println("Error:    in I/O processing of tempfile in dir "  
             + GraphViz.tempDIR + "\n");
      System.err.println("       or in calling external command");
      ioe.printStackTrace();
    } catch (java.lang.InterruptedException ie) {
      System.err.println("Error: the execution of the external program was interrupted");
      ie.printStackTrace();
    }
    return imgStream;   
  }
  
  /**
   * set default mock parameter.（方法说明）
   * @return data manager(返回值说明)
   * @throws Exception if has error(异常说明)
   */ 
  public File writeDotSourceToFile(String str) throws java.io.IOException {
    File temp;
    try {
      temp = File.createTempFile("graph_", ".dot.tmp", new File(GraphViz.tempDIR));
      final FileWriter fout = new FileWriter(temp);
      fout.write(str);
      fout.close();
    } catch (Exception e) {
      System.err.println("Error: I/O error while writing the dot source to temp file!");
      return null;
    }
    return temp;
  }
  
  public String startGraph() {
    return "digraph G {" ;
  }
       
  public String endGraph() {
    return "}";
  }
  
  /**
   * set default mock parameter.（方法说明）
   * @SuppressWarnings("unused")
   * @throws Exception if has error(异常说明)
   */  
  /*
  private void readSource(final String input) throws java.io.IOException {
    final StringBuilder strBuild = new StringBuilder();        
    try {
      final FileInputStream fis = new FileInputStream(input);
      final DataInputStream dis = new DataInputStream(fis);
      final BufferedReader buffRead = new BufferedReader(new InputStreamReader(dis));
      String line;
      while ((line = buffRead.readLine()) != null) {
          strBuild.append(line);
      }
      dis.close();
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
    this.graph = strBuild;
  }      */
} // end of class GraphViz
