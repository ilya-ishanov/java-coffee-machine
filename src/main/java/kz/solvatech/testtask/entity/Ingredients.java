package kz.solvatech.testtask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long quantity;
}
