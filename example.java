import org.rosuda.JRI.*;
public class example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Creating Rengine (with arguments)");
		String[] Rargs = {"--vanilla"};
		Rengine re =new Rengine(Rargs, false, null);
		System.out.println("Rengine created, waiting for R");
		
		if(!re.waitForR()) {
			System.out.println("Loading R engine was failed");
			return;
		}
		
		REXP a =re.eval("a <- 10", true);
		System.out.println(a.asDouble());
	}

}
