package viewmodel;

import model.Model;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer {

    private Model model;

    public ViewModel(Model model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
