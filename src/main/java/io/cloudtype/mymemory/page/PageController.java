package io.cloudtype.mymemory.page;

import io.cloudtype.mymemory.memo.Memo;
import io.cloudtype.mymemory.memo.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class PageController {
    private final MemoService memoService;

    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String goLogin(Model model) {
        return "content/login";
    }

    @RequestMapping(value = "/home", method= RequestMethod.GET)
    public String goHome(Model model) {
        return "content/home";
    }

    @RequestMapping(value = "/write/{y}/{m}/{d}", method= RequestMethod.GET)
    public String goWrite(
            Model model, @PathVariable Integer y, @PathVariable Integer m, @PathVariable Integer d) {

        if (y == null || m == null || d == null) return "redirect:/content/home";
        String[] stringDates = getDateAndWeekday(y, m, d);
        model.addAttribute("date", stringDates[0]);
        model.addAttribute("memoDate", stringDates[1]);
        return "content/write";
    }

    @RequestMapping(value = "/join", method= RequestMethod.GET)
    public String goJoin(Model model) {
        return "content/join";
    }

    public String[] getDateAndWeekday(Integer year, Integer month, Integer day) {
        LocalDate date = LocalDate.of(year, month, day);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy.MM.dd (E)", Locale.KOREAN);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return new String[] {date.format(formatter1), date.format(formatter2)};
    }
}
