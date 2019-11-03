import clinica.classes.Endereco;
import clinica.classes.Paciente;
import clinica.classes.Medico;
import clinica.classes.Atendente;
import clinica.classes.Consulta;
import clinica.dao.PacienteDao;
import clinica.dao.MedicoDao;
import clinica.dao.AtendenteDao;
import clinica.dao.ConsultaDao;
import clinica.dao.ConnectionFactory;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

public class ClinicaTest {
	private ArrayList<Integer> pacienteIds = new ArrayList();
	private ArrayList<Integer> medicoIds = new ArrayList();
	private ArrayList<Integer> atendenteIds = new ArrayList();
	private ArrayList<Integer> consultaIds = new ArrayList();

	private PacienteDao pacienteDao;
	private MedicoDao medicoDao;
	private AtendenteDao atendenteDao;
	private ConsultaDao consultaDao;

	@Test
	public void doTest() {
		ConnectionFactory.setDbName("clinicatest");

		pacienteDao = new PacienteDao();
		medicoDao = new MedicoDao();
		atendenteDao = new AtendenteDao();
		consultaDao = new ConsultaDao();

		testInsertMedico("Alberto", "111", "123", "Cardiologia");
		testInsertMedico("Ricardo", "222", "234", "Oftalmologia");

		Endereco endereco = new Endereco("Rua A", "1", "", "Porto Alegre");
		testInsertPaciente("Eduardo", "333", endereco, "1111", "2222");

		endereco = new Endereco("Rua B", "2", "Apto. 5", "Canoas");
		testInsertPaciente("Roberto", "444", endereco, "3333", null);

		testInsertAtendente("Antônio", "555", false);
		testInsertAtendente("João", "777", true);

		Paciente p1 = pacienteDao.findByID(pacienteIds.get(0));
		assertNotNull(p1);
		Paciente p2 = pacienteDao.findByID(pacienteIds.get(1));
		assertNotNull(p2);

		Medico m1 = medicoDao.findByID(medicoIds.get(0));
		assertNotNull(m1);
		Medico m2 = medicoDao.findByID(medicoIds.get(1));
		assertNotNull(m2);

		testInsertConsulta(p1, m2, new Date(2019, 10, 31, 16, 0, 0));

		cleanupDb();
	}

	//----------------------------------------------------------------------------

	private void testInsertPaciente(String nome, String cpf, Endereco endereco, String tel1, String tel2) {
		Paciente c = new Paciente(null, nome, cpf, endereco, tel1, tel2);
		int id = pacienteDao.insert(c);
		assertFalse("Impossível inserir o paciente", id == -1);

		Paciente r = pacienteDao.findByID(id);
		assertNotNull(r);

		pacienteIds.add(new Integer(id));

		assertEquals((Object)id, (Object)r.getId());
		assertEquals(nome, r.getNome());
		assertEquals(cpf, r.getCpf());
		assertEquals(tel1, r.getTelefone1());
		assertEquals(tel2, r.getTelefone2());

		Endereco re = r.getEndereco();
		assertEquals(endereco.getLogradouro(), re.getLogradouro());
		assertEquals(endereco.getNumero(), re.getNumero());
		assertEquals(endereco.getComplemento(), re.getComplemento());
		assertEquals(endereco.getMunicipio(), re.getMunicipio());
	}

	private void testInsertMedico(String nome, String cpf, String registro, String especialidade) {
		Medico c = new Medico(null, nome, cpf, registro, especialidade);
		int id = medicoDao.insert(c);
		assertFalse("Impossível inserir o médico", id == -1);

		Medico r = medicoDao.findByID(id);
		assertNotNull(r);

		medicoIds.add(new Integer(id));

		assertEquals((Object)id, (Object)r.getId());
		assertEquals(nome, r.getNome());
		assertEquals(cpf, r.getCpf());
		assertEquals(registro, r.getRegistro());
		assertEquals(especialidade, r.getEspecialidade());
	}

	private void testInsertAtendente(String nome, String cpf, boolean admin) {
		Atendente c = new Atendente(null, nome, cpf, admin);
		int id = atendenteDao.insert(c);
		assertFalse("Impossível inserir o atendente", id == -1);

		Atendente r = atendenteDao.findByID(id);
		assertNotNull(r);

		atendenteIds.add(new Integer(id));

		assertEquals((Object)id, (Object)r.getId());
		assertEquals(nome, r.getNome());
		assertEquals(cpf, r.getCpf());
		assertEquals(admin, r.isAdmin());
	}

	private void testInsertConsulta(Paciente paciente, Medico medico, Date dataHora) {
		Consulta c = new Consulta(null, paciente, medico, dataHora.getTime());
		int id = consultaDao.insert(c);
		assertFalse("Impossível inserir a consulta", id == -1);

		Consulta r = consultaDao.findByID(id);
		assertNotNull(r);

		consultaIds.add(new Integer(id));

		assertEquals((Object)id, (Object)r.getId());
		assertEquals(r.getPaciente().getId(), paciente.getId());
		assertEquals(r.getPaciente().getCpf(), paciente.getCpf());
		assertEquals(r.getMedico().getId(), medico.getId());
		assertEquals(r.getMedico().getCpf(), medico.getCpf());
		assertTrue(r.getDataHora().equals(dataHora));
	}

	private void cleanupDb() {
		for (Integer id : pacienteIds) {
			pacienteDao.deleteById(id.intValue());
		}

		for (Integer id : medicoIds) {
			medicoDao.deleteById(id.intValue());
		}

		for (Integer id : atendenteIds) {
			atendenteDao.deleteById(id.intValue());
		}

		for (Integer id : consultaIds) {
			consultaDao.deleteById(id.intValue());
		}
	}
}
