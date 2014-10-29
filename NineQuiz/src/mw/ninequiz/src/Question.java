package mw.ninequiz.src;

import java.util.*;

/**
 * 問題を表すクラスです。このクラスは不変で状態を持ちません。
 * インスタンスは{@link #builder()}からビルダークラスを作成し構築することができます。
 *
 *　<h1>問題データの契約</h1>
 * これは各ゲッターやBuilderのセッターにも記述されています。
 * ビルダークラスは契約違反が起こった場合に即座に非チェック例外を送出し、この契約を保証します。
 *
 * <ul>
 *     <li>問題文はnull・空ではない。</li>
 *     <li>正解インデックスは1から始まり選択肢の範囲内である。</li>
 *     <li>選択肢はnull・空ではなく重複しない。</li>
 * </ul>
 */
public class Question {

    // ================================================================
    // Statics
    // ================================================================
    /**
     * {@link Question}のインスタンスを構築するビルダークラスです。
     * このクラスは{@link Question}の契約を保証します。
     */
    public static class QuestionBuilder {

        private static final int ANSWER_INDEX_INITIAL_VALUE = -1;

        private String questionStatement;
        private int answerIndex = ANSWER_INDEX_INITIAL_VALUE;
        private final List<String> questionChoices = new ArrayList<>();

        private QuestionBuilder() {}

        /**
         * 複数の選択肢を追加します。
         * 内部では{@link #addChoice(String)}を使用しています。詳細はそちらを参照ください。
         *
         * @param questionChoices 複数の選択肢
         * @throws NullPointerException パラメータがnullの場合に送出
         * @return return this
         */
        public QuestionBuilder addChoiceAll(String... questionChoices) {
            if (questionChoices == null) {
                throw new NullPointerException("take question choices is null");
            }

            for (String questionChoice : questionChoices) {
                addChoice(questionChoice);
            }
            return this;
        }

        /**
         * 選択肢を追加します。
         * 空文字かnullが渡されると対応した例外を送出します。
         * 同じ選択肢がすでにある場合は追加されません。
         *
         * @param questionChoice 選択肢
         * @throws NullPointerException パラメータがnullの場合に送出
         * @throws IllegalArgumentException パラメータが空文字の場合に送出
         * @return return this
         */
        public QuestionBuilder addChoice(String questionChoice) {
            // 正当性検査
            if (questionChoice == null) {
                throw new NullPointerException("take question choice is null");
            }

            if (questionChoice.isEmpty()) {
                throw new IllegalArgumentException("take question choice is empty");
            }

            // すでになければ格納
            if (!questionChoices.contains(questionChoice)) {
                questionChoices.add(questionChoice);
            }
            return this;
        }

        /**
         * 問題文をセットします。
         * 空文字かnullが渡されると例外を送出します。
         *
         * @param questionStatement 問題文
         * @throws NullPointerException パラメータがnullの場合に送出
         * @throws IllegalArgumentException パラメータが空文字の場合に送出
         * @return return this
         */
        public QuestionBuilder setStatement(String questionStatement) {
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

        /**
         * 正解のインデックスをセットします。インデックスは1から始まります。
         * 選択肢の範囲外のインデックスが渡されると例外を送出します。
         *
         * @param answerIndex 正解のインデックス
         * @throws IndexOutOfBoundsException パラメータが範囲外の場合に送出
         * @return return this
         */
        public QuestionBuilder setAnswerIndex(int answerIndex) {
            // 正当性検査
            if (answerIndex < 1 || questionChoices.size() + 1 < answerIndex) {
                throw new IndexOutOfBoundsException("Answer index is out of choices bounds");
            }

            // 格納
            this.answerIndex = answerIndex;
            return this;
        }

        /**
         * {@link Question}のインスタンスを生成して返します。
         * 下記の契約に違反している場合は{@link IllegalStateException}を送出します。
         *
         * <h1>契約</h1>
         * <ul>
         *     <li>問題文がセットされている</li>
         *     <li>正解のインデックスがセットされている</li>
         *     <li>選択肢が1以上セットされている</li>
         * </ul>
         *
         * @throws IllegalStateException 上記の契約に違反した場合に送出
         * @return {@link Question}のインスタンス
         */
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

    /**
     * ビルダーを新たに生成して返します。
     * Questionインスタンスはこのビルダーを介して生成します。
     *
     * @return ビルダーのインスタンス
     */
    public static QuestionBuilder builder() {
        return new QuestionBuilder();
    }

    // ================================================================
    // Fields
    // ================================================================
    private final String questionStatement;

    // Q.なぜSetではなくListなのか
    // A.イテレート以外で値を取得する必要があるから
    private final List<String> questionChoices;

    private final int answerIndex;

    // ================================================================
    // Constructors
    // ================================================================
    /**
     * インスタンスを生成する。
     *
     * @param questionStatement 問題文
     * @param answerIndex 正解
     * @param questionChoices 選択肢
     */
    private Question(String questionStatement, int answerIndex,
                    Collection<String> questionChoices) {
        // 正当性はBuilderで検査されている。
        this.questionStatement = questionStatement;
        this.answerIndex = answerIndex;

        // 防御的コピー
        this.questionChoices = new ArrayList<>();
        this.questionChoices.addAll(questionChoices);
    }

    // ================================================================
    // Getters
    // ================================================================
    /**
     * 問題文を返します。
     *
     * <p>問題文はnullや空文字が入っておらず不変であることが保証されています。
     *
     * @return 問題文
     */
    public String getQuestionStatement() {
        return questionStatement;
    }

    /**
     * 選択肢のセットを返します。
     * クラスの不変性を保証するため、このメソッドは呼び出されると選択肢のセットのコピーを生成して返します。
     * キャッシュは行いません。
     *
     * <p>選択肢は1つ以上でnullや空文字が入っておらず不変であることが保証されています。
     *
     * @return 選択肢のリスト
     */
    public Set<String> getQuestionChoices() {
        final Set<String> defencedChoices = new LinkedHashSet<>();
        defencedChoices.addAll(questionChoices);

        return defencedChoices;
    }

    /**
     * 選択肢の数を返します。
     *
     * <p>選択肢の数は1以上で不変であることが保証されています。
     *
     * @return 選択肢の数
     */
    public int getChoiceCount() {
        return questionChoices.size();
    }

    /**
     * 正解のインデックスを返します。
     *
     * <p>正解のインデックスは1から始まり、
     *    選択肢の範囲内であることが保証されています。
     *
     * @return 正解
     */
    public int getAnswerIndex() {
        return answerIndex;
    }

    /**
     * このクラスの文字列表現を返します。
     *
     * <p>
     *     例：{@code "Question{state:"弦楽器はどれ?" choices:3　answer-index:2 answer:"チェロ"}"}
     * </p>
     * @return 文字列表現
     */
    @Override
    public String toString() {
        return String.format("Question{state:\"%s\" choices:%d　answer-index:%d answer:\"%s\"}",
                questionStatement,
                questionChoices.size(),
                answerIndex,
                questionChoices.get(answerIndex - 1));
    }
}
