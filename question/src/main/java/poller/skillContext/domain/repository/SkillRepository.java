package poller.skillContext.domain.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import poller.skillContext.domain.model.Category;
import poller.skillContext.domain.model.Skill;
import poller.skillContext.domain.model.Tag;

@RepositoryRestResource(collectionResourceRel = "skill", path = "skill")
public interface SkillRepository {

    Category GetCategories();
    Category GetCategoryById(@Param("id") long id);
    Category AddCategory(@Param("id") long id ,@Param("value") String value );
    Category DeleteCategoryById(@Param("id") long id);
    Category UpdateCategoryById(@Param("id") long id,@Param("value") String value);
    Tag GetTags();
    Tag GetTagById(@Param("id") long id);
    Tag AddTag(@Param("id") long id ,@Param("name") String name,@Param("category")Category category);
    Tag UpdateTagById(@Param("id") long id ,@Param("name") String name,@Param("category")Category category);
    Tag DeleteTagById(@Param("id") long id);
    Skill GetSkills();
    Skill AddSkill(@Param("name") long name , @Param("category")Category category, @Param("Tag")Tag tag );



/*- GetCategories(): List<Category>
- GetCategoryById(int id): Category
- AddCategory(int id, string value): Category
- DeleteById(int id): bool
- UpdateById(int id, string value): Category
- GetTags(): List<Tag>
- GetTagById(int id): Tag
- AddTag(int id, string value, int categoryId): Tag
- DeleteById(int id): bool
- UpdateById(int id, string value, int categoryId): Tag
+ GetSkills(): List<Skill>
+ AddSkill(string, categoryName, string tagName): Skill
*/

}
