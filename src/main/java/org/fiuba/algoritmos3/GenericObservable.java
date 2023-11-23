package org.fiuba.algoritmos3;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

public class GenericObservable<T> implements ObservableValue<T> {

    private T value;
    private final List<ChangeListener<? super T>> changeListeners = new ArrayList<>();
    private final List<InvalidationListener> invalidationListeners = new ArrayList<>();

    public void setValue(T newValue) {
        this.value = newValue;
        notifyChangeListeners(null, newValue);
        notifyInvalidationListeners();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void addListener(ChangeListener<? super T> listener) {
        changeListeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super T> listener) {
        changeListeners.remove(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListeners.remove(listener);
    }

    private void notifyChangeListeners(T oldValue, T newValue) {
        for (ChangeListener<? super T> listener : changeListeners) {
            listener.changed(this, oldValue, newValue);
        }
    }

    private void notifyInvalidationListeners() {
        for (InvalidationListener listener : invalidationListeners) {
            listener.invalidated(this);
        }
    }
}