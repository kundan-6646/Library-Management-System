package com.example.student_library.Services;

import com.example.student_library.DTOs.IssueBookRequestDto;
import com.example.student_library.Enums.CardStatus;
import com.example.student_library.Enums.TransactionStatus;
import com.example.student_library.Models.Book;
import com.example.student_library.Models.Card;
import com.example.student_library.Models.Transactions;
import com.example.student_library.Repositories.BookRepository;
import com.example.student_library.Repositories.CardRepository;
import com.example.student_library.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        //Get the Book Entity and Card Entity ??? Why do we need this
        //We are this bcz we want to set the Transaction attributes...

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();


        //Final goal is to make a transaction Entity, set its attribute
        //and save it.
        Transactions transaction = new Transactions();

        //Setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssue(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        //attribute left is success/Failure
        //Check for validations
        if(book==null || book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");

        }

        if(card==null || (card.getStatus()!= CardStatus.ACTIVATED)){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw  new Exception("Card is not valid");
        }



        //We have reached a success case now.

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);


        //set attributes of book
        book.setIssued(true);
        //Btw the book and transaction : bidirectional
        List<Transactions> listOfTransactionForBook = book.getTransactions();
        listOfTransactionForBook.add(transaction);
        book.setTransactions(listOfTransactionForBook);


        //I need to make changes in the card
        //Book and the card
        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);

        for(Book b: issuedBooksForCard){
            System.out.println(b.getName());
        }

        //Card and the Transaction : bidirectional (parent class)
        List<Transactions> transactionsListForCard = card.getTransactions();
        transactionsListForCard.add(transaction);
        card.setTransactions(transactionsListForCard);

        //save the parent.
        cardRepository.save(card);
        //automatically by cascading : book and transaction will be saved.
        //Saving the parent

        return "Book issued successfully";
    }

    public String returnBook(IssueBookRequestDto issueBookRequestDto) throws ParseException{
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        List<Transactions> bookAndCardTransactions = transactionRepository.getTransactionsForBookAndCard(bookId, cardId);

        //Getting last date when book was issued
        Date issuedDate = bookAndCardTransactions.get(0).getTransactionDate();
        for (Transactions transaction: bookAndCardTransactions) {
            if (transaction.getTransactionStatus() != TransactionStatus.ISSUED) continue;

            Date d1 = dateExtractor(transaction.getTransactionDate());
            Date d2 = dateExtractor(issuedDate);
            if(d2.compareTo(d1) < 0)
                issuedDate = transaction.getTransactionDate();

        }

        //calculating fine
        long days = getDifferenceDays(issuedDate, new Date());
        int fine = 0;
        if(days > 15) {
            fine = (int) (10*(days-15));
        }


        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();

        //Creating new return book transaction
        Transactions transaction = new Transactions();

        //Setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssue(false);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //set attributes of book
        book.setIssued(false);
        //Btw the book and transaction : bidirectional
        List<Transactions> listOfTransactionForBook = book.getTransactions();
        listOfTransactionForBook.add(transaction);
        book.setTransactions(listOfTransactionForBook);
        book.setCard(null);

        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.remove(book);
        card.setBooksIssued(issuedBooksForCard);


        //Card and the Transaction : bidirectional (parent class)
        List<Transactions> transactionsListForCard = card.getTransactions();
        transactionsListForCard.add(transaction);
        card.setTransactions(transactionsListForCard);
        card.setFine(card.getFine()+fine);

        //save the parent.
        cardRepository.save(card);
        //automatically by cascading : book and transaction will be saved.
        //Saving the parent

        return "Book Returned successfully and fine: " + card.getFine();
    }

    private long getDifferenceDays(Date d1, Date d2) throws ParseException {

        d1 = dateExtractor(d1);
        d2 = dateExtractor(d2);

        long diff = Math.abs(d2.getTime() - d1.getTime());
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    private Date dateExtractor(Date date) throws ParseException{
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(formatter.format(date));
    }
}
