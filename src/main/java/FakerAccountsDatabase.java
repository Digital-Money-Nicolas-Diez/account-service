import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import net.datafaker.Faker;

public class FakerAccountsDatabase {
    private static String fakeCvu() {
        long number = ThreadLocalRandom.current()
                .nextLong(1_000_000_000_000_000_000L);
        return String.format("%022d", number);
    }

    public static void main(String[] args) throws IOException {
        Faker faker = new Faker(Locale.ENGLISH);

        try (FileWriter writer = new FileWriter("accounts.csv")) {
            writer.write("cvu,alias\n");

            for (int i = 0; i < 100; i++) {
                String alias = faker.lorem().word() + "." +
                        faker.lorem().word() + "." +
                        faker.lorem().word();

                writer.write(fakeCvu() + "," + alias + "\n");
            }
        }
    }
}
