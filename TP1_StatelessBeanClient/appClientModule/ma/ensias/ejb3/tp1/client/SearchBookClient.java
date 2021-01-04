package ma.ensias.ejb3.tp1.client;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ma.ensias.ejb3.tp1.SearchBookFacadeRemote;


public class SearchBookClient {
	public SearchBookClient() {
	}
	public static void main(String[] args) {
		SearchBookClient searchFacadeTest = new SearchBookClient();
		searchFacadeTest.doTest();
	}
	InitialContext getInitialContext() throws javax.naming.NamingException {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
		p.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		return new InitialContext(p);
	}
	void doTest() {
		try {
			InitialContext ctx = getInitialContext();
			System.out.println("SearchFacade Lookup");
			String appName = "";
			String moduleName = "TP1StatelessBean";
			String beanName = "SearchBookFacade";
			String remoteInterface = "ma.ensias.ejb3.tp1.SearchBookFacadeRemote";
			String name = appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface;
			SearchBookFacadeRemote searchFacadeProxy = (SearchBookFacadeRemote) ctx.lookup("ejb:/TP1StatelessBean/SearchBookFacade!ma.ensias.ejb3.tp1.SearchBookFacadeRemote");
			System.out.println("Searching books");
			List<String> bookList = searchFacadeProxy.bookSearch("java");
			System.out.println("Printing books list");
			for (String book : (List<String>) bookList) {
				System.out.println(" -- " + book);
			}
			List<String> bookMapList = searchFacadeProxy.searchBookByCountry("Morocco");
			System.out.println("Printing Map books list");
			for (String book : (List<String>) bookMapList) {
				System.out.println(" -- " + book);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
