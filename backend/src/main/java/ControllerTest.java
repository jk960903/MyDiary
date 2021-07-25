
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ControllerTest {
	
	@RequestMapping("/")
	public String Test() {
		return "test";
	}
	
	@RequestMapping(value = "/restTest")
	public String restTest(@RequestParam String str) {
		return str + " : Test Test";
	}
	
	@RequestMapping(value = "/ReadAPI")
	public void ReadAPI() {
		List<HttpMessageConverter<?>> converters =new ArrayList<>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("str", "this is Rest API");
		
		String result = restTemplate.postForObject("http://localhost:8080/SampleProject/restTest/",map,String.class);
		System.out.println(result);
	}
}
