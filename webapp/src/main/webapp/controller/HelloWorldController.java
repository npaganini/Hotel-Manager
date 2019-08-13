@Controller
public class HelloWorldController {
@RequestMapping("/")
public ModelAndView helloWorld() {
final ModelAndView mav = new ModelAndView("index"
);
mav.addObject("greeting", "PAW");
return mav;
}}
