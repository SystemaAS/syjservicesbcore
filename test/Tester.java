import java.util.ArrayList;
import java.util.List;


public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fromAvd = "1";
		String toAvd = "10";
		
		int lLimit = 0;
		int uLimit = 0;
		try{
			lLimit = Integer.valueOf(fromAvd);
			uLimit = Integer.valueOf(toAvd);
			for (Integer x = lLimit; x<=uLimit; x++){
				//list.add(x);
				System.out.println(x.toString());
			}
			
		}catch(Exception e){
			
		}

	}

}
