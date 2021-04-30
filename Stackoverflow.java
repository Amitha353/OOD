class User {
	int questId;
	Search searchObj;
	
	public List<Question> getQuestions(String searchString);
}

class Member extends User {
	Account account;
	List<Badge> badges;
	
	public void addQuestion(Question question);
	public void addComment(Entity entity, Comment comment);
	public void addAnswer(Question question, Answer answer);
	public void vote(Entity entity, VoteType voteType);
	public void addTag(Question question, Tag tag);
	public void flag(Entity entity);
	public List<Badge> getBadges();
}

class Account {
	String name;
	Address address;
	int accountId;
	String userName;
	String password;
	String email;
	
	AccountStatus accountStatus;
	int reputation;
}

class Moderator extends Member {
	public Boolean closeQuestion(Question question);
	public Boolean restoreQuestion(Question question);
}

class Admin extends Member {
	public Boolean blockMember(Member member);
	public Boolean unblockMember(Member member);
}

public enum AccountStatus {
	BLOCKED, ACTIVE, CLOSED;
}

public enum VoteType {
	upVOTE, DOWNVOTE, CLOSEVOTE, DELETEVOTE;
}

public class Badge {
	String name;
	String description;
}

public class Entity {
	int entityId;
	Member created;
	Map<VoteType, Integer> votes;
	Date createdDatel
	List<Comment> comments;
	
	public boolean flagEntity(Member member);
	public boolean voteEntity(VoteType voteType);
	public boolean addComment(Comment comment);
}

public class Comment extends Entity {
	String message;
}

public class Question extends Entity {
	List<EditHistory> editHistoryList;
	List<Answer> answerList;
	List<Tag> tags;
	String title;
	String description;
	QuestionStatus status;
	
	public boolean addQuestion();
	public boolean addTag(Tag tag);
}

public class Answer extends Entity {
	String answer;
	Boolean isAccepted;
	public boolean addAnswer(Question question);
}

public enum QuestionStatus {
	ACTIVE, BOUNTIED, CLOSED, FLAGGED;
}

public class Tag {
	String name;
	String description;
}

public class EditHistory {
	int editHistoryId;
	Member creator;
	Date creationDate;
	Question prevQuestion;
	Question updatedQuestion;
}