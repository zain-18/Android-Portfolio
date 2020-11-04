package com.locantoapps.stupidquiz;

public class level3 {
    public String questions[] = {
            "What is the longest river in the world?",
            "The legend of Robin Hood is centred on which forest?",
            "What is Europe?",
            "How many moons does Mars have?",
            "What company was founded by Bill Gates?",
            "What is the largest ocean in the world?",
            "What country has the largest population in the world",
            "How many legs does a spider have?",
            "What is the major international competition for soccer?",
            "What is the capital of Australia?"
    };

    public String choices[][] = {
            {"Yangtze", "Nile", "Mississippi", "Mekong"},
            {"Forest of Dean","Sherwood","Lincoln","Shervage"},
            {"State", "Planet", "Continent", "Country"},
            {"0", "1", "2", "4"},
            {"Vista Systems","Microsoft","MicroUnity","IBM"},
            {"Pacific", "Atlantic", "Arctic", "indian"},
            {"Russia","China","USA","Brazil"},
            {"4","6","8","16"},
            {"NHL","FIFA World Cup","The Masters","Super Bowl"},
            {"Brisbane","Sydney","melbourne","Canberra"}
    };

    public String correctAnswer[] = {
            "Nile",
            "Sherwood",
            "Continent",
            "2",
            "Microsoft",
            "Pacific",
            "China",
            "8",
            "FIFA World Cup",
            "Canberra"

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
