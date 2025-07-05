package survidia.expensemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import survidia.expensemanager.model.Expense;
import survidia.expensemanager.service.impl.ExpenseServiceImpl;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ExpenseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ExpenseServiceImpl expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
    }

    @Test
    void getAllExpenses_ReturnsOk() throws Exception {
        Expense expense = Expense.builder()
                .idExpense(1L)
                .name("Test")
                .description("D")
                .amount(10.0)
                .date("2023-10-01")
                .category("Food")
                .build();
        when(expenseService.findAllExpenses()).thenReturn(Arrays.asList(expense));

        mockMvc.perform(get("/api/gastos/todos"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(expense))));

        verify(expenseService, times(1)).findAllExpenses();
    }

    @Test
    void saveExpense_ReturnsCreatedExpense() throws Exception {
        Expense expense = Expense.builder()
                .idExpense(1L)
                .name("Test")
                .description("D")
                .amount(10.0)
                .date("2023-10-01")
                .category("Food")
                .build();
        when(expenseService.saveExpense(any(Expense.class))).thenReturn(expense);

        mockMvc.perform(post("/api/gastos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expense)));

        verify(expenseService, times(1)).saveExpense(any(Expense.class));
    }

    @Test
    void getExpenseById_ReturnsExpense() throws Exception {
        Expense expense = Expense.builder()
                .idExpense(1L)
                .name("Test")
                .description("Desc")
                .amount(20.0)
                .date("2023-10-02")
                .category("Food")
                .build();
        when(expenseService.findExpenseById(1L)).thenReturn(java.util.Optional.of(expense));

        mockMvc.perform(get("/api/gastos/todos/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expense)));

        verify(expenseService, times(1)).findExpenseById(1L);
    }

    @Test
    void deleteExpense_ReturnsOk() throws Exception {
        doNothing().when(expenseService).deleteExpense(1L);

        mockMvc.perform(delete("/api/gastos/eliminar/1"))
                .andExpect(status().isOk());

        verify(expenseService, times(1)).deleteExpense(1L);
    }

    @Test
    void updateExpense_ReturnsUpdatedExpense() throws Exception {
        Expense existing = Expense.builder()
                .idExpense(1L)
                .name("Test")
                .description("Old")
                .amount(10.0)
                .date("2023-10-01")
                .category("Food")
                .build();
        Expense updated = Expense.builder()
                .idExpense(1L)
                .name("Test")
                .description("New")
                .amount(15.0)
                .date("2023-10-03")
                .category("Travel")
                .build();

        when(expenseService.findExpenseById(1L)).thenReturn(java.util.Optional.of(existing));
        when(expenseService.saveExpense(any(Expense.class))).thenReturn(updated);

        mockMvc.perform(put("/api/gastos/actualizar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updated)));

        verify(expenseService, times(1)).findExpenseById(1L);
        verify(expenseService, times(1)).saveExpense(any(Expense.class));
    }
}