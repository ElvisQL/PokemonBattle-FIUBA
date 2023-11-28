package org.fiuba.algoritmos3.controller.picker;

import javafx.beans.value.ChangeListener;
import org.fiuba.algoritmos3.GenericObservable;
import org.fiuba.algoritmos3.controller.BaseController;

import java.util.List;

public abstract class PickerController<T> extends BaseController {

    private List<T> options;
    protected final GenericObservable<T> selection = new GenericObservable<>();

    PickerController() {
        super();
    }

    public void setOptions(List<T> options) {
        this.options = options;
        updateView();
    }

    protected List<T> getOptions() {
        return this.options.stream().filter(this::filterFunction).toList();
    }

    protected boolean filterFunction(T option) {
        return true;
    }

    public void addSelectionListener(ChangeListener<? super T> listener) {
        this.selection.addListener(listener);
    }

    protected void updateView() {
        // Do nothing
    }

}
