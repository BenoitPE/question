package poller.skillContext.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poller.skillContext.domain.model.Category;
import poller.skillContext.domain.repository.CategoryRepository;
import poller.skillContext.domain.service.CategoryService;

/**
 * @author djer1
 *
 */
@RestController
public class CategoryController {
    /** The category service.
     */
    private final transient CategoryService categoryService;
    /** The category repository.
     */
    private final transient CategoryRepository categoryRepository;

    /** Constructor of CategoryController.
     * @param categoryService the category service
     * @param categoryRepository the category repository
     * */
    @Autowired
    public CategoryController(
            final CategoryService categoryService,
            final CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    /** updateTagFromCategory method.
     * @param idCategory the category ID
     * @param nameTag the tag name
     */
    @PostMapping("/updateTagFromCategory")
    public final void updateTagFromCategory(
            @RequestParam final long idCategory,
            @RequestParam final String nameTag) {
        final Category category = categoryRepository.findCategoryById(idCategory);
        System.out.println(category.getName());
        categoryService.setTag(category, nameTag);
        System.out.println(category.getIdTag());
        System.out.println(category.getName());
        categoryRepository.save(category);
    }

    /** deleteTagFromCategory method.
     * @param idCategory the category ID
     */
    @PostMapping("/deleteTagFromCategory")
    public final void deleteTagFromCategory(
            @RequestParam final long idCategory) {
        final Category category = categoryRepository.findCategoryById(idCategory);
        System.out.println(category.getName());
        System.out.println(category.getIdTag());
        categoryService.deleteTag(category);
        System.out.println(category.getIdTag());
        categoryRepository.save(category);
    }

    /** updateCategory method.
     * @param categoryId the category ID
     * @param tagName the tag name
     * @param categoryValue the category value
     */
    @PostMapping("/updateCategory")
    public final void updateCategory(
            @RequestParam final long categoryId,
            @RequestParam final String tagName,
            @RequestParam final String categoryValue) {
        final Category category = categoryRepository.findCategoryById(categoryId);
        categoryService.updateCategory(category, tagName, categoryValue);
        categoryRepository.save(category);
    }

    /** deleteTagById method.
     * @param categoryId the category ID
     */
    @DeleteMapping("/deleteCategoryById")
    public final void deleteTagById(@RequestParam final long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
