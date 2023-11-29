package org.fiuba.algoritmos3.controller.gameMove;

import javafx.beans.value.ObservableValue;
import org.fiuba.algoritmos3.controller.picker.PickerController;
import org.fiuba.algoritmos3.controller.picker.SkillPickerController;
import org.fiuba.algoritmos3.model.move.UseSkill;
import org.fiuba.algoritmos3.model.move.builder.UseSkillBuilder;
import org.fiuba.algoritmos3.model.pokemon.skills.ConcreteSkill;

import java.net.URL;
import java.util.ResourceBundle;

public class UseSkillController extends GameMoveController<UseSkill, UseSkillBuilder> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        builder = new UseSkillBuilder();
        loadItemPicker();
    }

    private void loadItemPicker() {
        PickerController<ConcreteSkill> controller = loadPicker(SkillPickerController.class, gameAPI.currentPlayer().getCurrentPokemon().getSkills());
        controller.addSelectionListener(this::onSkillPicked);
        controller.addBackListener((_a, _b, _c) -> loadChooseGameMove());
    }

    private void onSkillPicked(ObservableValue<?> _obs, ConcreteSkill oldItem, ConcreteSkill newItem) {
        builder.setSkill(newItem);
        executeGameMove();
    }
}
