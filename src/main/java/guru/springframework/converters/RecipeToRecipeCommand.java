package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
	private final CategoryToCategoryCommand categoryConverter;
	private final IngredientToIngredientCommand ingredientConverter;
	private final NotesToNotesCommand notesConverter;

	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter,
			IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
		this.categoryConverter = categoryConverter;
		this.ingredientConverter = ingredientConverter;
		this.notesConverter = notesConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null)
			return null;

		final RecipeCommand command = new RecipeCommand();
		command.setId(source.getId());
		command.setCookTime(source.getCookTime());
		command.setPrepTime(source.getPrepTime());
		command.setDescription(source.getDescription());
		command.setDirections(source.getDirections());
		command.setDifficulty(source.getDifficulty());
		command.setNotes(notesConverter.convert(source.getNotes()));
		command.setServings(source.getServings());
		command.setSource(source.getSource());
		command.setUrl(source.getUrl());
		if (source.getCategories() != null && source.getCategories().size() > 0)
			source.getCategories()
					.forEach(category -> command.getCategories().add(categoryConverter.convert(category)));

		if (source.getIngredients() != null && source.getIngredients().size() > 0)
			source.getIngredients()
					.forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));

		return command;
	}

}