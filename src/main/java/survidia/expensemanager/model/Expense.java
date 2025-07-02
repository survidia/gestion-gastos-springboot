package survidia.expensemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

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

    @Column(name = "name", nullable = false)
    @NotBlank(message = "El nombre del gasto no puede estar vacío")
    @Size(min = 1, max = 50, message = "El nombre del gasto debe tener entre 1 y 50 caracteres")
    private String name;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "La descripción del gasto no puede estar vacía")
    @Size(min = 1, max = 200, message = "La descripción del gasto debe tener entre 1 y 200 caracteres")
    private String description;

    @Column(name = "amount", nullable = false)
    @NotNull(message = "La cantidad del gasto no puede ser nulo")
    @Positive(message = "La cantidad del gasto debe mayor a cero")
    private double amount;

    @Column(name = "date", nullable = false)
    @NotBlank(message = "La fecha del gasto no puede estar vacía")
    private String date;

    @Column(name = "category", nullable = false)
    @NotBlank(message = "La categoría del gasto no puede estar vacía")
    private String category;
}
