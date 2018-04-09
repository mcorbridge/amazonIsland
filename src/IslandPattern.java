

import java.util.Vector;

/**
 * Created by Mike on 4/3/2018.
 */
public class IslandPattern {

	public static String fourXfour =  	"XXOO" +
										"XXOO" +
										"OOOO" +
										"OOOX";

	public static Vector<Vector<String>> buildFourXFour(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = fourXfour.split("");
		int q = 0;
		int z = 4;
		for(int n=0;n<4;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 4;
			z += 4;
		}
		return matrix;
	}

	public static String empty =	"0000" +
									"0000" +
									"0000" +
									"0000";

	public static Vector<Vector<String>> buildEmpty(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = empty.split("");
		int q = 0;
		int z = 4;
		for(int n=0;n<4;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 4;
			z += 4;
		}
		return matrix;
	}

	public static String block =  	"XXXX" +
									"XXXX" +
									"XXXX" +
									"XXXX";

	public static Vector<Vector<String>> buildBlock(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = block.split("");
		int q = 0;
		int z = 4;
		for(int n=0;n<4;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 4;
			z += 4;
		}
		return matrix;
	}

	public static String fiveXfive =  	"XOOOX" +
										"XXOOX" +
										"XOOOO" +
										"OOOXO" +
										"OOOXO";

	public static Vector<Vector<String>> buildFiveXFive(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = fiveXfive.split("");
		int q = 0;
		int z = 5;
		for(int n=0;n<5;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 5;
			z += 5;
		}
		return matrix;
	}


	public static String test =		"OOOOOO" +
									"OOOXOO" +
									"OXXXOO" +
									"OOOOOO" +
									"OOOOOO" +
									"OOOOOO";

	public static Vector<Vector<String>> buildTest(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = test.split("");
		int q = 0;
		int z = 6;
		for(int n=0;n<6;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 6;
			z += 6;
		}
		return matrix;
	}

	public static String sixXsix =	"OOOOOO" +
									"OOXXOO" +
									"OOOXOO" +
									"OOOOOO" +
									"OXOOOO" +
									"XXXXXX";

	public static Vector<Vector<String>> buildSixXSix(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = sixXsix.split("");
		int q = 0;
		int z = 6;
		for(int n=0;n<6;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 6;
			z += 6;
		}
		return matrix;
	}

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

	public static Vector<Vector<String>> buildTenXTen(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = tenXten.split("");
		int q = 0;
		int z = 10;
		for(int n=0;n<10;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 10;
			z += 10;
		}
		return matrix;
	}


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


	public static Vector<Vector<String>> buildSmile(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = smile.split("");
		int q = 0;
		int z = 25;
		for(int n=0;n<25;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 25;
			z += 25;
		}
		return matrix;
	}

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

	public static Vector<Vector<String>> buildSimple(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = simple.split("");
		int q = 0;
		int z = 10;
		for(int n=0;n<10;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 10;
			z += 10;
		}
		return matrix;
	}

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

	public static Vector<Vector<String>> buildSimpleTwo(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = simpleTwo.split("");
		int q = 0;
		int z = 10;
		for(int n=0;n<10;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 10;
			z += 10;
		}
		return matrix;
	}

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

	public static Vector<Vector<String>> buildSimpleThree(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = simpleThree.split("");
		int q = 0;
		int z = 19;
		for(int n=0;n<19;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 19;
			z += 19;
		}
		return matrix;
	}

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

	public static Vector<Vector<String>> buildSimpleSquare(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = simpleSquare.split("");
		int q = 0;
		int z = 20;
		for(int n=0;n<20;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 20;
			z += 20;
		}
		return matrix;
	}

	public static String testU =	"OOOOOO" +
									"OXOOXO" +
									"OXOOXO" +
									"OXOOXO" +
									"OXXXXO" +
									"OOOOOO";

	public static Vector<Vector<String>> buildTestU(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = testU.split("");
		int q = 0;
		int z = 6;
		for(int n=0;n<6;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 6;
			z += 6;
		}
		return matrix;
	}

	public static String cross ="OOXOO" +
								"OXXXO" +
								"OOXOO" +
								"OOOOO" +
								"OOOOO";

	public static Vector<Vector<String>> buildCross(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = cross.split("");
		int q = 0;
		int z = 5;
		for(int n=0;n<5;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 5;
			z += 5;
		}
		return matrix;
	}

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

	public static Vector<Vector<String>> buildBox(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = box.split("");
		int q = 0;
		int z = 10;
		for(int n=0;n<10;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 10;
			z += 10;
		}
		return matrix;
	}

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

	public static Vector<Vector<String>> buildSingleTest(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = singleTest.split("");
		int q = 0;
		int z = 10;
		for(int n=0;n<10;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 10;
			z += 10;
		}
		return matrix;
	}

	public static String diagonal =	"XOOOO" +
									"OXOOO" +
									"OOXOO" +
									"OOOXO" +
									"OOOOX";

	public static Vector<Vector<String>> buildDiagonal(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = diagonal.split("");
		int q = 0;
		int z = 5;
		for(int n=0;n<5;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 5;
			z += 5;
		}
		return matrix;
	}

	public static String corner =	"OOX" +
									"OXX" +
									"XXX";

	public static Vector<Vector<String>> buildCorner(){
		Vector<Vector<String>> matrix = new Vector<Vector<String>>();
		String[] temp = corner.split("");
		int q = 0;
		int z = 3;
		for(int n=0;n<3;n++){
			Vector<String> v = new Vector<>();
			for(int m=q;m<z;m++){
				String s = temp[m];
				v.add(s);
			}
			matrix.add(v);
			q += 3;
			z += 3;
		}
		return matrix;
	}

} // class
