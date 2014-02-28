package mw.ninequiz.src;

/**
 * 9問クイズのデータモデル.
 */
public class NineQuizModel {

    private String startMessage = "Nine Quiz Start!";

    private String questionNumberText = "Number{num}:";

    private String questionChoiceNumberText = "Q{num}.";

    private String questionIsCorrect = "That's right!";

    private String questionIsIncorrect = "That's wrong...";

    private String simpleCorrect = "○";

    private String simpleIncorrect = "☓";

    /**
     * 開始メッセージを返します.
     * @return 開始メッセージ
     */
    public String getStartMessage() {
        return startMessage;
    }

    /**
     * 開始メッセージをセットします.
     * @param startMessage 開始メッセージ
     */
    public void setStartMessage(String startMessage) {
        this.startMessage = startMessage;
    }

    /**
     * 正解した時のメッセージを返します.
     * @return 正解した時のメッセージ
     */
    public String getQuestionIsCorrect() {
        return questionIsCorrect;
    }

    /**
     * 正解した時のメッセージをセットします.
     * @param questionIsCorrect 正解した時のメッセージ
     */
    public void setQuestionIsCorrect(String questionIsCorrect) {
        this.questionIsCorrect = questionIsCorrect;
    }

    /**
     * 不正回だった時のメッセージを返します.
     * @return 不正回だった時のメッセージ
     */
    public String getQuestionIsIncorrect() {
        return questionIsIncorrect;
    }

    /**
     * 不正回だった時のメッセージをセットします.
     * @param questionIsIncorrect 不正回だった時のメッセージ
     */
    public void setQuestionIsIncorrect(String questionIsIncorrect) {
        this.questionIsIncorrect = questionIsIncorrect;
    }

    /**
     * 問題番号の未フォーマット文を返します.<br />
     * 通常はこれを{@link NineQuizFactory#createFormattedStatementNumber(int, String)}を利用し,
     * フォーマットして使用します.
     * @return 問題番号の未フォーマット文
     */
    public String getQuestionNumberText() {
        return questionNumberText;
    }

    /**
     * 問題番号の未フォーマット文をセットします.
     * @param questionNumberText 問題番号の未フォーマット文
     */
    public void setQuestionNumberText(String questionNumberText) {
        this.questionNumberText = questionNumberText;
    }

    /**
     * 選択肢番号の未フォーマット文を返します.<br />
     * 通常はこれを{@link NineQuizFactory#createFormattedChoiceNumber(int, String)}を利用し,
     * フォーマットして使用します.
     * @return 選択肢番号の未フォーマット文
     */
    public String getQuestionChoiceNumberText() {
        return questionChoiceNumberText;
    }

    /**
     * 選択肢番号の未フォーマット文をセットします.
     * @param questionChoiceNumberText 選択肢番号の未フォーマット文
     */
    public void setQuestionChoiceNumberText(String questionChoiceNumberText) {
        this.questionChoiceNumberText = questionChoiceNumberText;
    }

    /**
     * 正解を表す文字列を返します.
     * @return 正解を表す文字列
     */
    public String getSimpleCorrect() {
        return simpleCorrect;
    }

    /**
     * 正解を表す文字列をセットします.
     * @param simpleCorrect 正解を表す文字列
     */
    public void setSimpleCorrect(String simpleCorrect) {
        this.simpleCorrect = simpleCorrect;
    }

    /**
     * 不正解を表す文字列を返します.
     * @return simpleCorrect 不正解を表す文字列
     */
    public String getSimpleIncorrect() {
        return simpleIncorrect;
    }

    /**
     * 不正解を表す文字列をセットします.
     * @param simpleIncorrect 不正解を表す文字列
     */
    public void setSimpleIncorrect(String simpleIncorrect) {
        this.simpleIncorrect = simpleIncorrect;
    }
}
