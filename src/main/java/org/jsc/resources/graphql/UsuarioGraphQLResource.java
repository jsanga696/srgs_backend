import org.eclipse.microprofile.graphql.Query;
import org.jsc.entities.Usuario;
import org.services.UsuarioService;
import org.eclipse.microprofile.graphql.Mutation;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioGraphQLResource {

    @Inject
    UsuarioService service;

    @Query
    public Usuario usuario(Long id) {
        return service.buscarPorId(id);
    }

    /*@Query
    public List<Usuario> usuarios() {
        return service.listar(0,0);
    }*/

    @Mutation
    public Usuario crearUsuario(Usuario input) {
        return service.crear(input);
    }
}