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
		
		REXP a =re.eval("a <- 10", true);  //R 명령어 사용
		System.out.println(a.asDouble());  //자바에서 사용할 수 있는 형식으로 변환하는 과정
	}

}
