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

    exports org.fiuba.algoritmos3;
    opens org.fiuba.algoritmos3 to javafx.fxml;
    exports org.fiuba.algoritmos3.controller;
    opens org.fiuba.algoritmos3.controller to javafx.fxml;
    exports org.fiuba.algoritmos3.controller.picker;
    opens org.fiuba.algoritmos3.controller.picker to javafx.fxml;
    exports org.fiuba.algoritmos3.controller.gameMove;
    opens org.fiuba.algoritmos3.controller.gameMove to javafx.fxml;
//    exports org.fiuba.algoritmos3.view;
//    opens org.fiuba.algoritmos3.view to javafx.fxml;
    exports org.fiuba.algoritmos3.view.chooseGameMove;
    opens org.fiuba.algoritmos3.view.chooseGameMove to javafx.fxml;
    exports org.fiuba.algoritmos3.model.pokemon to com.fasterxml.jackson.databind;

    exports org.fiuba.algoritmos3.model;

    exports org.fiuba.algoritmos3.view.battlefield;
    opens org.fiuba.algoritmos3.view.battlefield to javafx.fxml;
    opens org.fiuba.algoritmos3.model to javafx.fxml;
    exports org.fiuba.algoritmos3.model.event;
    opens org.fiuba.algoritmos3.model.event to javafx.fxml;
    exports org.fiuba.algoritmos3.model.event.listener;
    opens org.fiuba.algoritmos3.model.event.listener to javafx.fxml;
    exports org.fiuba.algoritmos3.view.component;
    opens org.fiuba.algoritmos3.view.component to javafx.fxml;

}