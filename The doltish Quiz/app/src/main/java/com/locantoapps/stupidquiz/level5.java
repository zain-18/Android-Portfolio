package com.locantoapps.stupidquiz;

public class level5 {
    public String questions[] = {
            "What planet is known as the red planet?",
            "Who painted the Mona Lisa?",
            "What color is the bottom of a rainbow?",
            "Which is the coldest location on Earth?",
            "What is the largest country in Sounth America?",
            "What is the capital of China?",
            "Which continent is the least populated?",
            "How many months of the year have 31 days?",
            "How many sides does a hexagon have?",
            "What country makes BMW, Audi and Mercedes?"

    };

    public String choices[][] = {
            {"Saturn", "Mars", "Venus", "Jupiter"},
            {"Leonardo Da Vinci","Vincent van Gogh","Pablo Picasso","Michelangelo"},
            {"Voilet", "Red", "Blue", "Greenwich"},
            {"Antarctica", "Belgium", "Denmark ", "South Dubin"},
            {"Argentina","Peru","Brazil","Colombia"},
            {"Shanghai", "Hong Kong", "Macau", "Beijing"},
            {"Asia","Antarctica","SOunt America","Europe"},
            {"4","6","7","8"},
            {"5","6","7","8"},
            {"Italy","USA","France","Germany"}
    };

    public String correctAnswer[] = {
            "Mars",
            "Leonardo Da Vinci",
            "Voilet",
            "Antarctica",
            "Brazil",
            "Beijing",
            "Antarctica",
            "7",
            "6",
            "Germany"
    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getchoice4(int a){
        String choice = choices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }
}
