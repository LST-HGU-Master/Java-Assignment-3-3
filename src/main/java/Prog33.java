public class Prog33 {

	public static void main(String[] args) {
		for (int i=0; i<7; i++) {
			int j;
			for(j=0; j<=i; j++) {
				System.out.print("＊");
			}
			for(; j<7; j++) {
				System.out.print("　");
			}

			System.out.println("");
		}
	}

}

