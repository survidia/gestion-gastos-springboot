package survidia.expensemanager.service;

import org.springframework.stereotype.Service;
import survidia.expensemanager.model.Expense;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> findAllExpenses();
    Optional<Expense> findExpenseById(Long id);
    Expense saveExpense(Expense expense);
    void deleteExpense(Long id);
}
