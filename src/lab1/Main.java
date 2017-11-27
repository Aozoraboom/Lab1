package lab1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
	public static void main(String[] args) throws IOException {
		Controller C =new  Controller();		
		Scanner sc=new Scanner(System.in);
		while (true){
		System.out.print("ѡ���ܣ�0.�˳� 1.��ӡͼ  2.��ѯ�ŽӴ� 3���������ı� 4.��ѯ���·�� 5���������\n");
		int a=sc.nextInt();
		if(a==0)
			break;
		else if(a==1)
		{
			Graph G=C.show();
		    String str = "";
		    GraphViz gr = new GraphViz();
		    for (int i = 0;i < G.getm();i++) {
		      for (int j = 0;j < G.getm();j++) {
		        if (G.getedge()[i][j] > 0) {
		          str = str + G.getlist()[i];
		          str = str + "->";
		          str = str + G.getlist()[j];
		          str = str + "[label=\"";
		          str = str + G.getedge()[i][j];
		          str = str + "\"]";
		          str = str + "\n";
		        }
		      }
		    }
		    gr.addln(gr.start_graph());
		    gr.add(str);
		    gr.addln(gr.end_graph());
		    String type = "jpg";
		    File out = new File("graphFile" + "." + type);
		    gr.writeGraphToFile(gr.getGraph(gr.getDotSource(),type),out);


		    Logger log = Logger.getLogger(Main.class.getName());
		    
			log.fine("�����ɹ���");
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
		System.out.println(C.printBridgeWords(word1, word2));
		}
		else if(a==3)
		{sc.nextLine();
		String str=new String();
		System.out.print("�����ı���");
		str=sc.nextLine();
		System.out.println(C.text(str));
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
	    String r= C.shortpath(word3, word4);
		if(r=="error"){
		  System.out.println("����������ͬ���ʣ�");
		}else if(r=="���ɴ") {
		  System.out.println("���ɴ");
        }else if(r=="noword1") {
          System.out.printf("No ��%s�� in the graph!\n",word3);
		}else if(r=="noword2") {
		  System.out.printf("No ��%s�� in the graph!\n",word4);
		}else if(r=="noboth") {
		  System.out.printf("No ��%s�� or ��%s�� in the graph!%n",word3,word4);
		}else {
			String[] t = r.split(" "); 
			Graph G=C.show();
		    String str = "";
		    GraphViz gr = new GraphViz();
		    String result = new String();
		      for (int i = 0;i < t.length-2;i++) {
		        result += t[i];
		        result += " ";
		        result += "[style=\"filled\",color=\"black\",fillcolor=\"chartreuse\"]";
		        result += ";";
		        result += "\n";
		      }
		      for (int i = 0;i < G.getm();i++) {
		        for (int j = 0;j < G.getm();j++) {
		          if (G.getedge()[i][j] > 0) {
		            str = str + G.getlist()[i];
		            str = str + "->";
		            str = str + G.getlist()[j];
		            str = str + "[label=\"";
		            str = str + G.getedge()[i][j];
		            str = str + "\"]";
		            str = str + "\n";
		          }
		        }
		      }
		      gr.addln(gr.start_graph());
		      gr.add(result);
		      gr.add(str);
		      gr.addln(gr.end_graph());
		      String type = "jpg";
		      File out = new File("graphFile" + "." + type);
		      gr.writeGraphToFile(gr.getGraph(gr.getDotSource(),type),out);
		      System.out.print("��̾��룺" + t[t.length-1] + "%n");
			}
		 
		}
		else if(a==5)
		{
				System.out.print("������ߣ�");
				System.out.println(C.randompath());
		}
		else
		{
			System.out.print("��������\n");
			
		}
	  }
		
    }

}
