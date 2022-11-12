package c;


public class QuestionAnswer {
	
	private int id;
	private String question;
	private String answer;
	
	public QuestionAnswer(int id, String q, String a) {
		this.id = id;
		this.question = q;
		this.answer = a;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String q) {
		question = q;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String a) {
		answer = a;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}