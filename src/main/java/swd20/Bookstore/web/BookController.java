package swd20.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookstoreRepository;
import swd20.Bookstore.domain.Category;
import swd20.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	
	@Autowired
	private BookstoreRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getBook(Model model) {
		return "/hello";
	}
	@RequestMapping(value= "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
				
		return "booklist"; //html tiedoston nimi
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
		
	}
	//Lisäyslomake
	@RequestMapping(value="/addnewbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	@RequestMapping(value="/savebook", method =RequestMethod.POST)
	public String saveBook(Book book) {
		brepository.save(book);
		return "redirect:booklist";
		
	}
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", brepository.findById(id).get());		
		return "updatebook";
	}
	
	@PostMapping("/update/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book,BindingResult result, Model model) {
		if(result.hasErrors()) {
		book.setId(id);		
		return "updatebook";
		}
		brepository.save(book);
		return "redirect: booklist";
		}
	@RequestMapping(value= "/categorylist")
	public String categoryList(Model model) {
		model.addAttribute("categories", crepository.findAll());
		
		return "categorylist";
	
}
	@RequestMapping(value="/addnewcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}

@RequestMapping(value="/savecategory", method =RequestMethod.POST)
public String saveCategory(Category category) {
	crepository.save(category);
	
	
	return "redirect:categorylist";
}

}
