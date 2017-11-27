package lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphTest1 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
    Controller C =new  Controller();

@Test
public void testGenerateNewText2() {
String inputText="it just means you value your relationship more than your ego";
String res="it just means you value your relationship more than your ego";
String result=C.text(inputText);
assertEquals(res,result);
}
@Test
public void testGenerateNewText1() {
String inputText=null;
String res=null;
String result=C.text(inputText);
assertEquals(res,result);
}
@Test
public void testGenerateNewText3() {
String inputText="Apologizing does not always mean you wrong";
String res="apologizing does not always mean you are wrong";
String result=C.text(inputText);
assertEquals(res,result);
}

}
