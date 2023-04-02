package kr.co.velysound.controller;

import kr.co.velysound.common.controller.CommonParamController;
import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/video")
public class VideoController extends CommonParamController {

	@Autowired
	VideoService videoService;

	@PostMapping("/list")
	public ModelAndView getList(@RequestBody MoMap param) {
		ModelAndView modelAndView = new ModelAndView("/ajax/main/videoList");
		modelAndView.addObject("items", videoService.getList(param));
		return modelAndView;
	}
}
