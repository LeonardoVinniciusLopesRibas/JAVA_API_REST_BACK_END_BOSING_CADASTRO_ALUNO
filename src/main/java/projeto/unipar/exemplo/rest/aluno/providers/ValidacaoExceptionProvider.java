package projeto.unipar.exemplo.rest.aluno.providers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Date;
import projeto.unipar.exemplo.rest.aluno.exception.ExceptionResponse;
import projeto.unipar.exemplo.rest.aluno.exception.ValidacaoException;

@Provider
public class ValidacaoExceptionProvider implements ExceptionMapper<ValidacaoException> {

    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(ValidacaoException e) {

        ExceptionResponse response = new ExceptionResponse(e.getMessage(), new Date(), request.getRequestURI(), Response.Status.BAD_REQUEST.toString());
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();

    }

}
