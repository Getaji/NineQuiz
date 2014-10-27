package mw.ninequiz.src;

/**
 * 問題のリストです.<br />
 * 問題数は初期化時に固定され, 変更することはできません.<br />
 * 問題数は最低1以上でなければなりません.
 */
public class QuestionList {

    private final int questionCount;

    private final Question[] list;

    /**
     * 初期化します.<br />
     * 問題数は9個になります.
     */
    public QuestionList() {
        this(9);
    }

    /**
     * 問題数を指定して初期化します.
     * @param questionCount 問題数
     */
    public QuestionList(int questionCount) {
        if (questionCount < 1) {
            throw new RuntimeException("Too little question count! please greater than 0.");
        } else {
            this.questionCount = questionCount;
        }
        this.list = new Question[questionCount];
    }

    /**
     * 問題を受け取って初期化します.
     * @param questions 問題
     */
    public QuestionList(Question... questions) {
        if (questions.length < 1) {
            throw new RuntimeException("Too little question count! please greater than 0.");
        } else {
            this.questionCount = questions.length;
            this.list = questions;
        }
    }

    /**
     * 問題リストを返します.
     * @return 問題リスト
     */
    public Question[] getList() {
        return list;
    }

    /**
     * 問題数を返します.
     * @return 問題数
     */
    public int getQuestionCount() {
        return questionCount;
    }

    /**
     * 問題を返します.
     * @param index 問題番号
     * @return 問題
     */
    public Question getQuestion(int index) {
        return list[index];
    }

    /**
     * 問題をセットします.
     * @param index 問題番号
     * @param question 問題
     */
    public void setQuestion(int index, Question question) {
        list[index] = question;
    }
}
