import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainMenuController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private AnchorPane background;

    @FXML
    private Button playButton;
    
    @FXML
    private Button quitButton;

    @FXML
    private Slider volumeSlider;

    @FXML
    private ImageView imageView;

    private Media backgroundMusic;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            backgroundMusic = new Media(Main.class.getResource("resources/sounds/theme.wav").toURI().toString());
        } catch (URISyntaxException e) {}
        mediaPlayer = new MediaPlayer(backgroundMusic);
        mediaPlayer.play();

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
    }

    @FXML
    void quitGame(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void startGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("resources/scene/questionsPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}