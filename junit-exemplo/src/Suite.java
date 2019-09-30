import junit.framework.TestSuite;

public class Suite {
	@org.junit.Test
	public static TestSuite suite() {
		TestSuite s = new TestSuite();
		s.addTestSuite(Test.class);
		
		return s;
	}
}
