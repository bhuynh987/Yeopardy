public class Question {
    private String answer;
    private String [] choices;
    private String question;
    private int pointValue;
    private String typeQuestion;

    public Question()
    {
        this.answer = "";
        this.choices = new String[4];
        this.question = "";
        this.pointValue = 0;
        this.typeQuestion = "";
    }

    public Question(String qType, String correctAnswer, String[] answerChoices, String givenQuestion, int points) {
        this.answer = correctAnswer;
        this.choices = answerChoices;
        this.question = givenQuestion;
        this.pointValue = points;
        this.typeQuestion = qType;
    }

    public String getAnswer()
    {
        return this.answer;
    }

    public String[] getChoices()
    {
        return this.choices;
    }

    public String getQuestion()
    {
        return this.question;
    }

    public int getPointValue()
    {
        return this.pointValue;
    }

    public String getQuestionType() {
        return this.typeQuestion;
    }
}