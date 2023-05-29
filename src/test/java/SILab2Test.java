import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SILab2Test {
    private static List<User> users;

    @BeforeAll
    static void fillList(){
        users = new ArrayList<>();
        users.add(new User("testuser0","testpass0","testmail0"));
        users.add(new User("testuser1","testpass1","testmail1"));
        users.add(new User(null,"12345678&","mail@gmail.com"));
        users.add(new User("asd","asd132",null));
        users.add(new User("asd","asd132","email"));
        users.add(new User("asd","asd 13245","email"));
        users.add(new User("asd","asd13245","email"));
    }
    @Test
    void EveryBranch(){
        assertAll("Every Branch",
                () -> assertFalse(SILab2.function(new User(null, "12345678&", "mail@gmail.com"), users)),
                () -> assertFalse(SILab2.function(new User("asd","asd132","email"),users)),
                () -> assertFalse(SILab2.function(new User("asd","asd 13245","email"),users)),
                () -> assertFalse(SILab2.function(new User("asd","asd13245","email"),users)),
                () -> assertThrows(RuntimeException.class,()->SILab2.function(new User("asd","asd132",null),users)));
    }
    @Test
    void MultipleConditions(){
        assertAll("Multiple conditions",
                () -> assertThrows(RuntimeException.class,()->SILab2.function(null,users)),
                () -> assertThrows(RuntimeException.class,()->SILab2.function(new User("asd",null,"anything"),users)),
                () -> assertThrows(RuntimeException.class,()->SILab2.function(new User("asd","not null",null),users)),
                () -> assertDoesNotThrow(()->SILab2.function(new User("asd","not null","not null"),users)));
    }
}
