package dh.backend.accounts.domain.entity;

import dh.backend.accounts.domain.enums.CsvColumnIndex;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public final class AccountFactory {
    private static final List<String> CVU_AND_ALIAS_SOURCE_FILE = readFileStatic();
    private static final String CSV_FILE_PATH = "data/accounts.csv";
    private static final String DEFAULT_ALIAS = "default.alias.value";
    private static final String DEFAULT_CVU = "0000000000000000000000";
    private static final Logger logger = Logger.getLogger(AccountFactory.class.getName());

    private static String getFromFile(CsvColumnIndex field) {
        int index = ThreadLocalRandom.current().nextInt(1, CVU_AND_ALIAS_SOURCE_FILE.size());
        String[] parts = CVU_AND_ALIAS_SOURCE_FILE.get(index).split(",");
        int column = field.getValue();
        return parts[column];
    }

    private static List<String> readFileStatic() {
        try (InputStream is = Account.class.getClassLoader().getResourceAsStream(CSV_FILE_PATH)) {
            if (is == null) throw new IllegalStateException(CVU_AND_ALIAS_SOURCE_FILE + " not found in classpath");
            return new BufferedReader(new InputStreamReader(is)).lines().toList();
        } catch (Exception e) {
            assert logger != null;
            logger.warning("Error reading " + CSV_FILE_PATH + ":" + e.getMessage());
            return List.of(DEFAULT_CVU, DEFAULT_ALIAS);
        }
    }

    public static Account create(UUID user) {
        return AccountFactory.create(user, null,null,null);
    }
    public static Account create(UUID user, Float balance, String cvu, String alias) {
        String finalCvu = cvu != null ? cvu : getFromFile(CsvColumnIndex.CVU);
        String finalAlias = alias != null ? alias : getFromFile(CsvColumnIndex.ALIAS);
        return new Account(user, finalCvu, finalAlias, balance);
    }

}
