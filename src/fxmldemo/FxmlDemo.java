package fxmldemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aristo_PC
 */
public class FxmlDemo extends Application {
    public Connection connection;
    public Statement statement;
    
    public void connectDB() {
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=ProjectDB ; selectMethod=cursor", "sa", "123456");

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
