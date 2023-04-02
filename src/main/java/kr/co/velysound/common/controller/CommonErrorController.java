package kr.co.velysound.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CommonErrorController implements ErrorController {
	private final String ERROR_404_PATH = "/error/error_404";
	private final String ERROR_405_PATH = "/error/error_405";

	@GetMapping("/error")
	public String viewError(HttpServletRequest request, Model model) {
		String viewPath = "/error/error";

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				log.error("404 error...");
				viewPath = ERROR_404_PATH;
			} else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				log.error("405 error...");
				viewPath = ERROR_405_PATH;
			}
			model.addAttribute("code", status.toString());
			model.addAttribute("message", httpStatus.getReasonPhrase());
		}
		return viewPath;
	}
}
