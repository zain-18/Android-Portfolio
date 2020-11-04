package com.locantoapps.stupidquiz;

/**
 * Created by Arvin on 2/21/2018.
 */

public class Question {

    public String questions[] = {
            "Which shape does NOT have four sides?",
            "Who was the first person to step foot on the moon?",
            "Which force keeps you down to earth?",
            "How many years is a century?",
            "How much is a quarter?",
            "What is the most abundant gas in the Earth's atmosphere?",
            "Which month comes after August?",
            "How long does the earth take to rotate around its axis?",
            "What planet is closest to the sun?",
            "How many days are there in a leap year?"
    };

    public String choices[][] = {
            {"Rectangle", "Parallelogram", "Rhombus", "Triangle"},
            {"Buzz Aldrin","John Glenn","Alan Shepard","Neil Armstrong"},
            {"Friction", "Magnetic", "Gravity", "Electric"},
            {"100", "50", "1000", "25"},
            {"1/2","1/3","1/4","1/12"},
            {"Carbon Diozide", "Oxygen", "Hydrogen", "Nitrogen"},
            {"September","October","June","May"},
            {"1 Day","1 Week","1 Year","10 Years"},
            {"Venus","Mars","Earth","Mercury"},
            {"364","365","366","367"}
    };

    public String correctAnswer[] = {
        "Triangle",
        "Neil Armstrong",
        "Gravity",
            "100",
            "1/4",
            "Nitrogen",
            "September",
            "1 Day",
            "Mercury",
            "366"
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
