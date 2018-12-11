import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class UserTest {


    @Test
    public void userPasword(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String topse8610 = bCryptPasswordEncoder.encode("topse8610");
        System.out.println(topse8610);
    }
}
