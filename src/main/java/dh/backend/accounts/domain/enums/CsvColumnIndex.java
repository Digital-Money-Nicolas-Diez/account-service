package dh.backend.accounts.domain.enums;

import lombok.Getter;

@Getter
public enum CsvColumnIndex {
    CVU(0),
    ALIAS(1);

    private final int value;

    CsvColumnIndex(int value) {
        this.value = value;
    }

}