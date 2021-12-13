package edu.kravchenko.infohandling.entity;

public enum ComponentType {
    TEXT("\n\t"),
    PARAGRAPH(" "),
    SENTENCE(" "),
    LEXEME(""),
    WORD(""),
    SYMBOL(""),
    PUNCTUATION("");

    private final String delimiter;

    ComponentType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
