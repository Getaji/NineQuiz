package mw.ninequiz.src;

/**
 * 問題です.
 */
public class Question {

    private String statement;

    private String[] choices;

    private int answer;

    /**
     * 問題文と選択肢を指定して初期化します.<br />
     * 正解は自動で1になります.
     * @param statement 問題文
     * @param choices 選択肢
     */
    public Question(String statement, String... choices) {
        this(statement, 1, choices);
    }

    /**
     * 初期化します.
     * @param statement 問題文
     * @param answer 正解
     * @param choices 選択肢
     */
    public Question(String statement, int answer, String... choices) {
        this.statement = statement;
        if (choices.length < 1) {
            throw new RuntimeException("Too little choice count! please greater than 0.");
        } else {
            this.choices = choices;
        }
        if (answer < 1 || choices.length + 1 < answer) {
            throw new RuntimeException("Question answer out of bounds!");
        } else {
            this.answer = answer;
        }
    }

    /**
     * 問題文を返します.
     * @return 問題文
     */
    public String getStatement() {
        return statement;
    }

    /**
     * 問題文をセットします.
     * @param statement 問題文
     */
    public void setStatement(String statement) {
        this.statement = statement;
    }

    /**
     * 選択肢のリストを返します.
     * @return 選択肢のリスト
     */
    public String[] getChoices() {
        return choices;
    }

    /**
     * 選択肢のリストをセットします.
     * @param choices 選択肢のリスト
     */
    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    /**
     * 選択肢をセットします
     * @param index 選択肢番号
     * @param choice 選択肢
     */
    public void setChoice(int index, String choice) {
        choices[index] = choice;
    }

    /**
     * 選択肢の数を返します.
     * @return 選択肢の数
     */
    public int getChoiceCount() {
        return choices.length;
    }

    /**
     * 正解を返します.
     * @return 正解
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * 正解をセットします.
     * @param answer 正解
     */
    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
