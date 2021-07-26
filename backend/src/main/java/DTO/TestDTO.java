package DTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
public class TestDTO {
	private String userName;
	private String userID;

	@Autowired
	public TestDTO(String userID, String userName) {
		this.userID = userID;
		this.userName = userName;
	}
}
