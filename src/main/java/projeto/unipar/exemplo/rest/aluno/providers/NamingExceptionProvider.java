package projeto.unipar.exemplo.rest.aluno.providers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import java.util.Date;
import javax.naming.NamingException;
import projeto.unipar.exemplo.rest.aluno.exception.ExceptionResponse;

public class NamingExceptionProvider implements ExceptionMapper<NamingException>{
    
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(NamingException e) {

        ExceptionResponse response = new ExceptionResponse("Ops, algo ocorreu de errado, tente novamente mais tarde", new Date(), request.getRequestURI(), Response.Status.INTERNAL_SERVER_ERROR.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
        
    }
    
}
