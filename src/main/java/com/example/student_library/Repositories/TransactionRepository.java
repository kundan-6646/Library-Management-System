package com.example.student_library.Repositories;

import com.example.student_library.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

    @Query(value = "select * from transactions where book_id=:bookId and card_id=:cardId", nativeQuery = true)
    List<Transactions> getTransactionsForBookAndCard(int bookId, int cardId);
}
