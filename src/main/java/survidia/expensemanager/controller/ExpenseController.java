package survidia.expensemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survidia.expensemanager.model.Expense;
import survidia.expensemanager.service.impl.ExpenseServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gastos")
public class ExpenseController {
    private final ExpenseServiceImpl expenseServiceImpl;

    @Autowired
    public ExpenseController(ExpenseServiceImpl expenseServiceImpl) {
        this.expenseServiceImpl = expenseServiceImpl;
    }

    @GetMapping("/todos")
    public List<Expense> getAllExpenses() {
        return expenseServiceImpl.findAllExpenses();
    }

    @GetMapping("/todos/{id}")
    public Optional<Expense> getExpenseById(Long id) {
        return expenseServiceImpl.findExpenseById(id);
    }

    @PostMapping("/crear")
    public Expense saveExpense(Expense expense) {
        return expenseServiceImpl.saveExpense(expense);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseServiceImpl.deleteExpense(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        try {
            Optional<Expense> existingExpense = expenseServiceImpl.findExpenseById(id);
            if (existingExpense.isPresent()) {
                Expense updatedExpense = existingExpense.get();
                updatedExpense.setDescription(expense.getDescription());
                updatedExpense.setAmount(expense.getAmount());
                updatedExpense.setDate(expense.getDate());
                updatedExpense.setCategory(expense.getCategory());

                // Save the updated expense
                expenseServiceImpl.saveExpense(updatedExpense);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense updated successfully");
            } else {
                throw new RuntimeException("Expense not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating expense: " + e.getMessage());
        }
    }
}
