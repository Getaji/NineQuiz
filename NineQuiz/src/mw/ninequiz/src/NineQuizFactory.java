package mw.ninequiz.src;

/**
 * NineQuizのロジックで用いる各種オブジェクトを生成します.
 */
public class NineQuizFactory {

    private NineQuizFactory() {}

    /**
     * 問題番号をフォーマットして返します.<br />
     * <code>"{num}"</code>が問題番号に置換されます.
     * @param number 問題番号
     * @param unFormat 未フォーマット文字列
     * @return フォーマットされた文字列
     */
    public static String createFormattedStatementNumber(int number, String unFormat) {
        return unFormat.replace("{num}", String.valueOf(number));
    }

    /**
     * 選択肢番号をフォーマットして返します.<br />
     * <code>"{num}"</code>が選択肢番号に置換されます.
     * @param number 選択肢番号
     * @param unFormat 未フォーマット文字列
     * @return フォーマットされた文字列
     */
    public static String createFormattedChoiceNumber(int number, String unFormat) {
        return unFormat.replace("{num}", String.valueOf(number));
    }

    /**
     * characterで区切り結合したlistを文字列として返します.
     * @param character 区切り文字
     * @param list 対象リスト
     * @return 結果
     */
    public static String join(String character, Object... list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.length - 1; ++i) {
            builder.append(list[i]);
            builder.append(character);
        }
        builder.append(list[list.length - 1]);
        return builder.toString();
    }

    /**
     * 解答リストであるbooleanの配列を指定文字に変換してカンマで区切り返します.<br />
     * trueが正解, falseが不正解になります.
     * @param correct 正解を表す文字
     * @param incorrect 不正解を表す文字
     * @param answers 解答リスト
     * @return 結果
     */
    public static String createTransferredAnswers(String correct, String incorrect, boolean[] answers) {
        String[] charAnswers = new String[answers.length];
        for (int i = 0; i < answers.length; ++i) {
            charAnswers[i] = answers[i] ? correct : incorrect;
        }
        return join(", ", charAnswers);
    }
}
