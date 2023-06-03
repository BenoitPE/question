package poller.skillContext.domain.service.impl;

import poller.skillContext.domain.model.Category;
import poller.skillContext.domain.model.Skill;
import poller.skillContext.domain.model.Tag;
import poller.skillContext.domain.repository.SkillRepository;
import poller.skillContext.domain.service.interfaces.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    //region Category
    public void setCategory(final Skill skill, final Category category){
        var s = skillRepository.GetSkillById(skill.getId());
        s.setCategory(category);
    }

    public void updateCategory(final Category category, final String name, final Tag tag){
        var c = skillRepository.GetCategoryById(category.getId());
        c.setValue(name);
        c.setIdTag(tag.getId());
    }

    public void deleteCategory(final Category category){
        skillRepository.DeleteCategoryById(category.getId());
    }
    //endregion

    //region Tag
    public void setTag(final Skill skill, final Tag tag){
        var s = skillRepository.GetSkillById(skill.getId());
        s.setTag(tag);
    };

    public void updateTag(final Tag tag, final String name, final Category category){
        var t = skillRepository.GetTagById(tag.getId());
        t.setName(name);
        t.setCategory(category);
    };

    public void deleteTag(final Tag tag){
        skillRepository.DeleteTagById(tag.getId());
    };
    //endregion

    //region Skill agregate
    public void updateSkill(final Skill skill, final Tag tag, final Category category){
        var s = skillRepository.GetSkillById(skill.getId());
        s.setTag(tag);
        s.setCategory(category);
    };

    public Skill findSkillById(final Long id){
        return skillRepository.GetSkillById(id);
    };

    public List<Skill> findAllSkill(){
        return skillRepository.GetSkills();
    };

    public Skill CreateSkill(final String name, final Tag tag, final Category category){
        Skill skill = skillRepository.AddSkill(name, category, tag);
        return skill;
    };

    //endregion
}
