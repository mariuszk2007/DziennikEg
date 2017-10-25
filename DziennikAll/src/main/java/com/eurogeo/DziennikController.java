package com.eurogeo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DziennikController {
	@Autowired 
	DziennikDao dziennikDao;
	@Autowired
	UserDao userDao;
	
	@CrossOrigin
	 @RequestMapping("/getall")
	    public String getAll(Model model) {
		 System.out.println("1");
		 model.addAttribute("dziennikElement" ,getAllElements());
		return "jsonTemplate";
	    }
	
	@CrossOrigin
	 @RequestMapping("/getalluser")
	    public String getAllUser(Model model) {
		 
		 model.addAttribute("userElement" ,getAllUser());
		return "jsonTemplate";
	    }
	 @CrossOrigin
	 @RequestMapping("/create")
	    public String create(Model model) {
		 
	   DziennikAll dziennik = new DziennikAll();
	   LocalDate day= LocalDate.of(2017, 03, 01);
	   dziennik.setDay(day);
	   dziennik.setRobota("Jawor");
	   dziennik.setUwagi("all day");
	   
	   dziennikDao.save(dziennik);
	   model.addAttribute("dziennikList" ,getAllElements());
	return "jsonTemplate";
	     
		 
	    }
	 @CrossOrigin
	 @RequestMapping(value = "/getElement/{month}")
		public String getElementByMonth(@PathVariable("month") LocalDate day, Model model) {
		int month = day.getMonthValue();
			model.addAttribute("ZakupyMonth:",dziennikDao.findByMonth(month));
		return "jsonTemplate";
		}
	 
	 
	 private List<DziennikAll> getAllElements() {
			List<DziennikAll> dziennikList = new ArrayList<DziennikAll>();
			dziennikDao.findAll().forEach(dziennikList::add);
			System.out.println(dziennikList.toString());
			return dziennikList;
		 }
	 private List<User> getAllUser() {
			List<User> userList = new ArrayList<User>();
			userDao.findAll().forEach(userList::add);
			
			return userList;
		 }
	 @CrossOrigin
	 @RequestMapping(value = "/create/{year}/{month}/{user}")
		public String createElement(@PathVariable("year") int year, @PathVariable("month") int month,@PathVariable("user") String user,Model model) {
			
			LocalDate day = LocalDate.of(year, month,1);
			
			DziennikAll dziennikElement = new DziennikAll();
			if(dziennikDao.findByDayAndUser(day, user)== null ){
			System.out.println("Tworzenie miesiaca");
			LocalDate dayNextMonth = day.plusMonths(1);
			int daysInMonth = dayNextMonth.minusDays(1).getDayOfMonth()- day.getDayOfMonth()+1;
			System.out.println("Liczba dni w miesiÄ…cu: " + daysInMonth);
			for (int i=0; i<daysInMonth;i++){
				System.out.println("day: "+ i);
				dziennikElement = new DziennikAll();
				dziennikElement.setDay(day.plusDays(i));
				dziennikElement.setRobota("");
				dziennikElement.setUwagi("");
				dziennikElement.setMonth(day.getMonthValue());
				dziennikElement.setYear(day.getYear());
				dziennikElement.setUser(user);
				dziennikDao.save(dziennikElement);
			}
			}
			model.addAttribute("Month dodano:",getAllElements());
			return "jsonTemplate";
			
		}
		
	@CrossOrigin
	@RequestMapping(value = "/newuser")
	public String addNewUser(@RequestBody User userElementHttp, Model model) {
	User userElement = new User();
		userElement.setUserName(userElementHttp.getUserName());
		System.out.println("new user: "+userElement.getUserName());
		userDao.save(userElement);
		model.addAttribute("userElement", getAllUser());
		return "jsonTemplate";
	}
	 @CrossOrigin
	 @RequestMapping(value = "/updateday")
		public String update(@RequestBody DziennikAll dziennikElementHttp, Model model) {
			
			LocalDate day = dziennikElementHttp.getDay();
			String robota = dziennikElementHttp.getRobota();
			String uwagi = dziennikElementHttp.getUwagi();
			String user =  dziennikElementHttp.getUser();
			DziennikAll dziennikElement = new DziennikAll();
			dziennikElement = dziennikDao.findByDayAndUser(day,user);
			
			dziennikElement.setRobota(robota);
			dziennikElement.setUwagi(uwagi);
			
			dziennikDao.save(dziennikElement);
			model.addAttribute("dziennikElement",getAllElements());
			return "jsonTemplate";
		}
}
