package poller.skillContext.domain.service;

import poller.skillContext.domain.model.Category;
import poller.skillContext.domain.model.Tag;
import poller.skillContext.domain.repository.TagRepository;
import org.springframework.stereotype.Service;

/**
 * CategoryService class.
 */
@Service
public class CategoryService {

    /** Tag Repository. */
    private final transient TagRepository tagRepository;

    /** CategoryService constructor.
     * @param tagRepository the tag repository
     */
    public CategoryService(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /** Tag setter.
     * @param category the tag category
     * @param name the tag name */
    public void setTag(final Category category, final String name) {
        final Tag tag = tagRepository.findTagsByName(name);
        category.setIdTag(tag.getId());
    }

    /** updateCategory method.
     * @param category the category
     * @param name the tag name
     * @param categoryValue the category value*/
    public void updateCategory(
            final Category category,
            final String name,
            final String categoryValue) {
        final Tag tag = tagRepository.findTagsByName(name);
        category.setIdTag(tag.getId());
        category.setValue(categoryValue);
    }

    /** deleteTag method.
     * @param category the category*/
    public void deleteTag(final Category category) {
        category.setIdTag(0);
    }
}
