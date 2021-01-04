package ma.ensias.ejb3.tp1;


import java.util.List;

import javax.ejb.Remote;

@Remote
public interface SearchBookFacadeRemote {
	List<String> bookSearch(String bookType);
	List<String> searchBookByCountry(String country);
}
