package mw.ninequiz.src;

import java.util.function.Function;

/**
 * javadoc here.
 *
 * @author Getaji
 */
public enum JudgeType {

    CORRECT  (NineQuizData::getQuestionIsCorrect),
    INCORRECT(NineQuizData::getQuestionIsIncorrect),
    ;

    private final Function<NineQuizData, String> toStringFunc;

    private JudgeType(Function<NineQuizData, String> toStringFunc) {
        this.toStringFunc = toStringFunc;
    }

    public String toString(NineQuizData nineQuizData) {
        return toStringFunc.apply(nineQuizData);
    }
}