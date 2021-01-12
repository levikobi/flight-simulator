package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer {

    private Model model;

    public StringProperty ip;
    public StringProperty port;
    public StringProperty autopilotScript;

    public ViewModel(Model model) {
        this.model = model;

        ip = new SimpleStringProperty();
        port = new SimpleStringProperty();
        autopilotScript = new SimpleStringProperty();
    }

    public void connectToFlightGear() {
        model.connect(ip.get(), Integer.parseInt(port.get()));

        setChanged();
        notifyObservers("Connected");
    }

    public void runAutopilotScript() {
        model.runAutopilotScript(autopilotScript.get().split("\n"));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
