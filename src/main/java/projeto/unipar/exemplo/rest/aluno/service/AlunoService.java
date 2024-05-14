package projeto.unipar.exemplo.rest.aluno.service;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import projeto.unipar.exemplo.rest.aluno.exception.ObjectNotFoundException;
import projeto.unipar.exemplo.rest.aluno.exception.ValidacaoException;
import projeto.unipar.exemplo.rest.aluno.model.Aluno;
import projeto.unipar.exemplo.rest.aluno.repository.AlunoRepository;

public class AlunoService {

    public Aluno insert(Aluno aluno) throws ValidacaoException, SQLException, NamingException {

        validateInsertUpdate(aluno);

        return new AlunoRepository().insert(aluno);

    }

    public List<Aluno> findAll() throws ValidacaoException, SQLException, NamingException {
        return new AlunoRepository().findAll();

    }
    
    public List<Aluno> findAllGeral() throws ValidacaoException, SQLException, NamingException{
        return new AlunoRepository().findAllGeral();
    }

    public Aluno findById(int id) throws SQLException, ValidacaoException, NamingException, Exception, ObjectNotFoundException {

        if (id == 0) {
            throw new ValidacaoException("Informe o código para pesquisar");
        }
        Aluno aluno = new AlunoRepository().findById(id);
        if (aluno.getId() == 00 && aluno.getNome() == null) {
            throw new ObjectNotFoundException("Recurso não encontrado");
        }
        return aluno;
    }

    public Aluno update(Aluno aluno, int id) throws ValidacaoException, SQLException, NamingException, Exception {

        if (id <= 0) {
            throw new ValidacaoException("ID do aluno inválido");
        }

        aluno.setId(id); 

        validateInsertUpdate(aluno);

        return new AlunoRepository().update(aluno);

    }
    
    public Aluno delete(int id) throws ValidacaoException, SQLException, NamingException, Exception{
        
        if(id <= 0){
            throw new ValidacaoException("ID do Aluno inválido");
        }
        Aluno aluno = new AlunoRepository().delete(id);
        if (aluno.getId() == 00 && aluno.getNome() == null) {
            throw new ObjectNotFoundException("Recurso não encontrado");
        }
        return aluno;
        
        
    }

    public void validateInsertUpdate(Aluno aluno) throws ValidacaoException {

        if (aluno.getNome().trim().length() > 100) {
            throw new ValidacaoException("NÚMERO MÁXIMO DE 100 CARACTERES ATINGIDO");
        }

        if (aluno.getNome().isEmpty()) {
            throw new ValidacaoException("NOME DO ALUNO É OBRIGATÓRIO");
        }

        if (aluno.getCpf().trim().length() != 11) {
            throw new ValidacaoException("QUANTIDADE DE CARACTERES INVÁLIDO");
        }

        if (aluno.getCpf().isEmpty()) {
            throw new ValidacaoException("CPF DO ALUNO É OBRIGATÓRIO");
        }

    }

    

}
