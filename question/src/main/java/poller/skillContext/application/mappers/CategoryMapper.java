package poller.skillContext.application.mappers;

import poller.skillContext.application.dtos.CategoryDTO;
import poller.skillContext.domain.model.Category;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
