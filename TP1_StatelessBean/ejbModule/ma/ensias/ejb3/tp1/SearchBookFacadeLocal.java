package ma.ensias.ejb3.tp1;

import java.util.List;
import javax.ejb.Local;

@Local
public interface SearchBookFacadeLocal {
	List<String> bookSearch(String bookType);
	List<String> searchBookByCountry(String country);
}
