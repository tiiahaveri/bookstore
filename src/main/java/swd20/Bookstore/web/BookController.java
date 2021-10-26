package swd20.Bookstore.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	

	
    
	@RequestMapping(value= "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
				
		return "booklist"; //html tiedoston nimi
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
		
	}
	//Lisäyslomake
	@RequestMapping(value="/addnewbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	@PostMapping("savebook")
	public String saveBook(@ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			if (bindingResult.getFieldError().getField().equalsIgnoreCase("price")) {
				bindingResult.rejectValue("price", "err.price", "Check price format");
			} else {
				System.out.println("Jokin muu vika");
			}
			return "redirect:addBook";

		}
		brepository.save(book);
		return "redirect:booklist";
	
	}
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Book book=  brepository.findById(id).get();	
		model.addAttribute("book", book);
		model.addAttribute("categories", crepository.findAll());
		return "updatebook";
	}
	
	@PostMapping("/update/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
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
	//RESTful service to get all students
	// Java-kielinen Student-luokan oliolista muunnetaan JSON-opiskelijalistaksi ja 
	// lähetetään web-selaimelle vastauksena
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {	
    return (List<Book>) brepository.findAll();
}    

// RESTful service to get student by id
/*@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long studentId) {	
	return brepository.findById(studentId);
}      */

// RESTful service to save new student
/*@RequestMapping(value="/books", method = RequestMethod.POST)
public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
	return brepository.save(book); */
}


