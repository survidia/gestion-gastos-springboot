package survidia.expensemanager.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import survidia.expensemanager.model.Expense;
import survidia.expensemanager.repository.ExpenseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllExpenses_ReturnsList() {
        Expense expense = Expense.builder().idExpense(1L).name("Test").description("D").amount(10.0).date("2023-10-01").category("Food").build();
        when(expenseRepository.findAll()).thenReturn(Arrays.asList(expense));

        List<Expense> result = expenseService.findAllExpenses();

        assertEquals(1, result.size());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void findExpenseById_ReturnsExpense() {
        Expense expense = Expense.builder().idExpense(1L).name("Test").description("D").amount(10.0).date("2023-10-01").category("Food").build();
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        Optional<Expense> result = expenseService.findExpenseById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdExpense());
        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    void saveExpense_SavesAndReturnsExpense() {
        Expense expense = Expense.builder().idExpense(1L).name("Test").description("D").amount(10.0).date("2023-10-01").category("Food").build();
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense result = expenseService.saveExpense(expense);

        assertEquals(expense, result);
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void deleteExpense_CallsRepository() {
        doNothing().when(expenseRepository).deleteById(1L);

        expenseService.deleteExpense(1L);

        verify(expenseRepository, times(1)).deleteById(1L);
    }
}