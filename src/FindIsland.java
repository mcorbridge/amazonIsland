/**
 * Created by Mike on 4/15/2018.
 * copyright Michael D. Corbridge
 */

import java.util.ArrayList;
import java.util.Vector;

public class FindIsland {

	private static ArrayList<Vector> matrixList;
	private static ArrayList<Island> islands = new ArrayList<>();
	private static Vector<Vector<Cell>> MATRIX;
	private static int islandNdx;
	private static ArrayList<Cell> listCells;
	private static int ndx;

	public static void main(String[] args) {
		setMatrix();
		start();
	}

	/*
	To test the algorithm under a number of cases, a bunch of 2D matrices are created and supplied to the algorithm to ensure
	that it works universally
	 */
	private static void setMatrix() {
		matrixList = new ArrayList<>();
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.fourXfour,4));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.empty,4));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.block,4));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.fiveXfive,5));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.test,6));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.sixXsix,6));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.tenXten,10));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.smile,25));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.simple,10));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.simpleTwo,10));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.simpleThree,19));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.simpleSquare,20));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.testU,6));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.cross,5));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.singleTest,10));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.diagonal,5));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.corner,3));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.ex,5));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.effu,10));
	}

	private static void start() {
		for (Vector<Vector<String>> matrix : matrixList ){
			islands = new ArrayList<>();
			MATRIX = new Vector<Vector<Cell>>();
			islandNdx = 0;
			ndx = 0;
			buildMATRIX(matrix);
			printMATRIX(MATRIX);
			buildIslandList();
			if(listCells.size() == 0){
				doSummary();
			}else{
				findIslands(listCells.get(0));
				doSummary();
			}
		}
	}

	private static void buildIslandList() {
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

	private static void findIslands(Cell cell) {

		ndx++;
		Island island;

		if (isTotalNumberIslandsIncrement(cell)) {
			island = new Island(islandNdx);
			cell.isIsland = Boolean.TRUE;
			cell.islandNum = islandNdx;
			island.cells.add(cell);
			islands.add(island);
			islandNdx++;
		}else{
			island = islands.get(cell.islandNum);
			cell.isIsland = Boolean.TRUE;
			cell.islandNum = island.islandNum;
			island.cells.add(cell);
		}

		if (cell.contiguous.size() > 0) {
			for (Cell cc : cell.contiguous) {
				if (!cc.isIsland) {
					findIslands(cc);
				}
			}
		}

		if (ndx <= listCells.size() - 1) {
			Cell nextCell = getNextCell();
			findIslands(nextCell);
		}
	}

	private static void doSummary() {
		System.out.println("TOTAL ISLANDS: " + islands.size());
		System.out.println("********************************\n");
	}

	private static Cell getNextCell() {
		Cell nextCell = null;
		for (Cell cell : listCells) {
			if (!cell.isIsland) {
				nextCell = cell;
				break;
			}
		}
		return nextCell;
	}

	private static Boolean isTotalNumberIslandsIncrement(Cell cell) {
		Boolean isIncrement = Boolean.TRUE;
		if (islands.size() == 0) {
			isIncrement = Boolean.TRUE;
		} else {
			for (Island island : islands) {
				for (Cell ic : island.cells) {
					for(Cell cc:ic.contiguous){
						if(cell.coord[0] == cc.coord[0] && cell.coord[1] == cc.coord[1]){
							isIncrement = Boolean.FALSE;
						}
					}
				}
			}
		}
		return isIncrement;
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

	private static Boolean isValidCell(Cell cell) {
		return (cell != null && cell.value.equals("X")) ? Boolean.TRUE : Boolean.FALSE;
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

} // class
