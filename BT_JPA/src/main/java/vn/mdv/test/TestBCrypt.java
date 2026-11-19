package vn.mdv.test;
import org.mindrot.jbcrypt.BCrypt;

public class TestBCrypt {
    public static void main(String[] args) {
        String password = "123456";

        String hashedPassword = BCrypt.hashpw(
            password,
            BCrypt.gensalt(10)
        );

        System.out.println(hashedPassword);
    }
}