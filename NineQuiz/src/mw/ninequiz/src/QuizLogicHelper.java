package mw.ninequiz.src;

import lombok.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * クイズロジックのヘルパークラスです。
 */
public class QuizLogicHelper {

    private QuizLogicHelper() {}

    /**
     * 問題番号をフォーマットして返します.<br />
     * <code>"{num}"</code>が問題番号に置換されます.
     * @param number 問題番号
     * @param unFormat 未フォーマット文字列
     * @return フォーマットされた文字列
     */
    @NonNull
    public static String formatStatementNumber(int number, String unFormat) {
        return unFormat.replace("{num}", String.valueOf(number));
    }

    /**
     * 選択肢番号をフォーマットして返します.<br />
     * <code>"{num}"</code>が選択肢番号に置換されます.
     * @param number 選択肢番号
     * @param unFormatted 未フォーマット文字列
     * @return フォーマットされた文字列
     */
    @NonNull
    public static String formatChoiceNumber(int number, String unFormatted) {
        return unFormatted.replace("{num}", String.valueOf(number));
    }

    /**
     * characterで区切り結合したlistを文字列として返します.
     * @param character 区切り文字
     * @param list 対象リスト
     * @return 結果
     */
    @NonNull
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
     * 判定のコレクションを文字列に整形します。
     * 渡されたデータの書式に変換しカンマで区切って返します。
     *
     * <p>例：{@link JudgeType#CORRECT}　x2, {@link JudgeType#INCORRECT} x1, データ初期値を渡す<br>
     * {@code "○, ○, ☓"}
     *
     * @param judges 判定のコレクション
     * @param data データ
     * @return 結果
     */
    @NonNull
    public static String formatJudges(Collection<JudgeType> judges, QuizLogicData data) {
        final Stream<JudgeType> unFormatted = judges.stream();
        final Stream<String> formatted = unFormatted.map(judge -> judge.toShortString(data));
        final List<String> answers = formatted.collect(Collectors.toList());
        return join(", ", answers);
    }
}
