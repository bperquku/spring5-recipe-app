package guru.springframework.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private BigDecimal amount;

	@ManyToOne
	private Recipe recipe;

	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = uom;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = uom;
		this.recipe = recipe;
	}
}
