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
	//these are attributes of the queston class
	
	public Question(String questionId, String questionText, String description, String numb) {
		// TODO Auto-generated constructor stub
		//This constructor was auto made in eclipse and allows us to quickly fill in the values from the main file
		 this.id = questionId;
	        this.question = questionText;
	        this.description = description;
	        this.answered = false;
	        this.resolved=false;
	        this.number=numb;
	        
	}
	
	public void addAnswer(Answer answer) {
        answerList.add(answer);
        answered = true; // Mark the question as answered
    }


	//OLD CODE IGNORE - KEPT FOR STORAGE PURPOSES
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
