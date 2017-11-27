package lab1;

import static org.junit.Assert.*;

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
    Controller C =new  Controller();
	@Test
	public void testQueryBridgeWords1() {
		String result = C.printBridgeWords(" ", " ");
		Assert.assertEquals("有问题", "Input error!", result);
	}
	@Test
	public void testQueryBridgeWords2() {
		String result = C.printBridgeWords("aaa", "you");
		Assert.assertEquals("有问题", "No aaa in the graph!", result);
	}
	@Test
	public void testQueryBridgeWords3() {
		String result = C.printBridgeWords("you", "bbb");
		Assert.assertEquals("有问题", "No bbb in the graph!", result);
	}
	@Test
	public void testQueryBridgeWords4() {
		String result = C.printBridgeWords("&&", "you");
		Assert.assertEquals("有问题", "Input error!", result);
	}
	@Test
	public void testQueryBridgeWords5() {
		String result = C.printBridgeWords("means", "value");
		Assert.assertEquals("有问题", "The bridge words from means to value is: you", result);
	}
	@Test
	public void testQueryBridgeWords6() {
		String result = C.printBridgeWords("you", "wrong");
		Assert.assertEquals("有问题", "The bridge words from you to wrong are:“is” and “are”", result);
	}
	@Test
	public void testQueryBridgeWords7() {
		String result = C.printBridgeWords("the", "is");
		Assert.assertEquals("有问题", "No bridge words from the to is !", result);
	}
	@Test
	public void testQueryBridgeWords8() {
		String result = C.printBridgeWords("person", "right");
		Assert.assertEquals("有问题", "The bridge words from person to right are:“you”,“is” and “just”", result);

	}
	@Test
	public void testQueryBridgeWords9() {
		String result = C.printBridgeWords("aaa", "bbb");
		Assert.assertEquals("有问题", "No aaa and bbb in the graph!", result);

	}
	
}
