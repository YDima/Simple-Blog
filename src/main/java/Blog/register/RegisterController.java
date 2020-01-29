package Blog.register;




import Blog.auth.User;
import Blog.login.LoginController;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Named
@ApplicationScoped
public class RegisterController {
    @Inject
    private RegisterRequest registerRequest;
    @Inject
    private LoginController loginController;
    @Inject
    private HttpServletRequest request;
    @PersistenceContext
    private EntityManager em;
    @Transactional
    public String register(){
        System.out.println("Tried to log in using " + registerRequest.toString());
        User user = new User(registerRequest.getName(), registerRequest.getSurname(), registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail(), registerRequest.getBirthDate());

        addUser(user);
        return "/index.xhtml?faces-redirect=true";
    }

    public void addUser(User user) {
        if (loginController.ifUserExists(registerRequest.getUsername(), registerRequest.getPassword())) {
            throw new IllegalStateException(String.format("User %s already exists.", user.getUsername()));
        }
        else {
            em.persist(user);
            var session = request.getSession(true);
            session.setAttribute("username", user.getUsername());
        }

    }
}


















































