package org.fiuba.algoritmos3.controller;

import com.github.underscore.U;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.fiuba.algoritmos3.controller.picker.PickerController;

import java.io.IOException;
import java.util.List;

public abstract class PickerWrapperController extends BaseController {

    @FXML
    protected AnchorPane rootPane;

    protected PickerController<?> previousController;
    protected PickerController<?> currentController;

    protected <S> PickerController<S> loadPicker(Class<? extends PickerController<S>> clazz, List<S> options) throws RuntimeException {
        String viewUrl = "views/picker/" + U.kebabCase(clazz.getSimpleName().replace("Controller", "")) + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getResource(viewUrl));

        Node node;
        try {
            node = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        PickerController<S> pickerController = fxmlLoader.getController();
        pickerController.setOptions(options);

        rootPane.getChildren().removeAll();
        rootPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);

        previousController = currentController;
        currentController = pickerController;

        return pickerController;
    }

}
