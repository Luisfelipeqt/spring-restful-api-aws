package br.com.erudio.services;

import br.com.erudio.controllers.BooksController;
import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Books;
import br.com.erudio.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksServices {
	
	private Logger logger = Logger.getLogger(BooksServices.class.getName());
	
	@Autowired
	BooksRepository repository;

	public List<BooksVO> findAll() {

		logger.info("Finding all books!");

		var books = DozerMapper.parseListObjects(repository.findAll(), BooksVO.class);
		books
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	public BooksVO findById(Long id) {
		
		logger.info("Finding one books!");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, BooksVO.class);
		vo.add(linkTo(methodOn(BooksController.class).findAll()).withRel("List of Books"));
		return vo;
	}
	
	public BooksVO create(BooksVO books) {

		if (books == null)
		
		logger.info("Creating one books!");
		var entity = DozerMapper.parseObject(books, Books.class);
		var vo =  DozerMapper.parseObject(repository.save(entity), BooksVO.class);
		vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public BooksVO update(BooksVO books) {

		if (books == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one books!");
		
		var entity = repository.findById(books.getKey())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setAuthor(books.getAuthor());
		entity.setLaunch_date(books.getLaunch_date());
		entity.setPrice(books.getPrice());
		entity.setTitle(books.getTitle());
		
		var vo =  DozerMapper.parseObject(repository.save(entity), BooksVO.class);
		vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one book!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
