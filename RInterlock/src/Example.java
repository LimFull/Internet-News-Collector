import javax.swing.SwingUtilities;

import org.rosuda.JRI.*;


public class Example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Creating Rengine (with arguments)");
		String[] Rargs = {"--vanilla"};
		Rengine re = new Rengine(Rargs, false, null);
		System.out.println("Rengine created, waiting for R");
		
		if(!re.waitForR()) {
			System.out.println("Cannot load R");
			return;
		}
		
		 SwingUtilities.invokeLater(new Runnable()
	        {
	            @Override
	            public void run()
	            {
	            	Polrecord Pol = new Polrecord();
	            }
	        });
		
		System.out.println();
		re.end();
		System.out.println("Bye.");
	}

}
