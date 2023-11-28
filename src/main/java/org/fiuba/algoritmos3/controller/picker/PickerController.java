package org.fiuba.algoritmos3.controller.picker;

import javafx.beans.value.ChangeListener;
import org.fiuba.algoritmos3.GenericObservable;
import org.fiuba.algoritmos3.controller.BaseController;

import java.util.List;
import java.util.function.Predicate;

public abstract class PickerController<T> extends BaseController {

    private List<T> options;
    protected final GenericObservable<T> selection = new GenericObservable<>();
    protected final GenericObservable<Boolean> back = new GenericObservable<>();

    protected Predicate<T> filterFunction = (a) -> true;

    PickerController() {
        super();
    }

    public void setOptions(List<T> options) {
        this.options = options;
        updateView();
    }

    protected List<T> getOptions() {
        return this.options.stream().filter(filterFunction).toList();
    }

    public void setFilterFunction(Predicate<T> filterFunction) {
        this.filterFunction = filterFunction;
    }

    public void addSelectionListener(ChangeListener<? super T> listener) {
        this.selection.addListener(listener);
    }

    public void addBackListener(ChangeListener<Boolean> listener) {
        this.back.addListener(listener);
    }

    protected void updateView() {
        // Do nothing
    }

}
