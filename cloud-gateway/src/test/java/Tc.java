import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * @author yuantongqin
 * 2020-05-08
 */
public class Tc {


    @Test
    public void sa(){
        System.out.println("a");

        String s = UUID.randomUUID().toString();
        System.out.println(s);
        String replace = s.replace("-", "");
        System.out.println("==="+replace);

    }

    @Test
    public void aa(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
        String encode = passwordEncoder.encode("123");

        System.out.println("ll="+encode);
    }
}
