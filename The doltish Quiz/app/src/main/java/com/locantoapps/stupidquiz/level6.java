package com.locantoapps.stupidquiz;

public class level6 {
    public String questions[] = {
            "What does the first 'A' in NASA stand for?",
            "Who was the first black president of the United States?",
            "Which of these does not belong to a game of Chess?",
            "What do the stars of the USA flag represent?",
            "Why are there tides in the ocean?",
            "What is the chemical symbol for silber?",
            "Whar is the largest planet in the solar system?",
            "How many months of the year have 31 days?",
            "What is the fastest bird on foot?",
            "What country makes more movies than any other?"

    };

    public String choices[][] = {
            {"American", "Astrnaut", "Aeronautics", "Asian"},
            {"James jackson","Will Smith","Barack Obama","Tim Scott"},
            {"Prince", "Rook", "Queen", "King"},
            {"Cities", "Countries", "States", "provinces"},
            {"3.2","5.43","2.56","3.14"},
            {"Shanghai", "Hong Kong", "Macau", "Beijing"},
            {"Moon ","Sun","Volcano","Plants"},
            {"Si","Ag","Vr","Mg"},
            {"Saturn","Jupiter","Venus","Mars"},
            {"Pigeon","Ostrich","Eagle","Sparrow"}
    };

    public String correctAnswer[] = {
            "Aeronautics",
            "Barack Obama",
            "Prince",
            "States",
            "3.14 ",
            "Moon",
            "Ag",
            "Jupiter",
            "Ostrich",
            "India"
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
