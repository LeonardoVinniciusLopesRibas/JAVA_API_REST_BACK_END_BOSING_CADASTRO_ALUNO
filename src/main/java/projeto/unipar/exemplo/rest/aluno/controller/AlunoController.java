package projeto.unipar.exemplo.rest.aluno.controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.naming.NamingException;
import projeto.unipar.exemplo.rest.aluno.dto.AlunoFindAllResponse;
import projeto.unipar.exemplo.rest.aluno.dto.AlunoRequest;
import projeto.unipar.exemplo.rest.aluno.exception.ExceptionResponse;
import projeto.unipar.exemplo.rest.aluno.exception.ObjectNotFoundException;
import projeto.unipar.exemplo.rest.aluno.exception.ValidacaoException;
import projeto.unipar.exemplo.rest.aluno.model.Aluno;
import projeto.unipar.exemplo.rest.aluno.service.AlunoService;

@WebFilter("/*")
@Path("aluno")
@Produces(MediaType.APPLICATION_JSON)
public class AlunoController implements Filter{

    private static final Logger LOGGER = Logger.getLogger(AlunoController.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(AlunoRequest aluno, @Context HttpServletRequest request) throws NamingException, ValidacaoException, SQLException, Exception {
        
            AlunoService alunoService = new AlunoService();
            Aluno alunoDominio = alunoService.insert(Aluno.requestToAluno(aluno));
            return Response.created(URI.create(request.getRequestURI() + "/" + alunoDominio.getId())).build();

    }

    @GET
    public Response findAll(@Context HttpServletRequest request) throws NamingException, ValidacaoException, SQLException, Exception {

            AlunoService alunoService = new AlunoService();
            List<Aluno> alunos = alunoService.findAll();
            List<AlunoFindAllResponse> response = alunos.stream().map(aluno -> new AlunoFindAllResponse(aluno.getId(), aluno.getNome())).collect(Collectors.toList());
            return Response.ok(response).build();

    }
    
    @GET
    @Path("geral")
    public Response findAllGeral(@Context HttpServletRequest request) throws NamingException, ValidacaoException, SQLException, Exception {
     
            AlunoService alunoService = new AlunoService();
            List<Aluno> aluno = alunoService.findAllGeral();
            return Response.ok(aluno).build();

    }
    
    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") int id, @Context HttpServletRequest request) throws NamingException, ValidacaoException, SQLException, Exception {
    
            AlunoService alunoService = new AlunoService();
            Aluno aluno = alunoService.findById(id);
            return Response.ok(aluno).build();
        
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(AlunoRequest aluno, @PathParam("id") int id, @Context HttpServletRequest request) throws NamingException, ValidacaoException, SQLException, Exception {

            AlunoService alunoService = new AlunoService();
            Aluno alunoDominio = alunoService.update(Aluno.requestToAluno(aluno), id);
            return Response.created(URI.create(request.getRequestURI() + "/" + alunoDominio.getId())).build();
            
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id, @Context HttpServletRequest request) throws NamingException, ValidacaoException, SQLException, Exception {

            AlunoService alunoService = new AlunoService();
            Aluno aluno = alunoService.delete(id);
            return Response.ok(aluno).build();
            
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
    
}
