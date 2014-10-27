package mw.ninequiz.src;

import lombok.Data;

/**
 * 9問クイズのデータモデル.
 */
@Data
public class NineQuizModel {

    private String startMessage = "Nine Quiz Start!";

    private String questionNumberText = "Number{num}:";

    private String questionChoiceNumberText = "Q{num}.";

    private String questionIsCorrect = "That's right!";

    private String questionIsIncorrect = "That's wrong...";

    private String simpleCorrect = "○";

    private String simpleIncorrect = "☓";
}
