package survidia.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import survidia.expensemanager.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
