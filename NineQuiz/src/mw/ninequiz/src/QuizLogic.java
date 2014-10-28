package mw.ninequiz.src;

import java.util.*;

/**
 * NineQuizのロジックです.<br />
 * 問題を渡してstartするだけでクイズをすることができます.
 */
public class QuizLogic {

    public static QuizLogic of(Collection<Question> questions) {
        return new QuizLogic(questions, new NineQuizData());
    }

    public static QuizLogic of(Collection<Question> questions, NineQuizData model) {
        return new QuizLogic(questions, model);
    }

    private final NineQuizData model;
    private final Scanner scanner = new Scanner(System.in);
    private final Collection<Question> questions;

    /**
     * 問題を渡して初期化します.
     * @param questions 問題
     */
    private QuizLogic(Collection<Question> questions, NineQuizData model) {
        this.questions = questions;
        this.model = model;
    }

    /**
     * 開始前に呼ばれます.<br />
     * このクラスではなにも実行されません.
     */
    protected void preProcess() {
        //
    }

    /**
     * クイズを開始します.<br />
     * 最初に{@link mw.ninequiz.src.QuizLogic#preProcess()}が呼ばれ,
     * 次に開始文が流れ, {@link mw.ninequiz.src.QuizLogic#run()}が呼ばれクイズが始まります.
     */
    public void start() {
        this.preProcess();

        System.out.println(this.model.getStartMessage());

        run();
    }

    /**
     * クイズを出題し解答を要求するロジック。
     */
    protected void run() {
        List<JudgeType> judgedList = new ArrayList<>(questions.size());
        Iterator<Question> questionIterator = questions.iterator();

        for (int i = 0; questionIterator.hasNext(); ++i) {
            Question question = questionIterator.next();
            viewQuestion(i, question);

            System.out.print(">");

            final int answer = scanner.nextInt();
            final JudgeType judge = (answer == question.getAnswerIndex()) ?
                                     JudgeType.CORRECT :
                                     JudgeType.INCORRECT;
            System.out.println(judge.toString(model) + "\n");
            judgedList.add(judge);
        }
        viewResult(judgedList);
        scanner.close();
    }

    /**
     * 問題文を表示します.
     * @param number 問題番号
     * @param question 問題
     */
    protected void viewQuestion(int number, Question question) {
        System.out.print(NineQuizUtil.createFormattedStatementNumber(
                number + 1, model.getQuestionNumberText()));
        System.out.println(question.getQuestionStatement());

        Iterator<String> questionChoicesIterator = question.getQuestionChoices().iterator();

        for (int i = 0; questionChoicesIterator.hasNext(); ++i) {
            System.out.print(NineQuizUtil.createFormattedChoiceNumber(
                    i + 1, model.getQuestionChoiceNumberText()
            ));
            System.out.println(questionChoicesIterator.next());
        }
    }

    /**
     * 結果を表示します.
     * @param judges 解答
     */
    protected void viewResult(Collection<JudgeType> judges) {
        System.out.println("[結果発表]"); // TODO Modelに突っ込め
        System.out.println(NineQuizUtil.createTransferredAnswers(judges, model));
    }

    /**
     * データモデルを返します.
     * @return データモデル
     */
    public NineQuizData getModel() {
        return model;
    }

    /**
     * 問題数を返します.
     * @return 問題数
     */
    public int getQuestionCount() {
        return questions.size();
    }
}
