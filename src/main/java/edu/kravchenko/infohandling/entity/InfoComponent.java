package edu.kravchenko.infohandling.entity;

import java.util.List;

public interface Component {
    void add(Component component);

    void remove(Component component);

    List<Component> getChildren();

    ComponentType getType();
}
