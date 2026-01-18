package dh.backend.accounts.domain.entity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import lombok.Getter;

@Getter
public class Account {
    private int MIN_LENGTH_CVU = 22;
    private static final String ALIAS_FORMAT = "^[a-z]+\\.[a-z]+\\.[a-z]+$";
    private UUID user;
    private final Logger logger = Logger.getLogger(Account.class.getName());
    String cvu;
    String alias;

    public Account(UUID user, String cvu, String alias) {

        if (cvu != null && cvu.length() != MIN_LENGTH_CVU) {
            throw new IllegalArgumentException("CVU must be 22 characters long");
        }

        if (alias != null && !alias.matches(Account.ALIAS_FORMAT)) {
            throw new IllegalArgumentException("Alias format is invalid");
        }

        this.user = user;
        this.cvu = cvu != null ? cvu : createCvu();
        this.alias = alias != null ? alias : createAlias();
    }

    private String createAlias() {
        String[] data = data();
        return data[1];
    }

    private String createCvu() {
        String[] data = data();
        return data[0];
    }

    private String[] data() {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("data/accounts.csv")) {

            if (is == null) {
                throw new IllegalStateException("accounts.csv not found in classpath");
            }

            List<String> lines = new BufferedReader(
                    new InputStreamReader(is))
                    .lines()
                    .toList();

            int index = ThreadLocalRandom.current().nextInt(1, lines.size());
            String[] parts = lines.get(index).split(",");

            return new String[] { parts[0], parts[1] };

        } catch (Exception e) {
            logger.warning("Error reading accounts.csv: " + e.getMessage());
            return new String[] { "0000000000000000000000", "default.alias.value" };
        }
    }

}
