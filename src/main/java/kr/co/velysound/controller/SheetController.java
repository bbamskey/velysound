package kr.co.velysound.controller;

import kr.co.velysound.common.controller.CommonParamController;
import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sheet")
public class SheetController extends CommonParamController {

	@Autowired
	SheetService sheetService;

	@PostMapping("/list")
	public ModelAndView get(@RequestBody MoMap param) {
		ModelAndView modelAndView = new ModelAndView("/ajax/main/sheetList");
		modelAndView.addObject("items", sheetService.getList(param));
		return modelAndView;
	}
}
