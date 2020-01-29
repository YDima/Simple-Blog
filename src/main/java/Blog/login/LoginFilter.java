package Blog.login;
import javax.faces.application.ResourceHandler;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*")
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String registerURI = req.getContextPath() + "/register.xhtml";
        String indexURI = req.getContextPath() + "/index.xhtml";
        String loginURI = req.getContextPath() + "/login.xhtml";

        if (isResourceReq(req) || isSiteAllowed(req) || isUserLogged(req)) {
            chain.doFilter(req, res);

        }
        else if(req.getRequestURI().equals(indexURI)) {
            chain.doFilter(req, res);
        }
        else {
            res.sendRedirect(loginURI);
        }
    }

    private boolean isUserLogged(HttpServletRequest req) {
        var session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null)
            return true;
        else
            return false;
    }

    private boolean isSiteAllowed(HttpServletRequest req) {
        return req.getRequestURI().equals(req.getContextPath() + "/login.xhtml") ||
                req.getRequestURI().equals(req.getContextPath() + "/register.xhtml");
    }



    private boolean isResourceReq(HttpServletRequest req) {
        return req.getRequestURI().startsWith(
                req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
    }


//    private boolean isUserAdmin(HttpServletRequest request) {
//        var session = request.getSession(false);
//        if (session.getAttribute("isAdmin").equals(true))
//            return true;
//        else
//            return false;
//    }
}
