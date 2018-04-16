

import java.util.ArrayList;

/**
 * Created by Mike on 4/6/2018.
 */
public class Island {

	public ArrayList<int[]> cellCoord = new ArrayList<>();
	public int islandNum;


	Island(int islandNum){
		this.islandNum = islandNum;
	}

	public void addCoord(Cell cell){
		for(Cell cc:cell.contiguous){
			cellCoord.add(cc.coord);
		}
	}



}
