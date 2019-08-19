package sunyu.demo.security.manager;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Tests {

    @Test
    public void t1() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("123456");
        System.out.println(result);
        System.out.println(encoder.matches("123456", result));
        result = encoder.encode("123456");
        System.out.println(result);
        System.out.println(encoder.matches("123456", result));
        result = encoder.encode("123456");
        System.out.println(result);
        System.out.println(encoder.matches("123456", result));
    }

}
