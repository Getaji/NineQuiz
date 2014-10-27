package mw.ninequiz.src;

import java.util.Scanner;

/**
 * NineQuizのロジックです.<br />
 * 問題を渡してstartするだけでクイズをすることができます.
 */
public class QuizLogic {

    private final NineQuizModel model;

    private final QuestionList questions;

    private final Scanner scanner = new Scanner(System.in);

    /**
     * 問題を渡して初期化します.
     * @param questions 問題
     */
    public QuizLogic(QuestionList questions) {
        this.questions = questions;
        this.model = new NineQuizModel();
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
     * クイズを出題し解答を要求するロジックです.
     */
    protected void run() {
        boolean[] answers = new boolean[questions.getQuestionCount()];
        for (int i = 0; i < questions.getQuestionCount(); ++i) {
            Question question = questions.getQuestion(i);
            viewQuestion(i, question);

            System.out.print(">");
            int answer = scanner.nextInt();
            if (answer == question.getAnswer()) {
                System.out.println(model.getQuestionIsCorrect());
                answers[i] = true;
            } else {
                System.out.println(model.getQuestionIsIncorrect());
                answers[i] = false;
            }
            System.out.println("\n");
        }
        viewResult(answers);
        scanner.close();
    }

    /**
     * 問題文を表示します.
     * @param number 問題番号
     * @param question 問題
     */
    protected void viewQuestion(int number, Question question) {
        System.out.print(NineQuizUtil.createFormattedStatementNumber(
                number, model.getQuestionNumberText()));
        System.out.println(question.getQuestionStatement());
        for (int i = 0; i < question.getChoiceCount(); ++i) {
            System.out.print(NineQuizUtil.createFormattedChoiceNumber(
                    i + 1, model.getQuestionChoiceNumberText()
            ));
            System.out.println(question.getQuestionChoices()[i]);
        }
    }

    /**
     * 結果を表示します.
     * @param answers 解答
     */
    protected void viewResult(boolean[] answers) {
        System.out.println("[RESULT]");
        System.out.println(NineQuizUtil.createTransferredAnswers(
                model.getSimpleCorrect(), model.getSimpleIncorrect(), answers));
    }

    /**
     * データモデルを返します.
     * @return データモデル
     */
    public NineQuizModel getModel() {
        return model;
    }

    /**
     * 問題数を返します.
     * @return 問題数
     */
    public int getQuestionCount() {
        return questions.getQuestionCount();
    }
}
