import br.com.caelum.pm73.dao.CriadorDeSessao;
import br.com.caelum.pm73.dao.UsuarioDao;
import br.com.caelum.pm73.dominio.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class UsuarioDaoTest {


    @Test
    public void deveEncontrarPeloNomeEEmailMockado() {
        Session session = new CriadorDeSessao().getSession();
        UsuarioDao usuarioDao = new UsuarioDao(session);

        Usuario novoUsuario = new Usuario("Joao da Silva", "joaodasilva@gmail.com");
        usuarioDao.salvar(novoUsuario);

        Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("Joao da Silva", "joaodasilva@gmail.com");

        Assert.assertEquals("Joao da Silva", usuarioDoBanco.getNome());
        Assert.assertEquals("joaodasilva@gmail.com", usuarioDoBanco.getEmail());

        session.close();
    }


    @Test
    public void naoDeveEncontrarUsuarioNull() {
        Session session = new CriadorDeSessao().getSession();
        UsuarioDao usuarioDao = new UsuarioDao(session);

        Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("Joao da Silva", "joaodasilva@gmail.com");

        Assert.assertNull(usuarioDoBanco);

        session.close();
    }
}
