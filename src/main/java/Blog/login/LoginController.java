package Blog.login;









import Blog.auth.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginController {

    @Inject
    private LoginRequest loginRequest;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private HttpServletRequest request;

    public String login() {
        System.out.println("Tried to log in using " + loginRequest.toString());

        if ( logIn(loginRequest.getUsername(), loginRequest.getPassword())) {
            return "/index.xhtml?faces-redirect=true";
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("error-message", "Incorrect username or password");
            return "/login.xhtml?faces-redirect=true";
        }
    }


    public boolean logIn(String username, String password) {
        if (ifUserExists(username, password)) {
            var session = request.getSession(true);
            session.setAttribute("username", username);
            return true;
        }
        return false;
    }

    public boolean ifUserExists(String username, String password) {
        User user = new User(username, password);
        var list = em.createQuery("from User where username = :username and password = :password", User.class)
                .setParameter("username", user.getUsername())
                .setParameter("password", user.getPassword())
                .getResultList();
        if (list.isEmpty())
            return false;
        else
            return true;
    }


//    @Transactional
//    public void B(){
//        var passwordEncoder = new BCryptPasswordEncoder();
//        final String rawPassword = "xGdXi7Qb5EK4";
//
//        System.out.println("hashed password try 1: " + passwordEncoder.encode(rawPassword));
//        final String hashedPassword = passwordEncoder.encode(rawPassword);
//        System.out.println("hashed password try 2: " + hashedPassword);
//
//        System.out.println("Does password match?: " + passwordEncoder.matches(rawPassword, hashedPassword));
//
//        System.out.println();
//    }
}
















