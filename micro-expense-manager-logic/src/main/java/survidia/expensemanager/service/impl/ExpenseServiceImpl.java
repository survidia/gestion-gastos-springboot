package survidia.expensemanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survidia.expensemanager.model.Expense;
import survidia.expensemanager.repository.ExpenseRepository;
import survidia.expensemanager.service.ExpenseService;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl  implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> findExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}