package org.fiuba.algoritmos3.controller.picker;

import com.github.underscore.U;
import javafx.beans.value.ChangeListener;
import org.fiuba.algoritmos3.GenericObservable;
import org.fiuba.algoritmos3.controller.BaseController;

import java.util.List;

public abstract class PickerController<T> extends BaseController {

    protected List<T> options;
    protected final GenericObservable<T> selection = new GenericObservable<>();

    PickerController() {
        super();
    }

    public void setOptions(List<T> options) {
        this.options = options;
    }

    public void addSelectionListener(ChangeListener<? super T> listener) {
        this.selection.addListener(listener);
    }

}
