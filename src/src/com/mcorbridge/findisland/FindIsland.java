/**
 * Created by Mike on 4/4/2018.
 * copyright Michael D. Corbridge
 */


package src.com.mcorbridge.findisland;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Vector;

public class FindIsland {

	private static ArrayList<Vector> matrixList;
	private static ArrayList<Island> islands;
	private static Vector<Vector<Cell>> MATRIX;
	private static int totalNumberIslands;

	public static void main(String[] args) {
		setMatrix();
		start();
	}

	private static void setMatrix() {
		matrixList = new ArrayList<>();
		matrixList.add(IslandPattern.buildFourXFour());
		matrixList.add(IslandPattern.buildFiveXFive());
		matrixList.add(IslandPattern.buildSixXSix());
		matrixList.add(IslandPattern.buildTenXTen());
		matrixList.add(IslandPattern.buildSimple());
		matrixList.add(IslandPattern.buildSimpleTwo());
		matrixList.add(IslandPattern.buildSimpleThree());
		matrixList.add(IslandPattern.buildSmile());
		matrixList.add(IslandPattern.buildTest());
		matrixList.add(IslandPattern.buildSimpleSquare());
		matrixList.add(IslandPattern.buildTestU());
		matrixList.add(IslandPattern.buildCross());
		matrixList.add(IslandPattern.buildBox());
		matrixList.add(IslandPattern.buildBlock());
		matrixList.add(IslandPattern.buildEmpty());
	}

	private static void start() {
		for (Vector<Vector<String>> matrix: matrixList) {
			islands = new ArrayList<>();
			MATRIX = new Vector<Vector<Cell>>();
			totalNumberIslands = 0;

			buildMATRIX(matrix);
			printMATRIX(MATRIX);
			findIslands(MATRIX);
			inspectIslands(MATRIX);
			resetIslandDetail();
			printMATRIXasIslandNum(MATRIX);
			printTotalNumberIslands();
		}
	}

	private static void printMATRIX(Vector<Vector<Cell>> matrix) {
		for (int i = 0; i < MATRIX.size(); i++) {
			Vector<Cell> r = MATRIX.get(i);
			StringBuilder row = new StringBuilder();
			for (int j = 0; j < r.size(); j++) {
				Cell cell = r.get(j);
				row.append(cell.value).append(" ");
			}
			System.out.println(row);
		}
		System.out.println("");
	}

	private static void buildMATRIX(Vector<Vector<String>> matrix) {
		for (int i = 0; i < matrix.size(); i++) {
			Vector<Cell> rowCell = new Vector<>();
			for (int j = 0; j < matrix.get(i).size(); j++) {
				String c = matrix.get(i).get(j);
				int[] coord = {i, j};
				Cell cell = new Cell(c, coord);
				rowCell.add(cell);
			}
			MATRIX.add(rowCell);
		}
	}

