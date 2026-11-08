package model;
import java.util.List;

public class SlangQuiz {
    private final String prompt;
    private final List<String> options;
    private final int correctAnswer;

    public SlangQuiz(String prompt, List<String> options, int correctAnswer) {
        this.prompt = prompt;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getPrompt() {
        return prompt;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
