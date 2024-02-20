package com.project.book.service;

import com.project.book.entity.Author;
import com.project.book.entity.Book;
import com.project.book.entity.Domain;
import com.project.book.entity.PublicationDetails;
import com.project.book.exception.BookNotFoundException;
import com.project.book.repository.AuthorRepository;
import com.project.book.repository.BookRepository;
import com.project.book.repository.DomainRepository;
import com.project.book.repository.PublicationRepository;
import com.project.book.request.AuthorRequest;
import com.project.book.request.DomainRequest;
import com.project.book.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookServiceInterface{
    @Autowired
    private BookRepository repository;

    @Autowired
    private PublicationRepository publication_repo;

    @Autowired
    private AuthorRepository author_repo;

    @Autowired
    private DomainRepository domain_repo;

    public Book saveBook(Request request) {
        Book book = Book.details(0,request.getName(),request.getAuthor());
        return repository.save(book);
    }

    public Book getBookbyId(Integer id){
        return repository.findById(id).orElse(null);
    }

    public String deleteBookById(Integer id) {
        repository.deleteById(id);
        return "Deleted Book" + id;
    }

    public Book updateBookByName(Book book) throws BookNotFoundException {
        Book book_details = repository.findByName(book.getName());
        if (book_details != null) {
            book_details.setName(book.getName());
            book_details.setAuthor(book.getAuthor());
            return repository.save(book_details);
        }else{
            throw new BookNotFoundException("Book Not Found");
        }
    }

    public PublicationDetails SavePublicationDetails(Request request){
        Book book = Book.details(0,request.getName(),request.getAuthor());
        PublicationDetails details = PublicationDetails.builder()
                .id(0)
                .published_by(request.getPublished_by())
                .no_of_copies(request.getNo_of_copies())
                .book(book)
                .build();
        return publication_repo.save(details);
    }

    public Author SaveAuthorDetails(AuthorRequest request){
        Author author = Author.builder()
                .first_name(request.getFirst_name())
                .last_name(request.getLast_name())
                .book(request.getBooks())
                .build();
        return author_repo.save(author);
    }

    public Domain SaveDomainDetails(DomainRequest request) {
        Domain domain = Domain.builder()
                .domain_name(request.getDomain_name())
                .books(request.getBooks())
                .build();
        return domain_repo.save(domain);
    }

    @Override
    public List<Book> GetBookDetails() {
        Pageable page = PageRequest.of(0,3);
        return repository.findAll(page).getContent();
    }
}
