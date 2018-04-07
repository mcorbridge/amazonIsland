package src.com.mcorbridge.findisland;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Mike on 3/31/2018.
 * copyright Michael D. Corbridge
 */

public class LandItem {

    public int numIsland = 0;

    public Vector<int[]> associations = new Vector<>();

    public Vector<int[]> adjacencies = new Vector<>();

    public int[] landCell = new int[2];

	public Boolean isCollision;

    public void setAdjacent(int[] cell, int[] dim){

        landCell[0] = cell[0];
        landCell[1] = cell[1];

        int matrixRows = dim[0];
        int matrixCols = dim[1];

        int[] top = {cell[0]-1, cell[1]};
        int[] bottom = {cell[0]+1, cell[1]};
        int[] left = {cell[0], cell[1]-1};
        int[] right = {cell[0], cell[1]+1};

        if(top[0] < 0){
            top = null;
        }else{
            adjacencies.add(top);
        }
        if(bottom[0] > matrixRows-1){
            bottom = null;
        }else{
            adjacencies.add(bottom);
        }
        if(left[1] < 0){
            left = null;
        }else{
            adjacencies.add(left);
        }
        if(right[1] > matrixCols-1){
            right = null;
        }else{
            adjacencies.add(right);
        }

    }

}
