import Views.MainView;

import Models.DAO.DataBaseConnection;

public class Main {
    public static void main(String[] args) {
        // check database connection first
        DataBaseConnection.main(args);
        MainView.runApplication();
    }
}