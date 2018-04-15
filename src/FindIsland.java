/**
 * Created by Mike on 4/4/2018.
 * copyright Michael D. Corbridge
 */


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
		//start();
		startResursion();
	}

	/*
	To test the algorithm under a number of cases, a bunch of 2D matrices are created and supplied to the algorithm to ensure
	that it works universally
	 */
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
		matrixList.add(IslandPattern.buildSingleTest());
		matrixList.add(IslandPattern.buildDiagonal());
		matrixList.add(IslandPattern.buildCorner());
		matrixList.add(IslandPattern.buildEx());
	}

	private static void start() {
		for (Vector<Vector<String>> matrix : matrixList) {
			islands = new ArrayList<>();
			MATRIX = new Vector<Vector<Cell>>();
			totalNumberIslands = 0;

			buildMATRIX(matrix);
			printMATRIX(MATRIX);
			findIslands(MATRIX);
			resetIslandDetail();
			setMATRIXasIslandNum(MATRIX);
			System.out.println();
			printMATRIX(MATRIX);
			printTotalNumberIslands();
		}
	}

	private static void startResursion() {
		Vector<Vector<String>> matrix = matrixList.get(18);
		islands = new ArrayList<>();
		MATRIX = new Vector<Vector<Cell>>();
		totalNumberIslands = 0;
		buildMATRIX(matrix);
		printMATRIX(MATRIX);
		findIslandsResursive();
		doRecursiveFind(listCells.get(0));
		doSummary();
	}

	private static ArrayList<Cell> listCells;

	private static void findIslandsResursive() {
		listCells = new ArrayList<>();
		for (int i = 0; i < MATRIX.size(); i++) {
			for (int j = 0; j < MATRIX.get(i).size(); j++) {
				Cell cell = MATRIX.get(i).get(j);
				if (cell.value.equals("X")) {
					cell.contiguous = getContiguousCells(cell);
					listCells.add(cell);
				}
			}
		}
	}

	private static int ndx = 0;

	private static ArrayList<Cell> inspectedCells = new ArrayList<>();

	private static void doRecursiveFind(Cell cell) {


		ndx++;

		if(isTotalNumberIslandsIncrement(cell)){
			totalNumberIslands++;
			System.out.println(".........................................island added: " + totalNumberIslands);
			inspectedCells = new ArrayList<>();
			inspectedCells.add(cell);
		}

		cell.isIsland = Boolean.TRUE;
		cell.islandNum = totalNumberIslands;

		System.out.println("{"+ ndx + "} " + cellDetail(cell));

		if(cell.contiguous.size() > 0){
			for(Cell cc: cell.contiguous){
				if(!cc.isIsland){
					inspectedCells.add(cell);
					cell.isIsland = Boolean.TRUE;
					cell.islandNum = totalNumberIslands;
					doRecursiveFind(cc);
				}
			}
		}

		if(ndx <= listCells.size()-1){
			doRecursiveFind(getNextCell());
		}
	}

	private static void doSummary() {
		for (Cell cell : listCells) {
			//System.out.println(cellDetail(cell));
		}
	}

	private static Cell getNextCell(){
		Cell nextCell = null;
		for (Cell cell: listCells){
			if(!cell.isIsland){
				nextCell = cell;
				break;
			}
		}
		return  nextCell;
	}


	/*
	we need to reassess this method - should NOT compare contiguous cells in both cells (ya follow?)
	instead should be the cell coord with the cell contiguous (I think?)
	 */
	private static Boolean isTotalNumberIslandsIncrement(Cell cell) {
		Boolean isIncrement = Boolean.TRUE;
		System.out.println("look for [" + cell.coord[0] + "," + cell.coord[1] + "]");
		System.out.println("in " + showInspectedCellsCoord());
		if(inspectedCells.size() == 0){
			isIncrement = Boolean.TRUE;
		}else{
			for(Cell ic:inspectedCells){
				for(Cell ac:ic.contiguous){
					if(cell.coord[0] == ac.coord[0] && cell.coord[1] == ac.coord[1]){
						isIncrement = Boolean.FALSE;
						break;
					}
				}
			}
		}
		System.out.println();
		return isIncrement;
	}


	private static String showInspectedCellsCoord(){
		String temp = "";
		for(Cell ic:inspectedCells){
			for(Cell ac:ic.contiguous){
				temp += "[" + ac.coord[0] + "," + ac.coord[1] + "] ";
			}
		}
		return temp;
	}

	private static ArrayList<Cell> getContiguousCells(Cell cell) {
		ArrayList<Cell> adjacentCells = new ArrayList<>();

		// inspect adjacent neighbors
		Cell cellAbove = getCellAbove(cell);
		if (isValidCell(cellAbove))
			adjacentCells.add(cellAbove);

		Cell cellBelow = getCellBelow(cell);
		if (isValidCell(cellBelow))
			adjacentCells.add(cellBelow);

		Cell cellLeft = getCellLeft(cell);
		if (isValidCell(cellLeft))
			adjacentCells.add(cellLeft);

		Cell cellRight = getCellRight(cell);
		if (isValidCell(cellRight))
			adjacentCells.add(cellRight);

		Cell cellTopLeft = getCellTopLeft(cell);
		if (isValidCell(cellTopLeft)) {
			adjacentCells.add(cellTopLeft);
		}


		Cell cellTopRight = getCellTopRight(cell);
		if (isValidCell(cellTopRight)) {
			adjacentCells.add(cellTopRight);
		}

		Cell cellBottomLeft = getCellBottomLeft(cell);
		if (isValidCell(cellBottomLeft)) {
			adjacentCells.add(cellBottomLeft);
		}

		Cell cellBottomRight = getCellBottomRight(cell);
		if (isValidCell(cellBottomRight)) {
			adjacentCells.add(cellBottomRight);
		}

		return adjacentCells;

	}

	/*
	A simple method to show the user what the actual 2D matrix looks like
	 */
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
	}

	/*
	To inspect the grid for CONTIGUOUS (sharing a border) cells, each cell in the grid is converted to a Cell object.  This is the
	actual 2D matrix that will be used to find the islands
	 */
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


	/*
	The algorithm will loop through the rows of cells looking for an 'X'.  If one is found, it will immediately search
	above/below/left/right of itself looking for contiguous cells that also contain an 'X'.  If this cell is not yet
	part of an island then an island is created, that cell is added to the new island (incrementing the total # islands)
	as well as all contiguous 'X' cells found. If no adjacent 'X' are found, then this indicates a single solitary cell,
	but an island nonetheless.
	 */
	private static void findIslands(Vector<Vector<Cell>> matrix) {

		Island island = null;

		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				Cell cell = matrix.get(i).get(j);
				if (cell.value.equals("X")) {

					ArrayList<Cell> adjacentCells = new ArrayList<>();

					// inspect adjacent neighbors
					Cell cellAbove = getCellAbove(cell);
					if (isValidCell(cellAbove))
						adjacentCells.add(cellAbove);

					Cell cellBelow = getCellBelow(cell);
					if (isValidCell(cellBelow))
						adjacentCells.add(cellBelow);

					Cell cellLeft = getCellLeft(cell);
					if (isValidCell(cellLeft))
						adjacentCells.add(cellLeft);

					Cell cellRight = getCellRight(cell);
					if (isValidCell(cellRight))
						adjacentCells.add(cellRight);

					/*
					 ************************* test **********************
					 */
					Cell cellTopLeft = getCellTopLeft(cell);
					if (isValidCell(cellTopLeft)) {
						//System.out.println("found cellTopLeft + " + cellDetail(cellTopLeft));
						adjacentCells.add(cellTopLeft);
					}


					Cell cellTopRight = getCellTopRight(cell);
					if (isValidCell(cellTopRight)) {
						//System.out.println("found cellTopRight + " + cellDetail(cellTopRight));
						adjacentCells.add(cellTopRight);
					}

					Cell cellBottomLeft = getCellBottomLeft(cell);
					if (isValidCell(cellBottomLeft)) {
						//System.out.println("found cellBottomLeft + " + cellDetail(cellBottomLeft));
						adjacentCells.add(cellBottomLeft);
					}

					Cell cellBottomRight = getCellBottomRight(cell);
					if (isValidCell(cellBottomRight)) {
						//System.out.println("found cellBottomRight + " + cellDetail(cellBottomRight));
						adjacentCells.add(cellBottomRight);
					}
					/*
					  ************************* test **********************
					 */

					if (isNewIsland(adjacentCells)) {
						totalNumberIslands++;
						island = new Island(totalNumberIslands);
						islands.add(island);
						cell.isIsland = Boolean.TRUE;
						cell.islandNum = totalNumberIslands;
						isDuplicateCell(cell, island);
						if (!isDuplicateCell(cell, island)) {
							island.cells.add(cell);
						}
					} else {
						island = getIslandFromCells(adjacentCells);
						if (!isDuplicateCell(cell, island)) {
							island.cells.add(cell);
						}
					}
					setAdjacentCells(cell, adjacentCells, island);
				}
			}
		}
	}

	/*

	 */
	private static void setAdjacentCells(Cell cell, ArrayList<Cell> adjacentCells, Island island) {
		for (Cell adjacentCell : adjacentCells) {
			if (!adjacentCell.isIsland) {
				adjacentCell.isIsland = cell.isIsland;
				adjacentCell.islandNum = cell.islandNum;
				isDuplicateCell(cell, island);
				if (!isDuplicateCell(cell, island)) {
					island.cells.add(cell);
				}
			} else {
				if (adjacentCell.islandNum < cell.islandNum) { // this is a *collision*
					removeIsland(cell, island);
					cell.isIsland = adjacentCell.isIsland;
					cell.islandNum = adjacentCell.islandNum;
					totalNumberIslands--;
				}
			}
		}
	}

	private static Boolean isDuplicateCell(Cell cell, Island island) {
		Boolean isDupe = Boolean.FALSE;
		for (Cell c : island.cells) {
			if (c.coord[0] == cell.coord[0] && c.coord[1] == cell.coord[1]) {
				isDupe = Boolean.TRUE;
			}
		}
		return isDupe;
	}


	private static void removeIsland(Cell cell, Island island) {
		Island islandToRemove = getIslandFromCell(cell);
		for (Cell c : islandToRemove.cells) {
			if (!isDuplicateCell(c, island)) {
				island.cells.add(c);
			}
		}
		islands.remove(islandToRemove);
	}

	private static Island getIslandFromCell(Cell cell) {
		Island island = null;
		for (Island i : islands) {
			if (i.islandNum == cell.islandNum) {
				island = i;
			}
		}
		return island;
	}

	private static Island getIslandFromCells(ArrayList<Cell> adjacentCells) {
		Cell c = null;
		for (Cell cell : adjacentCells) {
			if (cell.islandNum != 0) {
				c = cell;
			}
		}
		return getIslandFromCell(c);
	}

	private static Boolean isValidCell(Cell cell) {
		//return (!(cell == null)) ? Boolean.TRUE : Boolean.FALSE;
		return (cell != null && cell.value.equals("X")) ? Boolean.TRUE : Boolean.FALSE;
	}

	private static Boolean isNewIsland(ArrayList<Cell> adjacentCells) {
		int currentIslands = 0;
		Boolean createNewIsland = Boolean.FALSE;
		for (Cell cell : adjacentCells) {
			if (cell.isIsland)
				currentIslands++;
		}
		if (currentIslands == 0) {
			createNewIsland = Boolean.TRUE;
		}
		return createNewIsland;
	}

	private static Cell getCellAbove(Cell cell) {
		// is cell on top border?
		Cell c = null;
		if (cell.coord[0] == 0) {
		} else {
			c = MATRIX.get(cell.coord[0] - 1).get(cell.coord[1]);
		}
		return c;
	}

	private static Cell getCellBelow(Cell cell) {
		// is cell on bottom border?
		Cell c = null;
		if (cell.coord[0] == MATRIX.size() - 1) {
		} else {
			c = MATRIX.get(cell.coord[0] + 1).get(cell.coord[1]);
		}
		return c;
	}

	private static Cell getCellLeft(Cell cell) {
		// is cell on left border?
		Cell c = null;
		if (cell.coord[1] == 0) {
		} else {
			c = MATRIX.get(cell.coord[0]).get(cell.coord[1] - 1);
		}
		return c;
	}

	private static Cell getCellRight(Cell cell) {
		// is cell on right border?
		Cell c = null;
		if (cell.coord[1] == MATRIX.size() - 1) {
		} else {
			c = MATRIX.get(cell.coord[0]).get(cell.coord[1] + 1);
		}
		return c;
	}

	private static Cell getCellTopLeft(Cell cell) {
		// cell at top left corner
		Cell c = null;
		if (!(cell.coord[0] == 0 || cell.coord[1] == 0)) {
			c = MATRIX.get(cell.coord[0] - 1).get(cell.coord[1] - 1);
		}
		return c;
	}

	private static Cell getCellTopRight(Cell cell) {
		// cell at top right corner
		Cell c = null;
		if (!(cell.coord[0] == 0 || cell.coord[1] == MATRIX.size() - 1)) {
			c = MATRIX.get(cell.coord[0] - 1).get(cell.coord[1] + 1);
		}
		return c;
	}

	private static Cell getCellBottomLeft(Cell cell) {
		// cell at bottom left corner
		Cell c = null;
		if (!(cell.coord[0] == MATRIX.size() - 1 || cell.coord[1] == 0)) {
			c = MATRIX.get(cell.coord[0] + 1).get(cell.coord[1] - 1);
		}
		return c;
	}

	private static Cell getCellBottomRight(Cell cell) {
		// cell at bottom right corner
		Cell c = null;
		if (!(cell.coord[0] == MATRIX.size() - 1 || cell.coord[1] == MATRIX.size() - 1)) {
			c = MATRIX.get(cell.coord[0] + 1).get(cell.coord[1] + 1);
		}
		return c;
	}

	private static void resetIslandDetail() {
		int count = 0;
		for (Island island : islands) {
			count++;
			island.islandNum = count;
			for (Cell cell : island.cells) {
				cell.islandNum = count;
			}
		}
	}

	private static void setMATRIXasIslandNum(Vector<Vector<Cell>> matrix) {
		for (Island island : islands) {
			for (Cell cell : island.cells) {
				for (Vector<Cell> row : MATRIX) {
					for (Cell c : row) {
						if (c.coord[0] == cell.coord[0] && c.coord[1] == cell.coord[1]) {
							c.value = Integer.toString(cell.islandNum);
						}
					}
				}
			}
		}
	}

	private static String contiguousCells(ArrayList<Cell> cells) {
		String cellList = "";
		for (Cell cell : cells) {
			cellList += "[" + cell.coord[0] + "," + cell.coord[1] + "]";
		}
		return cellList;
	}

	private static String cellDetail(Cell cell) {
		String detail = "";
		detail += " [" + cell.coord[0] + "," + cell.coord[1] + "] ";
		detail += " value " + cell.value;
		detail += " isIsland? " + cell.isIsland;
		detail += " contiguous with: " + contiguousCells(cell.contiguous);
		detail += " islandNum: " + cell.islandNum;
		return detail;
	}

	private static String islandDetail(Island island) {
		String detail = "";
		detail += "island num {" + island.islandNum + "} ";
		detail += "num cells=" + island.cells.size() + " ";
		for (Cell cell : island.cells) {
			detail += cellDetail(cell) + " | ";
		}

		return detail;
	}

	private static void printTotalNumberIslands() {
		System.out.println("------------> NUMBER OF ISLANDS FOUND = " + islands.size());
		System.out.println("**********************************************************");
	}


} // class
