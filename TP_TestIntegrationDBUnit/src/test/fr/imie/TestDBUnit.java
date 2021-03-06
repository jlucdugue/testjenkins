/**
 * 
 */
package fr.imie;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author imie
 *
 */
public class TestDBUnit {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		// 1. obtention du Jeux d'essai à partir d'un fichier XML
		IDataSet data = getDataSet();
		// 2. obtention de la connection à la base de donnée
		IDatabaseConnection connection = getConnection();
		// 3. application du jeux d'essai à la base de donnée
		DatabaseOperation.CLEAN_INSERT.execute(connection, data);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// 4.Close Connection
	}

	@Test
	public void test() throws SQLException, Exception {
		
		TodoDAO todoDAO = new TodoDAO();
		Integer rowCount = todoDAO.count();
		Assert.assertEquals(3, rowCount,0);
	}

	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		FlatXmlDataSet flatXmlDataSet = new FlatXmlDataSetBuilder()
				.build(new FileInputStream("src/test/dataset.xml"));
		return flatXmlDataSet;

	}

	protected IDatabaseConnection getConnection() throws Exception {
		Connection jdbcConnection = DriverManager
				.getConnection("jdbc:postgresql://localhost:5432/test",
						"postgres", "postgres");
		return new DatabaseConnection(jdbcConnection);
	}

}
