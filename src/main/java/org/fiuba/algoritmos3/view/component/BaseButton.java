package org.fiuba.algoritmos3.view.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.fiuba.algoritmos3.PokemonApp;

import java.io.IOException;
import java.net.URL;

public class BaseButton extends Button {
    @FXML
    private ImageView imageView;
    private final StringProperty defaultImageUrl = new SimpleStringProperty("images/default-button.png");
    private final StringProperty selectedImageUrl = new SimpleStringProperty("images/default-button-selected.png");

    private Image defaultImage;
    private Image selectedImage;

    private boolean isSelected;

    public BaseButton() {
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("views/components/base-button.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        refreshDefaultImage();
        refreshSelectedImage();

        // auto resize image with button
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(this.prefWidthProperty());
        imageView.fitHeightProperty().bind(this.prefHeightProperty());

        this.setOnMouseEntered(e -> {
            imageView.setImage(selectedImage);
            isSelected = true;
        });
        this.setOnMouseExited(e -> {
            imageView.setImage(defaultImage);
            isSelected = false;
        });
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl.get();
    }

    public StringProperty defaultImageUrlProperty() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl.set(defaultImageUrl);
        refreshDefaultImage();
    }

    public String getSelectedImageUrl() {
        return selectedImageUrl.get();
    }

    public StringProperty selectedImageUrlProperty() {
        return selectedImageUrl;
    }

    public void setSelectedImageUrl(String selectedImageUrl) {
        this.selectedImageUrl.set(selectedImageUrl);
        refreshSelectedImage();
    }

    private void refreshDefaultImage() {
        URL resource = PokemonApp.class.getResource(defaultImageUrl.getValue());
        assert resource != null;
        defaultImage = new Image(resource.toExternalForm(), imageView.getFitWidth(), imageView.getFitHeight(), true, false);

        if (!isSelected)
            imageView.setImage(defaultImage);
    }

    private void refreshSelectedImage() {
        URL resource = PokemonApp.class.getResource(selectedImageUrl.getValue());
        assert resource != null;
        selectedImage = new Image(resource.toExternalForm(), imageView.getFitWidth(), imageView.getFitHeight(), true, false);

        if (isSelected)
            imageView.setImage(selectedImage);
    }
}
