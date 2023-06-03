package poller.skillContext.domain.service.interfaces;

import poller.questionContext.domain.model.Question;
import poller.skillContext.domain.model.Category;
import poller.skillContext.domain.model.Skill;
import poller.skillContext.domain.model.Tag;

import java.util.List;

public interface SkillService {

    //region Category
    public void setCategory(final Skill skill , final Category category);

    public void updateCategory(final Category category, final String name, final Tag tag);

    public void deleteCategory(final Category category);
    //endregion

    //region Tag
    public void setTag(final Skill skill, final Tag tag);

    public void updateTag(final Tag tag, final String name, final Category category);

    public void deleteTag(final Tag tag);
    //endregion

    //region Skill agregate
    public void updateSkill(final Skill skill, final Tag tag, final Category category);

    public Skill findSkillById(final Long id);

    public List<Skill> findAllSkill();

    public Skill CreateSkill(final String name, final Tag tag, final Category category);

    //endregion
}
