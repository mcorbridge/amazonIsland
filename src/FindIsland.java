/**
 * Created by Mike on 4/15/2018.
 * copyright Michael D. Corbridge
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class FindIsland {

	private static ArrayList<Vector> matrixList;
	private static ArrayList<Island> islands = new ArrayList<>();
	private static Vector<Vector<Cell>> MATRIX;
	private static int islandNdx;
	private static ArrayList<Cell> listCells;
	private static int ndx;


	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		setMatrix();
		start();
	}

	/**
	 To test the algorithm under a number of cases, a bunch of 2D matrices are created and supplied to the algorithm to
	 ensure that it works universally
	 */
	private static void setMatrix() {
		matrixList = new ArrayList<>();
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.fiveXfour, new int[]{4,5})); // this is a rectangle
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.fourXfour,new int[]{4,4})); // this is a square grid
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.empty,new int[]{4,4})); // so is this ...
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.block,new int[]{4,4}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.fiveXfive,new int[]{5,5}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.test,new int[]{6,6}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.sixXsix,new int[]{6,6}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.tenXten,new int[]{10,10}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.smile,new int[]{25,25}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.simple,new int[]{10,10}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.simpleTwo,new int[]{10,10}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.simpleThree,new int[]{19,19}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.simpleSquare,new int[]{20,20}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.testU,new int[]{6,6}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.cross,new int[]{5,5}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.singleTest,new int[]{10,10}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.diagonal,new int[]{5,5}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.corner,new int[]{3,3}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.ex,new int[]{5,5}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.effu,new int[]{10,10}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.testFive,new int[]{5,5}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.testJive, new int[]{5,5}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.box, new int[]{10,10}));
		matrixList.add(IslandPattern.buildMatrix(IslandPattern.mattKevin, new int[]{12,10}));
	}

	/**
	 * Start .. what does 'start' do?  It STARTS the whole process!  Since this algorithm is tested against any rectangular
	 * grid containing a mix of "X's" and "O's" we rip through a list of test matrices created in 'setMatrix'.  Note that
	 * if some goof adds a grid with NO "X's", the algorithm is bypassed.
	 *
	 */
	private static void start() {
		for (Vector<Vector<String>> matrix : matrixList) {
			islands = new ArrayList<>();
			MATRIX = new Vector<>();
			islandNdx = 0;
			ndx = 0;
			buildMATRIX(matrix);
			printMATRIX(MATRIX); // provide a visual of the grid currently being investigated
			buildCellList();
			if (listCells.size() > 0) { // run ONLY if at least one 'X' is present
				findIslands(listCells.get(0));
			}
			System.out.println("----------------");
			printIslandNum();  // annotate each 'X' as the island number
			doSummary(); // how many islands did you find?
		}
	}

	/**
	 *  The MATRIX (apologies to Neo) is built up of cells that comprise the entire grid.  However we are only concerned
	 *  with cells that contain an 'X'. Thus this function filters out only those cells containing 'X' AND finds all the
	 *  neighboring cells that also contain an 'X'.  Thus 'listCells' is the fuel for 'findIslands'.  Feed 'findIslands'
	 *  an ArrayList of cells from 'buildCellList' containing information about its contiguous cells, and watch it go to
	 *  work.
	 *
	 */
	private static void buildCellList() {
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

	/**
	 * This is the meat of the algorithm.
	 * Basically the original grid has now been boiled down to just those cells containing an 'X' by the previous 'buildCellList'
	 * function. To recap, it has looked at each of those cells (as a Cell object) and added all its neighbors (contiguous cells). Now
	 * comes the hard part: This function walks through each Cell relationships recursively to find its neighbors and update
	 * its island status (boolean), and the island (islandNum) that it now belongs to. If a cell looks at a neighboring cell
	 * and sees that it already belongs to an island, then that cell must join that island.  If none of its neighboring
	 * cells belongs to an island it must be a new island and the process continues until all cells have been inspected.
	 * As they say in the business: "easy peasy lemon squeezy"
	 * @param cell
	 */
	private static void findIslands(Cell cell) {

		ndx++;
		Island island;

		if (isAddIsland(cell)) {    // Is this cell a new island?  The first 'X' encuntered is always an island
			island = new Island(islandNdx);
			cell.isIsland = Boolean.TRUE;
			cell.islandNum = islandNdx;
			island.addCoord(cell);
			islands.add(island);
			islandNdx++;
		} else {
			if (!cell.isIsland) { // this cell already belongs to an existing island
				island = getIsland(cell);
				cell.isIsland = Boolean.TRUE;
				cell.islandNum = island.islandNum;
				island.addCoord(cell);
			}
		}

		if (cell.contiguous.size() > 0) {   // all the contiguous cells in this Cell object MUST belong to that cells island
			for (Cell cc : cell.contiguous) {
				if (!cc.isIsland) {
					cc.isIsland = Boolean.TRUE;
					cc.islandNum = cell.islandNum;
					island = islands.get(cell.islandNum);
					island.addCoord(cc);
					findIslands(cc);   // <-- recursion!   But what about the contiguous cells in the Cell objects contiguous cell?
				}
			}
		}

		if (ndx <= listCells.size() - 1) {
			Cell nextCell = getNextCell();
			findIslands(nextCell); // <-- recursion!
		}
	}

	/**
	 * What is an island?  Without getting too philosophical, an island is an 'X' cell/cells that is/are surrounded by
	 * 'O' cells.  EXCEPT!! for the first Cell containing an 'X', where no islands have previously been discovered, which
	 * just seeds the whole process.
	 * @param cell
	 * @return
	 */
	private static Boolean isAddIsland(Cell cell) {
		Boolean isIncrement = Boolean.TRUE;
		if (islands.size() == 0) {
			isIncrement = Boolean.TRUE;
		} else {
			outerloop:
			for (Island island : islands) {
				for (int[] ic : island.cellCoord) {
					if(compareCoord(ic,cell.contiguous)){
						isIncrement = Boolean.FALSE;
						break outerloop;
					}
				}
			}
		}
		return isIncrement;
	}

	/**
	 * A side routine to test if [0,1] is equal to [0,1] given that an array in java is a primitive.
	 * @param coord
	 * @param cells
	 * @return
	 */
	private static Boolean compareCoord(int[] coord, ArrayList<Cell> cells){
		Boolean isMatch = Boolean.FALSE;
		for(Cell cell : cells){
			if(Arrays.equals(coord, cell.coord)){
				isMatch = Boolean.TRUE;
				break;
			}
		}
		return isMatch;
	}

	/**
	 * Islands are comprised of cells, and each island stores the coords [row,column] of the cells that currently
	 * comprise that island.  If a cell is currently an island (boolean) then an inspection of the islands will return
	 * the Island object it is related to.
	 * @param cell
	 * @return
	 */
	private static Island getIsland(Cell cell) {
		Island rIsland = null;
		outerloop:
		for (Island island : islands) {
			for (int[] ic : island.cellCoord) {
				for(Cell cc: cell.contiguous){
					if(Arrays.equals(ic, cc.coord)){
						rIsland = island;
						break outerloop;
					}
				}
			}
		}
		return rIsland;
	}

	/**
	 *  simple function that outputs the number of islands found based on the 'islands' ArrayList size.
	 */
	private static void doSummary() {
		System.out.println("TOTAL ISLANDS: " + islands.size());
		System.out.println("********************************\n");
	}

	/**
	 * Makes sure we run through the entire list of cells that are designed with the 'X'
	 * @return
	 */
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

	/**
	 * Finding islands means looking at its cells neighboring cells to establish their relationship to that cell.  A neighbor is defined
	 * as any cell: above, below, left of, right of, or at the topLeft, topRight, bottomLeft, or bottomRight corner. If that
	 * cell contains an 'X', then it is added to the list of that cells contiguous cells.  I could have made this easier
	 * by just looking at those cells above, below, left or right ... but that was too boring!
	 * @param cell
	 * @return
	 */
	private static ArrayList<Cell> getContiguousCells(Cell cell) {
		ArrayList<Cell> contiguousCells = new ArrayList<>();

		// inspect ALL adjacent cells
		Cell cellAbove = getCellAbove(cell);
		if (isValidCell(cellAbove)) // the 'isValidCell' method ensures the neighboring cell is an 'X' and it is not outside the grid border
			contiguousCells.add(cellAbove);

		Cell cellBelow = getCellBelow(cell);
		if (isValidCell(cellBelow))
			contiguousCells.add(cellBelow);

		Cell cellLeft = getCellLeft(cell);
		if (isValidCell(cellLeft))
			contiguousCells.add(cellLeft);

		Cell cellRight = getCellRight(cell);
		if (isValidCell(cellRight))
			contiguousCells.add(cellRight);

		Cell cellTopLeft = getCellTopLeft(cell);
		if (isValidCell(cellTopLeft)) {
			contiguousCells.add(cellTopLeft);
		}

		Cell cellTopRight = getCellTopRight(cell);
		if (isValidCell(cellTopRight)) {
			contiguousCells.add(cellTopRight);
		}

		Cell cellBottomLeft = getCellBottomLeft(cell);
		if (isValidCell(cellBottomLeft)) {
			contiguousCells.add(cellBottomLeft);
		}

		Cell cellBottomRight = getCellBottomRight(cell);
		if (isValidCell(cellBottomRight)) {
			contiguousCells.add(cellBottomRight);
		}
		return contiguousCells;
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
	To inspect the grid for CONTIGUOUS (sharing a border or corner) cells, each cell in the grid is converted to a Cell
	object.  This is the actual 2D matrix that will be used to find the islands
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


	/**
	 *
	 * @param cell
	 * @return
	 */
	private static Boolean isValidCell(Cell cell) {
		return (cell != null && cell.value.equals("X")) ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * When describing groups of cells as individual islands, each cell is associated to another in the same island as
	 * having at least one neighbor cell to the left, right, top, bottom - or at the topLeft, topRight, bottomLeft, or
	 * bottomRight corner. That is, the cell has at least one of eight possible options that indicates it is part of a contiguous
	 * group (island).  The following group of eight functions takes the cell [row,column] coordinates and inspects
	 * each neighboring cell (if does not extend past the grid border) to see it is and 'X'. If it does, then that cell
	 * is added to the 'contiguous' property that comprises the Cell object.
	 */
	/*
	 (1) cell above target cell
	 */
	private static Cell getCellAbove(Cell cell) {
		Cell c = null;
		if (cell.coord[0] == 0) {
		} else {
			c = MATRIX.get(cell.coord[0] - 1).get(cell.coord[1]);
		}
		return c;
	}

	/*
	(2) cell below target cell
	 */
	private static Cell getCellBelow(Cell cell) {
		// is cell on bottom border?
		Cell c = null;
		if (cell.coord[0] == MATRIX.size() - 1) {
		} else {
			c = MATRIX.get(cell.coord[0] + 1).get(cell.coord[1]);
		}
		return c;
	}

	/*
	(3) cell left of target cell
	 */
	private static Cell getCellLeft(Cell cell) {
		// is cell on left border?
		Cell c = null;
		if (cell.coord[1] == 0) {
		} else {
			c = MATRIX.get(cell.coord[0]).get(cell.coord[1] - 1);
		}
		return c;
	}

	/*
	(4) cell right of target cell
	 */
	private static Cell getCellRight(Cell cell) {
		// is cell on right border?
		Cell c = null;
		if (cell.coord[1] == MATRIX.size() - 1) {
		} else {
			c = MATRIX.get(cell.coord[0]).get(cell.coord[1] + 1);
		}
		return c;
	}

	/*
	(5) cell at top left right corner of target cell
	 */
	private static Cell getCellTopLeft(Cell cell) {
		// cell at top left corner
		Cell c = null;
		if (!(cell.coord[0] == 0 || cell.coord[1] == 0)) {
			c = MATRIX.get(cell.coord[0] - 1).get(cell.coord[1] - 1);
		}
		return c;
	}

	/*
	(6) cell at top right corner of target cell
	 */
	private static Cell getCellTopRight(Cell cell) {
		// cell at top right corner
		Cell c = null;
		if (!(cell.coord[0] == 0 || cell.coord[1] == MATRIX.size() - 1)) {
			c = MATRIX.get(cell.coord[0] - 1).get(cell.coord[1] + 1);
		}
		return c;
	}

	/*
	(7) cell at bottom right corner of target cell
	 */
	private static Cell getCellBottomLeft(Cell cell) {
		// cell at bottom left corner
		Cell c = null;
		if (!(cell.coord[0] == MATRIX.size() - 1 || cell.coord[1] == 0)) {
			c = MATRIX.get(cell.coord[0] + 1).get(cell.coord[1] - 1);
		}
		return c;
	}

	/*
	(8) cell at bottom right corner of target cell
	 */
	private static Cell getCellBottomRight(Cell cell) {
		Cell c = null;
		if (!(cell.coord[0] == MATRIX.size() - 1 || cell.coord[1] == MATRIX.size() - 1)) {
			c = MATRIX.get(cell.coord[0] + 1).get(cell.coord[1] + 1);
		}
		return c;
	}


	/**
	 * Ya want further proof!  Well then this is it!  Each cell is printed out as its island number (starting at 1)
	 */
	private static void printIslandNum() {

		StringBuilder row = null;
		for (Vector<Cell> cells : MATRIX) {
			row = new StringBuilder();
			for (Cell cell : cells) {
				if (cell.isIsland && cell.islandNum == 0) {
					cell.islandNum = cell.islandNum + 1;
					cell.value = Integer.toString(cell.islandNum);
				} else if (cell.isIsland) {
					cell.islandNum = cell.islandNum + 1;
					cell.value = Integer.toString(cell.islandNum);
				}
				row.append(cell.value).append(" ");
			}
			System.out.println(row);
		}
	}
	/** *********************************************************************
	 *							UTIL FUNCTIONS
	 ********************************************************************** */
	/**
	 * debug tool to show the coords of the contiguous cells related to a Cell
	 * @param cells
	 * @return
	 */
	private static String contiguousCells(ArrayList<Cell> cells) {
		String cellList = "";
		for (Cell cell : cells) {
			cellList += "[" + cell.coord[0] + "," + cell.coord[1] + "]";
		}
		return cellList;
	}

	/**
	 * debug tool to show the detail of the Cell object
	 * @param cell
	 * @return
	 */
	private static String cellDetail(Cell cell) {
		String detail = "";
		detail += " [" + cell.coord[0] + "," + cell.coord[1] + "] ";
		detail += " value " + cell.value;
		detail += " isIsland? " + cell.isIsland;
		detail += " contiguous with: " + contiguousCells(cell.contiguous);
		detail += " islandNum: " + cell.islandNum;
		return detail;
	}

	/**
	 * debug tool (wait! you needed to debug this code?) to show the detail of the Island object
	 * @param island
	 * @return
	 */
	private static String islandDetail(Island island) {
		String detail = "";
		detail += "island num {" + island.islandNum + "} ";
		for (int[] coord : island.cellCoord) {
			detail += "[" + coord[0] + "," + coord[1] + "]";
		}
		return detail;
	}

} // class
