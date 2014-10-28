package mw.ninequiz.src;

import lombok.Data;

/**
 * 9問クイズのデータモデル.
 */
@Data
public class NineQuizData {

    private String startMessage = "NineQuiz スタート!";

    private String questionNumberText = "問{num}:";

    private String questionChoiceNumberText = "{num}.";

    private String questionIsCorrect = "正解！";

    private String questionIsIncorrect = "不正解･･････。";

    private String simpleCorrect = "○";

    private String simpleIncorrect = "☓";
}
