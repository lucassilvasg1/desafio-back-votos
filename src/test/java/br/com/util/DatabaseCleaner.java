package br.com.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner
{

   private final Logger logger = LoggerFactory.getLogger(getClass());

   @Autowired
   private DataSource dataSource;

   private Connection connection;

   public void clearTables()
   {
      try (Connection connection = dataSource.getConnection())
      {
         this.connection = connection;

         checkTestDatabase();
         tryToClearTables();
      }
      catch (SQLException e)
      {
         throw new RuntimeException(e);
      }
      finally
      {
         this.connection = null;
      }
   }

   private void checkTestDatabase() throws SQLException
   {
      String catalog = connection.getCatalog();

      if (catalog == null || !catalog.endsWith("test"))
      {
         throw new RuntimeException("Cannot clear database tables because '"
                                    + catalog
                                    + "' is not a test database (suffix 'test' not found).");
      }
   }

   private void tryToClearTables() throws SQLException
   {
      List<String> tableNames = new ArrayList<String>();
      tableNames.add("tbl_voto");
      tableNames.add("tbl_sessao");
      tableNames.add("tbl_pauta");
      clear(tableNames);
   }

   private void clear(List<String> tableNames) throws SQLException
   {
      Statement statement = buildSqlStatement(tableNames);

      logger.debug("Executing SQL");
      statement.executeBatch();
   }

   private Statement buildSqlStatement(List<String> tableNames) throws SQLException
   {
      Statement statement = connection.createStatement();
      addTruncateSatements(tableNames, statement);
      statement.addBatch(sql("SELECT SETVAL('sq_pauta', 1)"));
      statement.addBatch(sql("SELECT SETVAL('sq_sessao', 1)"));
      statement.addBatch(sql("SELECT SETVAL('sq_voto', 1)"));
      return statement;
   }

   private void addTruncateSatements(List<String> tableNames, Statement statement)
   {
      tableNames.forEach(tableName -> {
         try
         {
            statement.addBatch(sql("DELETE FROM " + tableName + " CASCADE"));
         }
         catch (SQLException e)
         {
            throw new RuntimeException(e);
         }
      });
   }

   private String sql(String sql)
   {
      logger.debug("Adding SQL: {}", sql);
      return sql;
   }

}
