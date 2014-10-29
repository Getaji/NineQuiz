package mw.ninequiz.src;

import lombok.Data;

/**
 * クイズロジックに用いるデータのコンテナクラスです。
 * 主に{@link QuizLogic}によって使用されます。
 */
@Data
public class QuizLogicData {

    private String startMessage = "NineQuiz スタート!";

    private String questionNumberText = "問{num}:";

    private String questionChoiceNumberText = "{num}.";

    private String correctMessage = "正解！";

    private String incorrectMessage = "不正解･･････。";

    private String simpleCorrectMessage = "○";

    private String simpleIncorrectMessage = "☓";
}
