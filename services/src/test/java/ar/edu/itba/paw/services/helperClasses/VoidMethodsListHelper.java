package ar.edu.itba.paw.services.helperClasses;

import java.util.AbstractList;

public class VoidMethodsListHelper extends AbstractList<String> {
    @Override
    public String get(int index) {
        return this.get(index);
    }

    @Override
    public int size() {
        return this.size();
    }

    public void addMessage(String methodExecuted) {
        // no-op
    }
}
