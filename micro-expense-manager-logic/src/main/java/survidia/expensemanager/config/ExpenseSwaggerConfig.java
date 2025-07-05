package survidia.expensemanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Expense Manager API",
                version = "1.0",
                description = "API de gestión de gastos"
        )
)
public class ExpenseSwaggerConfig {
}
