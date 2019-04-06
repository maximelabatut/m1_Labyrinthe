package mesActions;

import com.opensymphony.xwork2.ActionSupport;
import modele.GestionLabyrinthe;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import rmiService.MonServiceImpl;

import java.util.Map;

public class Environnement extends ActionSupport implements ApplicationAware, SessionAware {

    private Map<String, Object> session;
    MonServiceImpl facadeRMI;

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    public MonServiceImpl getFacadeRMI() {
        return facadeRMI;
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        facadeRMI = (MonServiceImpl) map.get("facade");
        if (facadeRMI == null ) {
            facadeRMI = new MonServiceImpl();
            map.put("facadeRMI",facadeRMI);
        }
    }

    public String getPseudo() {
        return (String)getSession().get("login");
    }

    public void setLogin(String login) {
        session.put("login", login);
    }

    public void remLogin(String login) {
        session.remove("login");
    }
}
