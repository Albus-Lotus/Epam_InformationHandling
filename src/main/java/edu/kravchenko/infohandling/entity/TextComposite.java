package edu.kravchenko.infohandling.entity;

import edu.kravchenko.infohandling.exception.TextException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TextComposite implements InfoComponent {
    private List<InfoComponent> infoComponents = new ArrayList<>();
    private ComponentType type;

    public TextComposite(ComponentType type) throws TextException {
        EnumSet<ComponentType> compositeTypes = EnumSet.range(ComponentType.TEXT, ComponentType.WORD);
        if (!compositeTypes.contains(type)) {
            throw new TextException("Invalid text composite type");
        }
        this.type = type;
    }

    @Override
    public void add(InfoComponent infoComponent) {
        infoComponents.add(infoComponent);
    }

    @Override
    public void remove(InfoComponent infoComponent) {
        infoComponents.remove(infoComponent);
    }

    @Override
    public List<InfoComponent> getChildren() {
        return infoComponents;
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
        return infoComponents.equals(that.infoComponents) && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + infoComponents.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String delimiter = type.getDelimiter();
        for (InfoComponent infoComponent : infoComponents) {
            sb.append(infoComponent.toString()).append(delimiter);
        }
        return sb.toString();
    }
}
