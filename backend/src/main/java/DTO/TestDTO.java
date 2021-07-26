package DTO;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
public class TestDTO {
	private String userName;
	private String userID;
	
	public TestDTO(String userID, String userName) {
		this.userID = userID;
		this.userName = userName;
	}
}
