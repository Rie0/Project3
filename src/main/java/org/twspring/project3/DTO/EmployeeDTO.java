package org.twspring.project3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    @NotEmpty(message = "Position cannot be empty")
    private String position;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary cannot be a zero or a negative number")
    private double salary;
}
