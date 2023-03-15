package kr.co.velysound;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping("/main")
	public String Hello(Model model) {
		model.addAttribute("name", "velysound");
		return "/main";
	}
}
