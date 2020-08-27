package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    @FXML RadioButton  rb1,rb2;

    @FXML
    public void FindingRoute(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("TrainRoutes.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
    @FXML
    public void FindingTrainBetweenStations(ActionEvent event)throws IOException
    {
        Parent blah = FXMLLoader.load(getClass().getResource("FindingTrains.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }



    }




