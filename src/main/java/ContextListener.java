import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextListener implements ServletContextListener {

	
	public void contextInitialized(ServletContextEvent sce) {
		try {
			// Gambiarra para nao setar 0 quando o valor for null para os tipos Long, Integer, Double
			System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
