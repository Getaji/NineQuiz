package mw.ninequiz.src;

/**
 * コアクラス.
 */
public class Main {

    public static void main(String[] args) {
        QuestionList questions = new QuestionList(
                new Question("Javaの型付けは何ですか？", 1,
                        "静的型付け",
                        "動的型付け",
                        "性的型付け"),
                new Question("int型はどの種類の型ですか？", 2,
                        "参照型",
                        "基本型",
                        "金剛型")
        );
        QuizLogic quizLogic = new QuizLogic(questions);
        quizLogic.start();
    }
}
