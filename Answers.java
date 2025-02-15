package application;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Answers {

	public static String FILE_PATH_OUTPUT = "answers.txt";
	private static ArrayList<Question> answerList = new ArrayList<>();

	public static void writeAnswers(Answer answer) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_OUTPUT, true))){
			String answerData =  answer.number + "\n"+ answer.id + "\n" + answer.answer +"\n";
			writer.write(answerData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public static void loadQuestionsFromFile() { try (BufferedReader reader = new
	 * BufferedReader(new FileReader(FILE_PATH_OUTPUT))) { String line; while ((line
	 * = reader.readLine()) != null) { String number = line; String id =
	 * reader.readLine(); String questionText = reader.readLine(); String
	 * description = reader.readLine(); boolean answered =
	 * Boolean.parseBoolean(reader.readLine()); boolean resolved =
	 * Boolean.parseBoolean(reader.readLine());
	 * 
	 * // Add the question to the ArrayList questionList.add(new Question(id,
	 * questionText, description,number )); } } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * public static void displayQuestions() { int i=0; //questionList.clear(); if
	 * (questionList.isEmpty()) { System.out.println("No questions available.");
	 * return; }
	 * 
	 * while (i < questionList.size()) { Question question = questionList.get(i);
	 * i++;
	 * 
	 * System.out.println("Q#: "+question.number); System.out.println("ID: " +
	 * question.id); System.out.println("Question: " + question.question);
	 * System.out.println("Description: " + question.description);
	 * System.out.println("Answered: " + question.answered);
	 * System.out.println("Resolved: " + question.resolved);
	 * 
	 * if (questionList.answerList().isEmpty()) {
	 * System.out.println("Answers: No answers yet"); } else {
	 * System.out.println("Answers:"); for (String answer : question.getAnswers()) {
	 * System.out.println("- " + answer); }
	 * 
	 * System.out.println("----------------------"); } } public static void clear()
	 * { questionList.clear(); }
	 */

}
