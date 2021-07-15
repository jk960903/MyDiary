package DTO;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
public class TestDTO {
	private String title;
	private String content;
	private String authoor;
	
	public TestDTO(String title, String content, String authoor) {
		this.title = title;
		this.content = content;
		this.authoor = authoor;
	}
}
