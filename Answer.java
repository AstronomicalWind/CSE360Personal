package application;

public class Answer {
	//Sets up the answer propertiers
	public String id;
	public int number;
	public String answer;
	public boolean resolved;
	public String answerNum;
	//constructor to quickly fill it in
	public Answer(int num, String id, String answerText) {
		// TODO Auto-generated constructor stub
		
		 //this.id = answerId;
	        this.answer = answerText;
	        this.number=num;
	        this.id=id;
	}
}
