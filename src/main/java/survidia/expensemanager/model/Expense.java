package survidia.expensemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_expense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExpense;

    @Column(name = "name", nullable = false, length = 30)
    @NotBlank(message = "El nombre del gasto no puede estar vacío")
    @Size(min = 1, max = 50, message = "El nombre del gasto debe tener entre 1 y 50 caracteres")
    private String name;

    @Column(name = "description", nullable = false, length = 200)
    @NotBlank(message = "La descripción del gasto no puede estar vacía")
    @Size(min = 1, max = 200, message = "La descripción del gasto debe tener entre 1 y 200 caracteres")
    private String description;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "La cantidad del gasto no puede ser nulo")
    @Digits(integer = 10, fraction = 2, message = "Formato inválido para la cantidad del gasto")
    @DecimalMin(value = "0.00", message = "El monto debe ser positivo")
    private BigDecimal amount;

    @Column(name = "date", nullable = false, length = 10)
    @NotBlank(message = "La fecha del gasto no puede estar vacía")
    private String date;

    @Column(name = "category", nullable = false, length = 30)
    @NotBlank(message = "La categoría del gasto no puede estar vacía")
    private String category;
}
