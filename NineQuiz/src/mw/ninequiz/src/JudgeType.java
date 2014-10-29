package mw.ninequiz.src;

import java.util.function.Function;

/**
 * 判定の種類を管理する列挙クラスです。
 *
 * @author Getaji
 */
public enum JudgeType {

    /** 正解を表す。 */
    CORRECT  (QuizLogicData::getCorrectMessage,
              QuizLogicData::getSimpleCorrectMessage),

    /** 不正解を表す。 */
    INCORRECT(QuizLogicData::getIncorrectMessage,
              QuizLogicData::getSimpleIncorrectMessage),
    ;

    private final Function<QuizLogicData, String> toStringFunc;
    private final Function<QuizLogicData, String> toShortStringFunc;

    private JudgeType(Function<QuizLogicData, String> toStringFunc,
                      Function<QuizLogicData, String> toShortStringFunc) {
        this.toStringFunc = toStringFunc;
        this.toShortStringFunc = toShortStringFunc;
    }

    /**
     * 受け取ったデータからこのインスタンスに応じた文字列表現を返す。
     *
     * @param quizLogicData 用いるデータ
     * @return 文字列表現
     */
    public String toString(QuizLogicData quizLogicData) {
        return toStringFunc.apply(quizLogicData);
    }

    /**
     * 受け取ったデータからこのインスタンスに応じた短い文字列表現を返す。
     *
     * @param quizLogicData 用いるデータ
     * @return 短い文字列表現
     */
    public String toShortString(QuizLogicData quizLogicData) {
        return toShortStringFunc.apply(quizLogicData);
    }
}