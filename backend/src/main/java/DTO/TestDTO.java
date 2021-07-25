package DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
