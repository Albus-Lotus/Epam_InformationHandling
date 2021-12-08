package edu.kravchenko.infohandling.entity;

import edu.kravchenko.infohandling.exception.TextException;

import java.util.EnumSet;
import java.util.List;

public class TextSymbol implements Component {
    private ComponentType type;
    private char symbol;

    public TextSymbol(ComponentType type, char symbol) throws TextException {
        EnumSet<ComponentType> symbolTypes = EnumSet.range(ComponentType.SYMBOL, ComponentType.PUNCTUATION);
        if (!symbolTypes.contains(type)) {
            throw new TextException("Invalid text leaf type");
        }
        this.type = type;
        this.symbol = symbol;
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("Unsupported operation add on symbol");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("Unsupported operation remove on symbol");
    }

    @Override
    public List<Component> getChildren() {
        throw new UnsupportedOperationException("Unsupported operation get children on symbol");
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSymbol that = (TextSymbol) o;
        return symbol == that.symbol && getType() == that.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + type.hashCode();
        result = 31 * result + Character.hashCode(symbol);
        return result;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
