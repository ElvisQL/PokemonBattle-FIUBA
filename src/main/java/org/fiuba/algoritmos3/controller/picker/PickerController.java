package org.fiuba.algoritmos3.controller.picker;

import javafx.beans.value.ChangeListener;
import org.fiuba.algoritmos3.GenericObservable;
import org.fiuba.algoritmos3.controller.BaseController;

import java.util.List;
import java.util.function.Predicate;

public abstract class PickerController<T> extends BaseController {

    private List<T> options;
    protected Predicate<? super T> filterFunction;
    protected final GenericObservable<T> selection = new GenericObservable<>();

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

    public void filter(Predicate<? super T> filterFunction) {
        this.filterFunction = filterFunction;
    }

    public void addSelectionListener(ChangeListener<? super T> listener) {
        this.selection.addListener(listener);
    }

    protected void updateView() {
        throw new RuntimeException("NotImplementedException");
    }

}
