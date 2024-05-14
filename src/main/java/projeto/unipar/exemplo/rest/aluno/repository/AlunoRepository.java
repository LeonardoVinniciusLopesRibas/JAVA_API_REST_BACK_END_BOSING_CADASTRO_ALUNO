package projeto.unipar.exemplo.rest.aluno.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import projeto.unipar.exemplo.rest.aluno.exception.ObjectNotFoundException;
import projeto.unipar.exemplo.rest.aluno.exception.ValidacaoException;
import projeto.unipar.exemplo.rest.aluno.infrastructure.ConnectionFactory;
import projeto.unipar.exemplo.rest.aluno.model.Aluno;

public class AlunoRepository {
    
    private static final String INSERT = "INSERT INTO ALUNO (NOME, CPF, EMAIL) VALUES (?, ?, ?)";
    private static final String FIND_ALL = "SELECT ID, NOME, CPF, EMAIL FROM ALUNO";
    private static final String FIND_BY_ID = "SELECT ID, NOME, CPF, EMAIL FROM ALUNO WHERE ID = ?";
    private static final String UPDATE = "UPDATE ALUNO SET NOME = ?, CPF = ?, EMAIL = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM ALUNO WHERE ID = ?";
    
    public Aluno insert(Aluno aluno) throws SQLException, NamingException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getCpf());
            pstmt.setString(3, aluno.getEmail());
            
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            
            rs.next();
            
            aluno.setId(rs.getInt(1));
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return aluno;
    }
    
    public List<Aluno> findAll() throws SQLException, NamingException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<Aluno> resultado = new ArrayList<>();
        
        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                resultado.add(
                        new Aluno(
                                rs.getInt("ID"),
                                rs.getString("NOME"),
                                rs.getString("CPF"),
                                rs.getString("EMAIL"))
                );
                
            }
            
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }
    
    public List<Aluno> findAllGeral() throws SQLException, ValidacaoException, NamingException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<Aluno> resultado = new ArrayList<>();
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                resultado.add(new Aluno(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("EMAIL"))
                );
                
            }
            
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return resultado;
    }
    
    public Aluno findById(int id) throws SQLException, ValidacaoException, NamingException, ObjectNotFoundException, Exception {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Aluno aluno = new Aluno();
        
        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                aluno = new Aluno(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("EMAIL"));
            }
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return aluno;
    }
    
    public Aluno update(Aluno aluno) throws SQLException, NamingException, Exception, ValidacaoException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getCpf());
            pstmt.setString(3, aluno.getEmail());
            pstmt.setInt(4, aluno.getId());
            
            pstmt.executeUpdate();
            
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return aluno;
        
    }
    
    public Aluno delete(int id) throws SQLException, NamingException, ValidacaoException, Exception {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        Aluno alunoDeletado = new Aluno();
        
        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(DELETE);
            pstmt.setInt(1, id);
            alunoDeletado = findById(id);
            pstmt.executeUpdate();
            
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return alunoDeletado;
    }
    
}
