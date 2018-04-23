

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Mike on 4/15/2018.
 */
public class IslandPattern {

	/**
	 * It needs to be easy to create string representations of the grid used in the algorithm.  That makes it
	 * easy to test your artistic skills and create your own fancy grid. Go ahead!  Try to fool the algorithm!
	 * But...the algorithm finds it hard to digest Strings, so we make it more palatable by turning them into Vectors
	 * Yum! Originally I built this around a square, but that was BORING!  Hence this algorithm accepts rectangles - a
	 * square is a rectangle, but a rectangle is not a .... oh, you know the rest.
	 * @param matrixString
	 * @param dim
	 * @return Vector<Vector<String>>
	 */
	public static Vector<Vector<String>> buildMatrix(String matrixString, int[] dim){
		Vector<Vector<String>> matrix = new Vector<>();
		String[] temp = matrixString.split("");

		int q = 0;
		int z = dim[1];

		for(int n=0;n<dim[0];n++){
			String[] range = Arrays.copyOfRange(temp,q,z);
			q = q + dim[1];
			z =  z + dim[1];
			Vector<String> vector = getVector(range);
			matrix.add(vector);
		}
		return matrix;
	}

	/**
	 * Side routine to take each row and return a vector
	 * @param range
	 * @return Vector<String>
	 */
	public static Vector<String> getVector(String[] range){
		Vector<String> vector = new Vector<>();
		for(int n=0;n<range.length;n++){
			vector.add(range[n]);
		}
		return vector;
	}

	/** *********************************************************************************
	 *							TEST GRIDS
	 ************************************************************************************/
	public static String fiveXfour =  	"XOOOO" +
										"OXOOO" +
										"OOXOO" +
										"OOOXO";


	public static String fourXfour =  	"XXOO" +
										"XXOO" +
										"OOOO" +
										"OOOX";

	public static String empty =	"0000" +
									"0000" +
									"0000" +
									"0000";

	public static String block =  	"XXXX" +
									"XXXX" +
									"XXXX" +
									"XXXX";

	public static String fiveXfive =  	"XOOOX" +
			"XXOOX" +
			"XOOOO" +
			"OOOXO" +
			"OOOXO";

	public static String testFive =  	"XXOOO" +
										"XXOOO" +
										"XOOOX" +
										"XOOXO" +
										"XOOOX";

	public static String testJive =  	"XOOXX" +
										"OXXOX" +
										"OOXOO" +
										"XOXXO" +
										"XXOOX";

	public static String test =		"OOOOOO" +
									"OOOXOO" +
									"OXXXOO" +
									"OOOOOO" +
									"OOOOOO" +
									"OOOOOO";

	public static String sixXsix =	"OOOOOO" +
									"OOXXOO" +
									"OOOXOO" +
									"OOOOOO" +
									"OXOOOO" +
									"XXXXXX";

	public static String tenXten =	"OOOXXOOOOO" +
									"OOOXXOOOOO" +
									"OOOOOOOOOO" +
									"OOOOOOOOOO" +
									"OOOOOOOOOO" +
									"OOOXOOOOOO" +
									"OOOOOOOOOO" +
									"OOOOOOOOOO" +
									"OOOOOOOOXX" +
									"OOOOOOOOXX";

	public static String smile = 	"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000"  +
									"XXXXXXXX000X00000000X000X" +
									"X0000000000X00000000X000X" +
									"X0000000000X00000000X000X" +
									"XXXXXX00000X00000000X000X" +
									"X0000000000X00000000X000X" +
									"X0000000000X00000000X000X" +
									"X0000000000X00000000X000X" +
									"X0000000000X00000000X000X" +
									"X0000000000X00000000X0000" +
									"X0000000000XXXXXXXXXX000X" +
									"0000000000000000000000000"  +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000" +
									"0000000000000000000000000";

	public static String simple = 	"0000000000" +
									"000000X000" +
									"000000X000" +
									"0000XXX000" +
									"0000000000" +
									"0000000000" +
									"0000000000" +
									"0000000000" +
									"0000000000" +
									"0000000000";

	public static String simpleTwo = 	"0000000000" +
										"0000000000" +
										"0XXX000000" +
										"000XXXX000" +
										"000000XXXX" +
										"0000000000" +
										"0000000000" +
										"0000000000" +
										"0000000000" +
										"0000000000";

	public static String simpleThree = 	"0000000000000000000" +
										"0000XX0000000XX0000" +
										"0000XX0000000XX0000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000" +
										"0000000000000000000";

	public static String simpleSquare = 	"00XX00000000000XX000" +
											"00XX00000000000XX000" +
											"0000000XX00000000000" +
											"XX00000XX000000000XX" +
											"XX0000000000000000XX" +
											"0000000XX00000000000" +
											"0000000XX00000000000" +
											"XX0000000000000000XX" +
											"XX00000XX000000000XX" +
											"0000000XX00000000000" +
											"XX0000000000000000XX" +
											"0000000XX00000XX0000" +
											"0000000XX00000XX0000" +
											"XX0000000000000000XX" +
											"0000000XX00000000000" +
											"0000000XX00000000000" +
											"00XX000000000XX00000" +
											"0000000XX00000000000" +
											"0000000XX000000000XX" +
											"000000000000000000XX";

	public static String testU =	"OOOOOO" +
									"OXOOXO" +
									"OXOOXO" +
									"OXOOXO" +
									"OXXXXO" +
									"OOOOOO";

	public static String cross ="OOXOO" +
								"OXXXO" +
								"OOXOO" +
								"OOOOO" +
								"OOOOO";

	public static String box =	"XXXXXXXXXX" +
								"XOOOOOOOOX" +
								"XOOOOOOOOX" +
								"XOOOXXOOOX" +
								"XOOOXXOOOX" +
								"XOOOOOOOOX" +
								"XOOOOOOOOX" +
								"XOOOOOOOOX" +
								"XOOOOOOOOX" +
								"XXXXXXXXXX";

	public static String singleTest =	"XXXOOOOXXX" +
										"XXOOOOOOXX" +
										"XOOOOOOOOX" +
										"OOOOXOOOOO" +
										"OOOOOOOOOO" +
										"OOOOXOOOOO" +
										"OOOOOOOOOO" +
										"XOOOOOOOOX" +
										"XXOOOOOOOX" +
										"XXXOOOOXXX";

	public static String diagonal =	"XOOOO" +
									"OXOOO" +
									"OOXOO" +
									"OOOXO" +
									"OOOOX";

	public static String corner =	"OOX" +
									"OXX" +
									"XXX";

	public static String ex =	"XOOOX" +
								"OXOXO" +
								"OOXOO" +
								"OXOXO" +
								"XOOOX";

	public static String effu =	"0000000000" +
								"0000000000" +
								"X0X0000000" +
								"X000000000" +
								"XX00000000" +
								"X000000000" +
								"X000000000" +
								"0000000000" +
								"0000000000" +
								"0000000000";

	public static String mattKevin =	"0000000000" +
										"0000000000" +
										"XX0000XX00" +
										"X0X00X0X00" +
										"X00X0X0X00" +
										"X000X00X00" +
										"X000000X00" +
										"0000000000" +
										"X0X0X0X0X0" +
										"0000000000";

} // class
