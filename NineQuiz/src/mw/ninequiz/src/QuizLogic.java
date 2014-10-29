package mw.ninequiz.src;

import java.util.*;

/**
 * クイズを実行するロジッククラスです。
 * 問題を渡して{@link #start()}を呼ぶとクイズが開始され、標準入力から解答を受け取ります。
 * 正誤は即座に出力され、最後にすべての結果が表示されます。
 */
public class QuizLogic {

    /**
     * 問題のコレクションを受け取りインスタンスを生成します。
     * {@link QuizLogicData}は新規生成されます。これを取得する場合は{@link #getData()}を呼び出してください。
     *
     * @param questions 問題のコレクション
     * @return インスタンス
     */
    public static QuizLogic of(Collection<Question> questions) {
        return new QuizLogic(questions, new QuizLogicData());
    }

    /**
     * 問題のコレクションとロジックデータを受け取りインスタンスを生成します。
     *
     * @param questions 問題のコレクション
     * @param data ロジックデータ
     * @return インスタンス
     */
    public static QuizLogic of(Collection<Question> questions, QuizLogicData data) {
        return new QuizLogic(questions, data);
    }

    private final QuizLogicData data;
    private final Scanner scanner = new Scanner(System.in);
    private final Collection<Question> questions;

    /**
     * 初期化
     *
     * @param questions 問題
     */
    private QuizLogic(Collection<Question> questions, QuizLogicData data) {
        this.questions = questions;
        this.data = data;
    }

    /**
     * 開始前に呼ばれます。
     */
    protected void preProcess() {
        //
    }

    /**
     * クイズを開始します。受け取った問題を順に表示し、標準入力から回答を受け取ります。
     */
    public void start() {
        this.preProcess();

        System.out.println(this.data.getStartMessage());

        run();
    }

    /**
     * クイズを出題し解答を要求するロジック。
     */
    protected void run() {
        final List<JudgeType> judgedList = new ArrayList<>(questions.size());
        final Iterator<Question> questionIterator = questions.iterator();

        for (int i = 0; questionIterator.hasNext(); ++i) {
            Question question = questionIterator.next();
            viewQuestion(i, question);

            System.out.print("number>");

            final int answer = scanner.nextInt();
            judgedList.add(judgeAndView(answer, question));
        }
        viewResult(judgedList);
        scanner.close();
    }

    /**
     * 解答を判定して表示し結果を返す。
     *
     * @param userAnswer 受け取った解答
     * @param question 問題
     * @return 判定結果
     */
    protected JudgeType judgeAndView(int userAnswer, Question question) {
        final boolean isCorrect = userAnswer == question.getAnswerIndex();
        final JudgeType judge = (isCorrect) ?
                          JudgeType.CORRECT : JudgeType.INCORRECT;
        final String stringJudge = judge.toString(data) + "\n";
        System.out.println(stringJudge);
        return judge;
    }

    /**
     * 問題を表示します。
     *
     * @param number 問題番号
     * @param question 問題
     */
    protected void viewQuestion(int number, Question question) {
        final String formattedStateNum = QuizLogicHelper.formatStatementNumber(
                number + 1, data.getQuestionNumberText());
        System.out.println(formattedStateNum + question.getQuestionStatement());

        Iterator<String> choiceIter = question.getQuestionChoices().iterator();

        for (int i = 0; choiceIter.hasNext(); ++i) {
            final String formattedChoiceNum = QuizLogicHelper.formatChoiceNumber(
                    i + 1, data.getQuestionChoiceNumberText());
            System.out.println(formattedChoiceNum + choiceIter.next());
        }
    }

    /**
     * 結果を表示します。
     *
     * @param judges 解答
     */
    protected void viewResult(Collection<JudgeType> judges) {
        System.out.println("[結果発表]"); // TODO Modelに突っ込め
        System.out.println(QuizLogicHelper.formatJudges(judges, data));
    }

    /**
     * 保持するデータクラスを返します。
     *
     * @return データ
     */
    public QuizLogicData getData() {
        return data;
    }

    /**
     * 問題数を返します。
     *
     * @return 問題数
     */
    public int getQuestionCount() {
        return questions.size();
    }
}
