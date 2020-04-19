import Model.DB.SQLOperations;
import Controller.Controller;
import View.MainView;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.setVisible(true);

        Controller controller = new Controller(mainView);
        SQLOperations sqlOperations = new SQLOperations();

    }
}

