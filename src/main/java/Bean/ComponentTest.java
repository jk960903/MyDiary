package Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentTest implements IComponentTest{
	
	@Override
	public void run() {
		System.out.println("빈이당!!");
	}
}
