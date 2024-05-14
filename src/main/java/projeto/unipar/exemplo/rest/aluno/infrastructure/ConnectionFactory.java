
package projeto.unipar.exemplo.rest.aluno.infrastructure;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author andersonbosing
 */
public class ConnectionFactory {
    
    //Constantes
    private static final String RESOURCE_NAME = "exemplorestalunoresource";
    
    private DataSource getDatasource() throws NamingException {
        
        Context c = new InitialContext();
        
        return (DataSource) c.lookup(RESOURCE_NAME);
        
    }
    
    public Connection getConnection() throws SQLException, NamingException {
       
        return getDatasource().getConnection();
        
    }
    
}
