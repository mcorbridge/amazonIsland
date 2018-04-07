

/**
 * Created by Mike on 4/5/2018.
 */
public class Cell {


	Cell(String value, int[] coord){
		this.value = value;
		this.coord = coord;
	}


	public String value;

	public int islandNum;

	public Boolean isIsland = Boolean.FALSE;

	public int[] coord;
}
