import org.junit.*;
import ru.sbt.javaschool.Barbershop;

public class BarberShopTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing Salon.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void testSalon() throws InterruptedException {
        Barbershop barbershop = new Barbershop();
        barbershop.barbershopStart(3,10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSalonException() throws InterruptedException {
        Barbershop barbershop = new Barbershop();
        barbershop.barbershopStart(-1,6);
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Testing completed");
    }
}
