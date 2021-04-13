import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class SignupTesting {

	@Test
	void test() {

	
		Reg ltest =new Reg();
		int result=ltest.userSignup("ss", "ss", "ss", "ss", "ss");
		assertEquals(1,result);
	}

}
