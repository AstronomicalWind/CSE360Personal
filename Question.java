package application;
import java.util.List;
import java.util.ArrayList;
public class Question {

	
	public String question;
	public String description;
	public String id;
	public String number;
	public boolean resolved=false;
	public boolean answered=false;
	List<Answer> answerList = new ArrayList<>();
	//public static int numCounter=0;

	
	public Question(String questionId, String questionText, String description, String numb) {
		// TODO Auto-generated constructor stub
		
		 this.id = questionId;
	        this.question = questionText;
	        this.description = description;
	        this.answered = false;
	        this.resolved=false;
	        this.number=numb;
	        //numCounter+=1;
	}
	
	public void addAnswer(Answer answer) {
        answerList.add(answer);
        answered = true; // Mark the question as answered
    }
	
	/*
	 * public void displayAnswers() { if (answerList.isEmpty()) {
	 * System.out.println("No answers");
	 * 
	 * } else { for (int i = 0; i < answerList.size(); i++) {
	 * System.out.println(answerList.get(i).id + ": " + answerList.get(i).answer);
	 * System.out.println("----------------------"); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
}
