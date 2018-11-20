package guru.springframework.commands;

import java.util.Set;

import guru.springframework.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {

	private Long id;
	private String description;
	private Set<RecipeCommand> recipes;
}
