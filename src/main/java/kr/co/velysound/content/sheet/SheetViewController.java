package kr.co.velysound.content.sheet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SheetViewController {
    @RequestMapping("/sheet")
    public String sheet(Model model) {
        model.addAttribute("bbsTy", "NTC");
        model.addAttribute("modalPage", "bbsData");
        return "/sheet/sheetMain";
    }

    @RequestMapping("/sheet/{sheetId}")
    public String sheetDetail(Model model, @PathVariable String sheetId) {
        model.addAttribute("test", sheetId);
        model.addAttribute("sheetId", "vely!");
        return "/sheet/sheetDetail";
    }
}
