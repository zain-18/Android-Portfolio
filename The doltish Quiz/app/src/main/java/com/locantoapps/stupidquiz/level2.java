package com.locantoapps.stupidquiz;

public class level2 {
    public String questions[] = {
            "Which Apollo mission landed the first humans on the Moon?",
            "Whar color are the stars in the flag of the United States of America?",
            "In which city can you visit the Statue of Liberty",
            "What force affects compasses?",
            "What superhero is weakend by kryptonite?",
            "What kind of animal is dolphin?",
            "Which fruit is usually used when producing wine?",
            "What is 150% of 50?",
            "What is the largest continent on earth??",
            "What is the longest bone in the human body?"
    };

    public String choices[][] = {
            {"Apollo 7", "Apollo 9", "Apollo 11", "Apollo 13"},
            {"White","Gold","Blue","Red"},
            {"New york", "Washington DC", "Greenwich", "Toronto "},
            {"Gravity", "Tension", "Electric", "Magnetic"},
            {"Aquaman","Batman","Superman","Wolverine"},
            {"Mammal", "Fish", "Reptile", "Amphibian"},
            {"Apples","Rasberries","Melons","Grapes"},
            {"55","75","65","500"},
            {"Sount America","Europe","Africa","Asia"},
            {"Femur","Scapula","Tibia","Skull"}
    };

    public String correctAnswer[] = {
            "Apollo 11",
            "White",
            "New york",
            "Magnetic",
            "Superman",
            "Mammal",
            "Grapes",
            "75",
            "Asia",
            "Femur"
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
