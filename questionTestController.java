import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class questionTestController implements Initializable {
    @FXML
    private Button AcarButton1;

    @FXML
    private Button AdanielButton3;

    @FXML
    private Button AgamingButton1;

    @FXML
    private Button AgamingButton2;

    @FXML
    private Button BcarButton3;

    @FXML
    private Button BdanielButton1;

    @FXML
    private Button BfoodButton1;

    @FXML
    private Button BgamingButton3;

    @FXML
    private Button CcarButton2;

    @FXML
    private Button CcarButton4;

    @FXML
    private Button CdanielButton2;

    @FXML
    private Button CfoodButton2;

    @FXML
    private Button CfoodButton3;

    @FXML
    private Button CgamingButton4;

    @FXML
    private Button DdanielButton4;

    @FXML
    private Button DfoodButton4;

    private int totalScore;

    @FXML
    private Text scoreCounter;

    public void updateScore(int currentScore) throws IOException{
        totalScore = currentScore;
        scoreCounter.setText("Score: " + totalScore); 
    }

    @FXML 
    public Question loadQuestion(ActionEvent event) throws IOException, URISyntaxException {
        Button buttonID = (Button)event.getSource();
        String buttonIDID = buttonID.getId();

        System.out.println("Button ID Retrived: " + buttonIDID);

        String questionType = "";
        Question questionChoice = new Question();
        
        if(buttonIDID.substring(buttonIDID.length() - 1).equals("1"))
        {    
            questionType = buttonIDID.substring(1,2);
            
            if (questionType.equals("c"))
            {   
                questionType = "Car";
                questionChoice = getQuestion(0, buttonIDID, 100, questionType);  
            }
            else if (questionType.equals("g"))
            {   
                questionType = "Gaming";
                questionChoice = getQuestion(4, buttonIDID, 100, questionType);  
            }
            else if (questionType.equals("f"))
            {   
                questionType = "Food";
                questionChoice = getQuestion(8, buttonIDID, 100, questionType);  
            }
            else if (questionType.equals("d"))
            {   
                questionType = "Daniel";
                questionChoice = getQuestion(12, buttonIDID, 100, questionType);  
            }
        }
        else if(buttonIDID.substring(buttonIDID.length() - 1).equals("2"))
        {
            questionType = buttonIDID.substring(1,2);
            
            if (questionType.equals("c"))
            {   
                questionType = "Car";
                questionChoice = getQuestion(1, buttonIDID, 200, questionType);  
            }
            else if (questionType.equals("g"))
            {   
                questionType = "Gaming";
                questionChoice = getQuestion(5, buttonIDID, 200, questionType);  
            }
            else if (questionType.equals("f"))
            {   
                questionType = "Food";
                questionChoice = getQuestion(9, buttonIDID, 200, questionType);  
            }
            else if (questionType.equals("d"))
            {   
                questionType = "Daniel";
                questionChoice = getQuestion(13, buttonIDID, 200, questionType);  
            }
        }
        else if(buttonIDID.substring(buttonIDID.length() - 1).equals("3"))
        {
            questionType = buttonIDID.substring(1,2);
            
            if (questionType.equals("c"))
            {   
                questionType = "Car";
                questionChoice = getQuestion(2, buttonIDID, 300, questionType);  
            }
            else if (questionType.equals("g"))
            {   
                questionType = "Gaming";
                questionChoice = getQuestion(6, buttonIDID, 300, questionType);  
            }
            else if (questionType.equals("f"))
            {   
                questionType = "Food";
                questionChoice = getQuestion(10, buttonIDID, 300, questionType);  
            }
            else if (questionType.equals("d"))
            {   
                questionType = "Daniel";
                questionChoice = getQuestion(14, buttonIDID, 300, questionType);  
            }
        }
        else if(buttonIDID.substring(buttonIDID.length() - 1).equals("4"))
        {
            questionType = buttonIDID.substring(1,2);
            
            if (questionType.equals("c"))
            {   
                questionType = "Car";
                questionChoice = getQuestion(3, buttonIDID, 400, questionType);  
            }
            else if (questionType.equals("g"))
            {   
                questionType = "Gaming";
                questionChoice = getQuestion(7, buttonIDID, 400, questionType);  
            }
            else if (questionType.equals("f"))
            {   
                questionType = "Food";
                questionChoice = getQuestion(11, buttonIDID, 400, questionType);  
            }
            else if (questionType.equals("d"))
            {   
                questionType = "Daniel";
                questionChoice = getQuestion(15, buttonIDID, 400, questionType);  
            }
        }
        return questionChoice;
    }

    public void changeSceneToQuestion(ActionEvent event) throws IOException, URISyntaxException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("resources/scene/genericQuestion.fxml"));
        Parent viewQuestion = loader.load();

        Scene viewQuestionScene = new Scene(viewQuestion);

        answerController controller = loader.getController();
        controller.initData(loadQuestion(event));
        controller.keepScore(totalScore);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewQuestionScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scoreCounter.setText("Score: " + totalScore); 
    }

    public Question getQuestion(int questionLineNumber, String buttonIDID, int score, String questionType) throws IOException, URISyntaxException {
        System.out.println("Question Line Number: " + questionLineNumber);
        URL url = Main.class.getResource("resources/QuestionsPool.txt");
        Path questionPool = Paths.get(url.toURI());
        String line = Files.readAllLines(questionPool).get(questionLineNumber);
        String question = line.substring(0, line.indexOf("@"));

        String answer = line.substring(line.indexOf("@") + 1);
    
        String[] answerChoices = answer.split("#");
        System.out.println("Button IDID: " + buttonIDID);
        String correctAns = buttonIDID.substring(0,1);

        return new Question(questionType, correctAns, answerChoices, question, score);
    }
}