package mw.ninequiz.src;

import java.util.Arrays;
import java.util.List;

/**
 * さんぷる。
 */
public class Main {

    public static void main(String[] args) {
        final List<Question> questions = Arrays.asList(
                Question.builder().setStatement("Javaの型付けは何ですか？")
                        .addChoice("静的型付け")
                        .addChoice("動的型付け")
                        .addChoice("性的型付け")
                        .setAnswerIndex(1)
                        .build(),
                Question.builder().setStatement("int型はどの種類の型ですか？")
                        .addChoice("参照型")
                        .addChoice("基本型")
                        .addChoice("金剛型")
                        .setAnswerIndex(2)
                        .build()
        );
        final QuizLogicData quizData = new QuizLogicData();
        final QuizLogic quizLogic = QuizLogic.of(questions, quizData);
        quizLogic.start();
    }
}
