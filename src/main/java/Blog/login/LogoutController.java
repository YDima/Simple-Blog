package Blog.login;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


@Named
@RequestScoped
public class LogoutController {

    @Inject
    private HttpServletRequest request;


    public String logout() {
        var session = request.getSession(false);
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }
}
