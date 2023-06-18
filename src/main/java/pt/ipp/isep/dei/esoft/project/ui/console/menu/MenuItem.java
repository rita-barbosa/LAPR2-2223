package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import javafx.application.Application;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class MenuItem {
    private final String description;
    private final Runnable ui;
    private final Application gui;



    public MenuItem(String description, Runnable ui) {
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        }
        if (Objects.isNull(ui)) {
            throw new IllegalArgumentException("MenuItem does not support a null UI.");
        }

        this.description = description;
        this.ui = ui;
        this.gui = null;
    }

    public MenuItem(String description, Application gui) {
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        }
        if (Objects.isNull(gui)) {
            throw new IllegalArgumentException("MenuItem does not support a null UI.");
        }
        this.description = description;
        this.ui = null;
        this.gui = gui;
    }

    public void run() {
        this.ui.run();
    }

    public boolean hasDescription(String description) {
        return this.description.equals(description);
    }

    public String toString() {
        return this.description;
    }

    public Runnable getUi() {
        return ui;
    }

    public Application getGui() {
        return gui;
    }
}
