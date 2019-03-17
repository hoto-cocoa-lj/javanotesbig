package s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@Autowired
	private Environment env;

	@RequestMapping("/info1")
	public String info1() {
		return "HelloWorld~~" + env.getProperty("url");
	}

	@RequestMapping("/info")
	public String info() {
		return "HelloWorld";
	}
}