package mw.ninequiz.src;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 問題を表すクラスです。このクラスは不変で状態を持ちません。
 *
 *　<h1>問題データの規則</h1>
 * これはBuilderのセッターにも記述されています。
 *
 * <ul>
 *     <li>禁止：問題文にはnullか空文字は許可されない。</li>
 *     <li>要求：正解のインデックスは選択肢の数の範囲内でなければならない。</li>
 *     <li>要求：正解のインデックスは１から始まれなければならない。</li>
 *     <li>禁止：選択肢にはnullか空文字は許可されない。</li>
 *     <li>禁止：選択肢の重複は許可されない。</li>
 * </ul>
 */
public class Question {

    public static class QuestionBuilder {

        private static final int ANSWER_INDEX_INITIAL_VALUE = -1;

        private String questionStatement;
        private int answerIndex = ANSWER_INDEX_INITIAL_VALUE;
        private final Set<String> questionChoices = new LinkedHashSet<>();

        private QuestionBuilder() {}

        public QuestionBuilder addQuestionChoice(String questionChoice) {
            // 正当性検査
            if (questionChoice == null) {
                throw new NullPointerException("take question choice is null");
            }

            if (questionChoice.isEmpty()) {
                throw new IllegalArgumentException("take question choice is empty");
            }

            // 格納
            questionChoices.add(questionChoice);
            return this;
        }

        public QuestionBuilder setQuestionStatement(String questionStatement) {
            // 正当性検査
            if (questionStatement == null) {
                throw new NullPointerException("Take question statement is null");
            }

            if (questionStatement.isEmpty()) {
                throw new IllegalArgumentException("Take question statement is empty");
            }

            // 格納
            this.questionStatement = questionStatement;
            return this;
        }

        public QuestionBuilder setAnswerIndex(int answerIndex) {
            // 正当性検査
            if (answerIndex < 1 || questionChoices.size() + 1 < answerIndex) {
                throw new IndexOutOfBoundsException("Answer index is out of choices bounds");
            }

            // 格納
            this.answerIndex = answerIndex;
            return this;
        }

        public Question build() {
            // 状態検査
            if (questionStatement == null) {
                throw new IllegalStateException("Question statement is not set");
            }

            if (answerIndex == ANSWER_INDEX_INITIAL_VALUE) {
                throw new IllegalStateException("Answer index is not set");
            }

            if (questionChoices.size() == 0) {
                throw new IllegalStateException("Question choice is not set");
            }

            // 発行
            return new Question(questionStatement, answerIndex, questionChoices);
        }
    }

    public static QuestionBuilder builder() {
        return new QuestionBuilder();
    }

    private final String questionStatement;

    private final Set<String> questionChoices;

    private final int answerIndex;

    /**
     * 初期化します。
     * @param questionStatement 問題文
     * @param answerIndex 正解
     * @param questionChoices 選択肢
     */
    private Question(String questionStatement, int answerIndex,
                    Set<String> questionChoices) {
        // 正当性はBuilderで検査されている。
        this.questionStatement = questionStatement;
        this.questionChoices = questionChoices;
        this.answerIndex = answerIndex;
    }

    /**
     * 問題文を返します。
     * @return 問題文
     */
    public String getQuestionStatement() {
        return questionStatement;
    }

    /**
     * 選択肢のセットを返します。
     * @return 選択肢のリスト
     */
    public Set<String> getQuestionChoices() {
        return questionChoices;
    }

    /**
     * 選択肢の数を返します。
     * @return 選択肢の数
     */
    public int getChoiceCount() {
        return questionChoices.size();
    }

    /**
     * 正解を返します。
     * @return 正解
     */
    public int getAnswerIndex() {
        return answerIndex;
    }
}
