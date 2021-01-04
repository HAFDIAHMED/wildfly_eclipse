package ma.ensias.ejb3.tp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Session Bean implementation class SearchBookFacade
 */
@Stateless
@LocalBean
public class SearchBookFacade implements SearchBookFacadeRemote, SearchBookFacadeLocal {

    /**
     * Default constructor. 
     */
    public SearchBookFacade() {
        // TODO Auto-generated constructor stub
    }
    HashMap<String, List<String>> countryBookMap = new HashMap<String, List<String>>();
    public List<String> bookSearch(String bookType) {
    	List<String> bookList = new ArrayList<String>();
    	if (bookType.equals("java")) {
    		bookList.add("Java for dummies");
    		bookList.add("Beginnig Java 6");
    	}else if (bookType.equals("C++")) {
    		bookList.add("C++ for dummies");
    	}
    	return bookList;
    }
    public List<String> searchBookByCountry(String country) {
    	return countryBookMap.get(country);
    }
    @PostConstruct
    public void initializeCountryBookList () {
    	ArrayList<String> Book = new ArrayList<String>();
    	Book.add("Welcome to Australia");
    	Book.add("Australia History");
    	countryBookMap.put("Australia",(List<String>) Book.clone());
    	Book.clear();
    	Book.add("Welcome to Morocco");
    	Book.add("Morocco History");
    	countryBookMap.put("Morocco",(List<String>) Book.clone());
    	Book.clear();
    }
    
    @PreDestroy
    public void destroyBookList () {
    	countryBookMap.clear();
    }

    @AroundInvoke
    public Object timerLog(InvocationContext ctx) throws Exception {
    	String beanClassName = ctx.getClass().getName();
    	String businessMethodName = ctx.getMethod().getName();
    	String target = beanClassName + "." + businessMethodName;
    	long startTime = System.currentTimeMillis();
    	System.out.println("Invoking " + target);
    	try {
    		return ctx .proceed();
    	} finally {
    		System.out.println("Exiting " + target);
    		long totalTime = System.currentTimeMillis() - startTime;
    		System.out.println("Business method {" +businessMethodName + "} in " + beanClassName + " takes "+totalTime +"ms to execute");
    	}
    }
  
}
