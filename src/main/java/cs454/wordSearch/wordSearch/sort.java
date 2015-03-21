package cs454.wordSearch.wordSearch;

import java.util.Comparator;

public class sort implements Comparator<Pagerank>
{
	
	public int compare(Pagerank p1, Pagerank p2) {
		if(p1.getTfid() < p2.getTfid()){
            return 1;
        } else {
            return -1;
        }
	}

}
