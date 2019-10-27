import clinica.classes.Medico;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClinicaTest {
	@Test
	public void doTest() {
		Medico medico = new Medico();
		medico.setNome("Roberto");
		medico.setCpf("1234");

		assertEquals(medico.getNome(), "Roberto");
		assertEquals(medico.getCpf(), "1234");
	}
}
