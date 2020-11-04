package com.locantoapps.stupidquiz;

public class level4 {
    public String questions[] = {
            "What system do the blind use to read?",
            "How many colors are the rainbow?",
            "if I drink, I die. If I eat, I am fine. What am I?",
            "What continent has the most countries?",
            "What are peaccocks to colourful?",
            "What is air pressure measured with?",
            "What is the total number of dots on a dice?",
            "Who is the founder of Amazon?",
            "What country is nelson mandela from?",
            "What is the national bird of New Zealand?"
    };

    public String choices[][] = {
            {"Morse", "Binary", "Braille", "Broil"},
            {"5","7","6","8"},
            {"Fire", "Water", "Ice", "Steam"},
            {"Asia", "Europe", "Africa ", "South America"},
            {"To attract the opposite gender","Camouflage","Fly","Catch Prey"},
            {"Thermometer", "Aerometer", "Ruler", "Barometer"},
            {"16","18","21","28"},
            {"Jeff Bezos","mark Zuckerberg","Elon Musk","Steve Jobs"},
            {"South Africa","kenya","Zimbabwe","Ghana"},
            {"Dove","Eagle","Pigeon","Kiwi"}
    };

    public String correctAnswer[] = {
            "Braille",
            "7",
            "Fire",
            "Africa",
            "To attract the opposite gender",
            "Barometer",
            "21",
            "Jeff Bezos",
            "South Africa",
            "Kiwi"
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
