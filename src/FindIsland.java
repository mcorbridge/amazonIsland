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
		start();
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
			//setMATRIXasIslandNum(MATRIX);
			//System.out.println();
			//printMATRIX(MATRIX);
			printTotalNumberIslands();
		}
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
	To inspect the grid for contingent cells, each cell in the grid is converted to a Cell object.  This is the
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
	above/below/left/right of itself looking for neighboring cells that also contain an 'X'.  If this cell is not yet
	part of an island then an island is created, that cell is added to the new island, and the number of total islands
	are incremented. If cells containing an 'X' are found then they are added to the existing Island object.  If no
	adjacent 'X' are found, then this indicates a single solitary cell, but an island nonetheless.
	 */
	private static void findIslands(Vector<Vector<Cell>> matrix) {

		Island island = null;

		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				Cell cell = matrix.get(i).get(j);
				if (cell.value.equals("X")) {

					//System.out.println("(a)look at " + cellDetail(cell));

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

					if (isNewIsland(adjacentCells)) {
						totalNumberIslands++;
						//System.out.println("               island added: total= " + totalNumberIslands);
						island = new Island(totalNumberIslands);
						islands.add(island);
						cell.isIsland = Boolean.TRUE;
						cell.islandNum = totalNumberIslands;
						//System.out.println("     (a)add " + cellDetail(cell));
						//island.cells.add(cell);
						//System.out.println("add " + cellDetail(cell));
					}

					setAdjacentCells(cell, adjacentCells);
				}
			}
		}
	}

	/*

	 */
	private static void setAdjacentCells(Cell cell, ArrayList<Cell> adjacentCells) {
		Island island;
		//System.out.println("(b)look at " + cellDetail(cell) + " adj " + adjacentCells.size());

		ArrayList<Cell> tmp = adjacentCells;

		for (Cell adjacentCell : adjacentCells) {

			if (!adjacentCell.isIsland) {
				if (adjacentCell.value.equals("X")) {
					adjacentCell.isIsland = cell.isIsland;
					adjacentCell.islandNum = cell.islandNum;
					//System.out.println("     (b)add " + cellDetail(cell));
					island = getIslandFromCellIslandNum(adjacentCell);
					//island.cells.add(adjacentCell);
					//System.out.println("add " + cellDetail(adjacentCell));
				}
			} else {
				if (adjacentCell.islandNum < cell.islandNum) {
					//removeIsland(adjacentCell, cell);
					 cell.isIsland = adjacentCell.isIsland;
					 cell.islandNum = adjacentCell.islandNum;
					totalNumberIslands--;
					//System.out.println("island removed: total= " + totalNumberIslands);
				}
			}
		}
	}


	private static void removeIsland(Cell adjacentCell, Cell cell) {
		Island islandToReceive = getIslandFromCellIslandNum(adjacentCell);
		Island islandToRemove = getIslandFromCellIslandNum(cell);
		islandToReceive.cells.addAll(islandToRemove.cells);
		islands.remove(islandToRemove);
	}

	private static Island getIslandFromCellIslandNum(Cell cell){

		Island island = null;
		for(Island i: islands){
			if(i.islandNum == cell.islandNum){
				island = i;
			}
		}
		return island;
	}

	private static Boolean isValidCell(Cell cell) {
		return (!(cell == null)) ? Boolean.TRUE : Boolean.FALSE;
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
		for(Vector<Cell> row: MATRIX){
			for(Cell cell: row){
				for (Island island : islands) {
					for(Cell c: island.cells){
						if(cell.coord[0] == c.coord[0] && cell.coord[1] == c.coord[1]){
							cell.value = Integer.toString(c.islandNum);
						}
					}
				}
			}
		}
	}

	private static String cellDetail(Cell cell) {
		String detail = "";
		detail += " [" + cell.coord[0] + "," + cell.coord[1] + "] ";
		detail += " value " + cell.value;
		detail += " isIsland? " + cell.isIsland;
		detail += " islandNum: " + cell.islandNum;
		return detail;
	}

	private static String islandDetail(Island island) {
		String detail = "";
		detail += "island num {" + island.islandNum + "} ";
		for (Cell cell : island.cells) {
			detail += cellDetail(cell) + " | ";
		}

		return detail;
	}

	private static void printTotalNumberIslands() {
//		System.out.println("------------> NUMBER OF ISLANDS FOUND = " + islands.size());
		System.out.println("------------> NUMBER OF ISLANDS FOUND = " + totalNumberIslands);
		System.out.println("**********************************************************");
	}


} // class
