import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class correctIncorrectController {

    @FXML
    private AnchorPane correctScreen;

    @FXML
    private Button nextButton;

    private int currentScore;

    public void initScore(int score, int totalScore) {
        currentScore = totalScore;
        currentScore += score;
    }

    @FXML
    public void nextScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("resources/scene/questionsPage.fxml"));
        Parent viewQuestion = loader.load();

        Scene viewQuestionScene = new Scene(viewQuestion);

        questionTestController controller = loader.getController();
        controller.updateScore(currentScore);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewQuestionScene);
        window.show();
    }
}