	private static void findIslands(Vector<Vector<Cell>> matrix) {
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				Cell cell = matrix.get(i).get(j);
				if (cell.value.equals("X")) {

					ArrayList<Cell> adjacentCells = new ArrayList<>();

					// inspect adjacent neighbors
					Cell cellAbove = getCellAbove(cell);
					if(isValidCell(cellAbove))
						adjacentCells.add(cellAbove);

					Cell cellBelow = getCellBelow(cell);
					if(isValidCell(cellBelow))
						adjacentCells.add(cellBelow);

					Cell cellLeft = getCellLeft(cell);
					if(isValidCell(cellLeft))
						adjacentCells.add(cellLeft);

					Cell cellRight = getCellRight(cell);
					if(isValidCell(cellRight))
						adjacentCells.add(cellRight);

					if(isNewIsland(adjacentCells)){
						totalNumberIslands++;
						Island island = new Island(totalNumberIslands);
						islands.add(island);
						cell.isIsland = Boolean.TRUE;
						cell.islandNum = totalNumberIslands;
					}

					placeCellInIsland(cell);
					setAdjacentCells(cell,adjacentCells);
				}
			}
		}
	}


	private static String cellDetails(Cell cell){
		String detail = "";
		detail += "[" + cell.coord[0] + ","  + cell.coord[1] + "] ";
		detail += " is Island? " + cell.isIsland;
		detail += " islandNum: " + cell.islandNum;
		return detail;
	}

	private static String islandDetail(Island island){
		String detail = "";
		detail += "island num {" + island.islandNum + "} " ;
		detail += cellsDetail(island.cells);
		return detail;
	}

	private static void setAdjacentCells(Cell cell, ArrayList<Cell> adjacentCells){
		for(Cell adjacentCell: adjacentCells){
			if(!adjacentCell.isIsland){
				adjacentCell.isIsland = cell.isIsland;
				adjacentCell.islandNum = cell.islandNum;
				//place cell in the island object

			}else{
				if(adjacentCell.islandNum < cell.islandNum){
					// remove island and concat cells
					removeIsland(adjacentCell, cell);
					totalNumberIslands--;
				}
			}
		}
	}

	private static String cellsDetail(ArrayList<Cell> cells){
		String detail = "";
		for(Cell cell: cells){
			detail += "[" + cell.coord[0] + ","  + cell.coord[1] + "] ";
		}
		return detail;
	}

	private static void placeCellInIsland(Cell cell) {
		for(Island island: islands){
			if(island.islandNum == cell.islandNum){
				island.cells.add(cell);
			}
		}
	}

	private static void removeIsland(Cell adjacentCell, Cell cell) {

		Island islandReceivingCells = null;
		Island islandRemoved = null;

		for (Island island: islands){
			if(island.islandNum == adjacentCell.islandNum){
				islandReceivingCells = island;
			}
		}

		for (Island island: islands){
			if(island.islandNum == cell.islandNum){
				islandRemoved = island;
				for(Cell c: island.cells){
					if(islandReceivingCells != null){
						islandReceivingCells.cells.add(c);
					}

				}
			}
		}
		islands.remove(islandRemoved);
	}

	private static Boolean isValidCell(Cell cell) {
		return (!(cell == null)) ? Boolean.TRUE : Boolean.FALSE;
	}

	private static Boolean isNewIsland(ArrayList<Cell> adjacentCells){
		int currentIslands = 0;
		Boolean createNewIsland = Boolean.FALSE;
		for(int n=0;n<adjacentCells.size();n++){
			Cell adjacentCell = adjacentCells.get(n);
			if(adjacentCell.isIsland)
				currentIslands++;
		}
		if(currentIslands == 0){
			createNewIsland = Boolean.TRUE;
		}
	 return createNewIsland;
	}


	private static Cell getCellAbove(Cell cell) {
		// is cell on top border?
		Cell c = null;
		if (cell.coord[0] == 0) {
		} else {
			c = MATRIX.get(cell.coord[0]-1).get(cell.coord[1]);
		}
		return c;
	}

	private static Cell getCellBelow(Cell cell) {
		// is cell on bottom border?
		Cell c = null;
		if (cell.coord[0] == MATRIX.size()-1) {
		} else {
			c = MATRIX.get(cell.coord[0]+1).get(cell.coord[1]);
		}
		return c;
	}

	private static Cell getCellLeft(Cell cell) {
		// is cell on left border?
		Cell c = null;
		if (cell.coord[1] == 0) {
		} else {
			c = MATRIX.get(cell.coord[0]).get(cell.coord[1]-1);
		}
		return c;
	}

	private static Cell getCellRight(Cell cell) {
		// is cell on right border?
		Cell c = null;
		if (cell.coord[1] == MATRIX.size()-1) {
		} else {
			c = MATRIX.get(cell.coord[0]).get(cell.coord[1] + 1);
		}
		return c;
	}


	private static void inspectIslands(Vector<Vector<Cell>> matrix) {
		for (int i = 0; i < MATRIX.size(); i++) {
			Vector<Cell> r = MATRIX.get(i);
			for (int j = 0; j < r.size(); j++) {
				Cell cell = r.get(j);
				if (cell.value.equals("X")) {
				}
			}
		}
	}

	private static void resetIslandDetail(){
		int count = 0;
		for (Island island: islands){
			count++;
			island.islandNum = count;
			for(Cell cell: island.cells){
				cell.islandNum = count;
			}
		}
	}

	private static void printMATRIXasIslandNum(Vector<Vector<Cell>> matrix) {
		for (int i = 0; i < MATRIX.size(); i++) {
			Vector<Cell> r = MATRIX.get(i);
			StringBuilder row = new StringBuilder();
			for (int j = 0; j < r.size(); j++) {
				Cell cell = r.get(j);
				if(cell.value.equals("X")){
					row.append(cell.islandNum).append(" ");
				}else{
					row.append(cell.value).append(" ");
				}

			}
			System.out.println(row);
		}
	}

	private static void printTotalNumberIslands(){
		System.out.println("------------> NUMBER OF ISLANDS FOUND = "+ islands.size() );
	}


} // class
