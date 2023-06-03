package poller.skillContext.application.mappers;

import poller.skillContext.application.dtos.SkillDTO;
import poller.skillContext.domain.model.Skill;

public class SkillMapper {
    public static SkillDTO toDTO(Skill skill) {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setId(skill.getId());
        skillDTO.setName(skill.getName());
        skillDTO.setCategory(CategoryMapper.toDTO(skill.getCategory()));
        skillDTO.setTag(TagMapper.toDTO(skill.getTag()));
        return skillDTO;
    }
}
