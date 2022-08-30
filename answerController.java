import java.io.IOException;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class answerController {

    @FXML
    private Button answerA;

    @FXML
    private Button answerB;

    @FXML
    private Button answerC;

    @FXML
    private Button answerD;

    @FXML
    private Text questionText;

    @FXML ActionEvent event;

    @FXML
    private Text questionTitle;

    private Question questionChoice;
    private int currentScore;

    public void initData(Question question) {
        questionChoice = question;
        questionTitle.setText(questionChoice.getQuestionType() + " " + questionChoice.getPointValue());
        questionText.setText(questionChoice.getQuestion());

        answerA.setText(questionChoice.getChoices()[0]);
        answerB.setText(questionChoice.getChoices()[1]);
        answerC.setText(questionChoice.getChoices()[2]);
        answerD.setText(questionChoice.getChoices()[3]);
    }

    @FXML
    public void checkAnswer(ActionEvent event) throws URISyntaxException, IOException {
        Button checkAnswerButton = (Button)event.getSource();
        String userAnswerID = checkAnswerButton.getId();
        String userAnswer = userAnswerID.substring(6, 7);

        System.out.println("Correct answer: " + questionChoice.getAnswer());
        System.out.println("User answer: " + userAnswer);
        
        if (userAnswer.toLowerCase().equals(questionChoice.getAnswer().toLowerCase()))
        {
            System.out.println("Correct!");
            correctAnswer();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/scene/correct.fxml"));
            Parent viewQuestion = loader.load();

            Scene viewQuestionScene = new Scene(viewQuestion);

            correctIncorrectController controller = loader.getController();
            controller.initScore(questionChoice.getPointValue(), currentScore);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(viewQuestionScene);
            window.show();
        }
        else
        {
            System.out.println("Wrong!");
            wrongAnswer();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/scene/incorrect.fxml"));
            Parent viewQuestion = loader.load();

            Scene viewQuestionScene = new Scene(viewQuestion);

            correctIncorrectController controller = loader.getController();
            controller.initScore(0, currentScore);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(viewQuestionScene);
            window.show();
        }
    }

    public void keepScore(int totalScore) {
        currentScore = totalScore;
    }

    public void correctAnswer() throws URISyntaxException, IOException 
    {
        AudioClip buzzer = new AudioClip(Main.class.getResource("resources/sounds/correct.wav").toURI().toString()); 
        buzzer.setVolume(0.1);
        buzzer.play();
    }

    public void wrongAnswer() throws URISyntaxException, IOException {
        int randomInt = (int)Math.floor(Math.random()*(10-1+1)+1);
        if(randomInt < 7) {
            AudioClip buzzer = new AudioClip(Main.class.getResource("resources/sounds/buzzer.wav").toURI().toString());
            buzzer.setVolume(0.1);
            buzzer.play();
        }
        else {
            AudioClip buzzer = new AudioClip(Main.class.getResource("resources/sounds/bruh.wav").toURI().toString());
            buzzer.setVolume(0.1);
            buzzer.play();
        }
    }
}