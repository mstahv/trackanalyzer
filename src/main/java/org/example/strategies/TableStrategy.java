package org.example.strategies;

import com.vaadin.ui.Component;
import java.io.Serializable;

public interface TableStrategy extends Serializable {
    public Component getTable();
}
