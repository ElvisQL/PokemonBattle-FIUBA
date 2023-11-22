package org.fiuba.algoritmos3.view;

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

    public BaseButton() {
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApp.class.getResource("base-button.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        refreshDefaultImage();
        refreshSelectedImage();

        // TODO auto resize image with button
//        imageView.fitWidthProperty().bind(this.widthProperty());
//        imageView.fitHeightProperty().bind(this.heightProperty());

        this.setOnMouseEntered(e -> imageView.setImage(selectedImage));
        this.setOnMouseExited(e -> imageView.setImage(defaultImage));

        imageView.fitWidthProperty().set(this.getPrefWidth());
        imageView.fitHeightProperty().set(this.getPrefHeight());
    }

    @Override
    public void setPrefSize(double v, double v1) {
        super.setPrefSize(v, v1);

        imageView.fitWidthProperty().set(this.getPrefWidth());
        imageView.fitHeightProperty().set(this.getPrefHeight());
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
        defaultImage = new Image(resource.toExternalForm());
        if (!isSelected()) {
            imageView.setImage(defaultImage);
        }
    }

    private void refreshSelectedImage() {
        URL resource = PokemonApp.class.getResource(selectedImageUrl.getValue());
        selectedImage = new Image(resource.toExternalForm());
        if (isSelected()) {
            imageView.setImage(selectedImage);
        }
    }

    private boolean isSelected() {
        return imageView.getImage() != null && imageView.getImage().equals(selectedImage);
    }
}
