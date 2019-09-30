import static org.junit.Assert.*;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Test extends TestCase {

	@org.junit.Test
	public void test() {
		CurrencyConv conv = new CurrencyConv();
		
		assertEquals(4.16, conv.dolarToReal(1.0));
		assertEquals(8.32, conv.dolarToReal(2.0));
		
		assertEquals(1.0, conv.realToDolar(4.16));
		assertEquals(2.0, conv.realToDolar(8.32));
	}
}
