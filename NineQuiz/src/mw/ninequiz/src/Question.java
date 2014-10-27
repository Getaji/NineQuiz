package mw.ninequiz.src;

/**
 * 問題を表すクラスです。このクラスは不変で状態を持ちません。
 */
public class Question {

    private final String questionStatement;

    private final String[] questionChoices;

    private final int answer;

    /**
     * 問題文と選択肢を指定して初期化します。<br />
     * 正解は自動で1になります。
     * @param questionStatement 問題文
     * @param questionChoices 選択肢
     */
    public Question(String questionStatement, String... questionChoices) {
        this(questionStatement, 1, questionChoices);
    }

    /**
     * 初期化します。
     * @param questionStatement 問題文
     * @param answerIndex 正解
     * @param questionChoices 選択肢
     */
    public Question(String questionStatement, int answerIndex,
                    String... questionChoices) {
        this.questionStatement = questionStatement;

        if (questionChoices.length < 1) {
            throw new RuntimeException("Too little choice count! please greater than 0.");
        }

        if (answerIndex < 1 || questionChoices.length + 1 < answerIndex) {
            throw new RuntimeException("Question answer out of bounds!");
        }

        this.questionChoices = questionChoices;
        this.answer = answerIndex;
    }

    /**
     * 問題文を返します。
     * @return 問題文
     */
    public String getQuestionStatement() {
        return questionStatement;
    }

    /**
     * 選択肢のリストを返します。
     * @return 選択肢のリスト
     */
    public String[] getQuestionChoices() {
        return questionChoices;
    }

    /**
     * 選択肢の数を返します。
     * @return 選択肢の数
     */
    public int getChoiceCount() {
        return questionChoices.length;
    }

    /**
     * 正解を返します。
     * @return 正解
     */
    public int getAnswer() {
        return answer;
    }
}
