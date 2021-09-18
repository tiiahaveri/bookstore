package swd20.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.Bookstore.domain.BookstoreRepository;

@Controller
public class BookController {
	@Autowired
	private BookstoreRepository repository;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getBook(Model model) {
		return "/hello";
	}
	@RequestMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
				
		return "booklist"; //html tiedoston nimi
	}

}
