
package Modelo.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;
import java.sql.SQLException;
import java.util.List;
import modelo.Contato;
import modelo.dao.ContatoDAO;
import modelo.dao.DAOFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Moon_
 */
public class ContatoDAOTest {
    
    public ContatoDAOTest() {
    }
    
    private static final DbSetupTracker dbSetupTracker = new DbSetupTracker();
    
    private DAOFactory factory;
    private ContatoDAO dao;

    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException{
        //"INSERT INTO livro(nome,autor,nroPaginas,preco) "
        //+ "VALUES('TESTE', 'TESTE', 2, 2.0)"
        factory = new DAOFactory();
        factory.abrirConexao();
        dao = factory.criarContatoDAO();
        
        Operation operation = sequenceOf(
                insertInto("Contato")
                .columns("Nome", "Endereco", "Telefone", "Email", "Fundos")
                .values("TESTE", "TESTE","123TESTE", "TESTE", 200)
                .build());
                    
                    
   //     Operation operation
   //             = sequenceOf(OperacoesComunsBD.LIMPA_TUDO,
   //                          OperacoesComunsBD.RESET_AUTOINCREMENT_MYSQL,
   //                         OperacoesComunsBD.RESET_AUTOINCREMENT_H2,
   //                           OperacoesComunsBD.INSERE_DADOS_BASICOS);
   // MySQL
        DbSetup dbSetup = new DbSetup(new DriverManagerDestination(
                "jdbc:mysql://localhost:3306/mydb",
                "root", "12345"), operation);

//        //H2 em arquivo
//        ConexaoFactory.getConexaoH2Arquivo();  // para criar o BD caso ele nao exista
//        DbSetup dbSetup = new DbSetup(new DriverManagerDestination(
//                "jdbc:h2:./contatossimples;DATABASE_TO_UPPER=false;",
//                "root", "12345"), operation);
        
//        //H2 em memoria
//           DbSetup dbSetup = new DbSetup(new DriverManagerDestination(
//                "jdbc:mysql://localhost:3306/mydb;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1;"
//                + "INIT=CREATE TABLE IF NOT EXISTS contato (idContatos int(11) "
//                + "PRIMARY KEY AUTO_INCREMENT, Nome varchar(255), "
//                + "Endereco varchar(255), Telefone varchar(45), Email "
//                + "varchar(255), Fundos decimal(10,0))\\;",
//                         "root", "12345"), operation);
        
        dbSetupTracker.launchIfNecessary(dbSetup);

       

        
    }
    
    @After
    public void tearDown() throws SQLException {
        factory.fecharConexao();
    }

    /**
     * Test of gravar method, of class ContatoDAO.
     */
    @Test
    public void testGravar() throws Exception {
        Contato teste = mock(Contato.class);
        teste.setIdContato(1);
        teste.setNome("teste");
        teste.setEndereco("teste");
        teste.setEmail("teste");
        teste.setTelefone("123teste");
        teste.setFundos(200);
        dao.gravar(teste);
        List<Contato> list = new ArrayList<>();
        list = dao.buscarTodos();
        assertTrue(list.size() >= 2);
    }

    /**
     * Test of buscar method, of class ContatoDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        Contato resultBuscar = dao.buscar(2);
        
        assertTrue(resultBuscar.getNome().equals("1"));
        assertTrue(String.valueOf(resultBuscar.getIdContato()).equals("2"));
      

    }

//    /**
//     * Test of buscarTodos method, of class ContatoDAO.
//     */
//    @Test
//    public void testBuscarTodos() throws Exception {
//        
//    }
//
//    /**
//     * Test of remover method, of class ContatoDAO.
//     */
//    @Test
//    public void testRemover() throws Exception {
//        
//    }

    /**
     * Test of atualizar method, of class ContatoDAO.
     */
    @Test
    public void testAtualizar() throws Exception {
        Contato teste = mock(Contato.class);
        teste.setIdContato(1);
        teste.setNome("teste");
        teste.setEndereco("teste");
        teste.setEmail("teste");
        teste.setTelefone("123teste");
        teste.setFundos(200);
        dao.gravar(teste);
        List<Contato> list = new ArrayList<>();
        list = dao.buscarTodos();
        Contato update = list.get(0);
        update.setNome("novoteste");
        dao.atualizar(update);
        Contato result = dao.buscar(1);
        assertTrue(result.getNome().equals("novoteste"));
    }
    
}
