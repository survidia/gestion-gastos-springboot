package survidia.expensemanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survidia.expensemanager.model.Expense;
import survidia.expensemanager.service.impl.ExpenseServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inicio")
public class ExpenseController {
    private final ExpenseServiceImpl expenseServiceImpl;

    @Autowired
    public ExpenseController(ExpenseServiceImpl expenseServiceImpl) {
        this.expenseServiceImpl = expenseServiceImpl;
    }

    @Operation(
            summary = "Obtiene todos los gastos",
            description = "Devuelve una lista de todos los gastos registrados en el sistema."
    )
    @GetMapping("/todos")
    public List<Expense> getAllExpenses() {
        return expenseServiceImpl.findAllExpenses();
    }

    @Operation(
            summary = "Obtiene un gasto por su ID",
            description = "Devuelve un gasto específico por su ID. Si no se encuentra, devuelve un Optional vacío."
    )
    @GetMapping("/todos/{id}")
    public Optional<Expense> getExpenseById(@PathVariable Long id) {
        return expenseServiceImpl.findExpenseById(id);
    }

    @Operation(
            summary = "Crea un nuevo gasto",
            description = "Registra un nuevo gasto en el sistema."
    )
    @PostMapping("/crear")
    public Expense saveExpense(@Valid @RequestBody Expense expense) {
        return expenseServiceImpl.saveExpense(expense);
    }

    @Operation(
            summary = "Elimina un gasto por su ID",
            description = "Elimina un gasto específico por su ID. Si no se encuentra, no realiza ninguna acción."
    )
    @DeleteMapping("/eliminar/{idExpense}")
    public void deleteExpense(@PathVariable Long idExpense) {
        expenseServiceImpl.deleteExpense(idExpense);
    }


    @Operation(
            summary = "Actualiza un gasto existente",
            description = "Actualiza un gasto existente por su ID. Si el gasto no se encuentra, devuelve un mensaje de error."
    )
    @PutMapping("/actualizar/{idExpense}")
    public ResponseEntity<?> updateExpense(@PathVariable Long idExpense, @Valid @RequestBody Expense expense) {
        try {
            Optional<Expense> existingExpense = expenseServiceImpl.findExpenseById(idExpense);
            if (existingExpense.isPresent()) {
                Expense updatedExpense = existingExpense.get();
                updatedExpense.setDescription(expense.getDescription());
                updatedExpense.setAmount(expense.getAmount());
                updatedExpense.setDate(expense.getDate());
                updatedExpense.setCategory(expense.getCategory());

                // Save the updated expense
                expenseServiceImpl.saveExpense(updatedExpense);
                return ResponseEntity.ok(updatedExpense);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado con el id: " + idExpense);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar: " + e.getMessage());
        }
    }
}
