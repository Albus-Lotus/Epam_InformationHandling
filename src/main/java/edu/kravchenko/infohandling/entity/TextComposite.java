package edu.kravchenko.infohandling.entity;

import edu.kravchenko.infohandling.exception.TextException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TextComposite implements Component {
    private List<Component> components = new ArrayList<>();
    private ComponentType type;

    public TextComposite(ComponentType type) throws TextException {
        EnumSet<ComponentType> compositeTypes = EnumSet.range(ComponentType.TEXT, ComponentType.WORD);
        if (!compositeTypes.contains(type)) {
            throw new TextException("Invalid text composite type");
        }
        this.type = type;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public List<Component> getChildren() {
        return components;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return components.equals(that.components) && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + components.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String delimiter = type.getDelimiter();
        for (Component component : components) {
            sb.append(component.toString()).append(delimiter);
        }
        return sb.toString();
    }
}
