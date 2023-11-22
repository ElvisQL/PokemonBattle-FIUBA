module org.fiuba.algoritmos3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.github.underscore;
    requires org.jetbrains.annotations;
    requires org.jline.terminal;
    requires javatuples;
    requires com.fasterxml.jackson.databind;
    requires consoleui;
    requires jansi;

    opens org.fiuba.algoritmos3 to javafx.fxml;
    exports org.fiuba.algoritmos3;
    exports org.fiuba.algoritmos3.controller;
    opens org.fiuba.algoritmos3.controller to javafx.fxml;
    exports org.fiuba.algoritmos3.view;
    opens org.fiuba.algoritmos3.view to javafx.fxml;
}