package mw.ninequiz.src;

import java.util.Arrays;
import java.util.List;

/**
 * コアクラス.
 */
public class Main {

    public static void main(String[] args) {
        List<Question> questions = Arrays.asList(
                Question.builder().setQuestionStatement("Javaの型付けは何ですか？")
                        .addQuestionChoice("静的型付け")
                        .addQuestionChoice("動的型付け")
                        .addQuestionChoice("性的型付け")
                        .setAnswerIndex(1)
                        .build(),
                Question.builder().setQuestionStatement("int型はどの種類の型ですか？")
                        .addQuestionChoice("参照型")
                        .addQuestionChoice("基本型")
                        .addQuestionChoice("金剛型")
                        .setAnswerIndex(2)
                        .build()
        );
        NineQuizData quizData = new NineQuizData();
        QuizLogic quizLogic = QuizLogic.of(questions, quizData);
        quizLogic.start();
    }
}
