package projeto.unipar.exemplo.rest.aluno.providers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Date;
import projeto.unipar.exemplo.rest.aluno.exception.ExceptionResponse;

@Provider
public class ExceptionProvider implements ExceptionMapper<Exception> {

    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(Exception e) {

        ExceptionResponse response = new ExceptionResponse("Erro ao buscar alunos", new Date(), request.getRequestURI(), Response.Status.INTERNAL_SERVER_ERROR.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
    
    }

}
