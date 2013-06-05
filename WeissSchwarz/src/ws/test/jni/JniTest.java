package ws.test.jni;
/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 8:32:37 PM - May 4, 2013
 * Description :
 *   
 */
public class JniTest {
	
	static {
		System.load("WeissSchwarzTest");
	}
	
	public native int APlusB(int a, int b);
	
	public static void main(String[] args) {
	}

}
