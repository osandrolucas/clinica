import clinica.classes.Medico;
import clinica.dao.MedicoDao;
import clinica.dao.ConnectionFactory;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ClinicaTest {
	@Test
	public void doTest() {
		ConnectionFactory.setDbName("clinicatest");

		testInsertMedico("Alberto", "111", "123", "Cardiologia");
		testInsertMedico("Ricardo", "222", "234", "Oftalmologia");
	}

	private void testInsertMedico(String nome, String cpf, String registro, String especialidade) {
		MedicoDao dao = new MedicoDao();

		Medico c = new Medico(null, nome, cpf, registro, especialidade);
		int id = dao.insert(c);
		assertFalse("Impossível inserir o médico", id == -1);

		Medico r = dao.findByID(id);
		assertNotNull(r);

		assertEquals((Object)id, (Object)r.getId());
		assertEquals(nome, r.getNome());
		assertEquals(cpf, r.getCpf());
		assertEquals(registro, r.getRegistro());
		assertEquals(especialidade, r.getEspecialidade());

		dao.deleteById(id);
	}
}
