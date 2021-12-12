package edu.kravchenko.infohandling.entity;

import java.util.List;

public interface InfoComponent {
    void add(InfoComponent infoComponent);

    void remove(InfoComponent infoComponent);

    List<InfoComponent> getChildren();

    ComponentType getType();
}
