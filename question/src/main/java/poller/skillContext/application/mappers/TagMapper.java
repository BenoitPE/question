package poller.skillContext.application.mappers;

import poller.skillContext.application.dtos.TagDTO;
import poller.skillContext.domain.model.Tag;

public class TagMapper {
    public static TagDTO toDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        tagDTO.setCategory(CategoryMapper.toDTO(tag.getCategory()));
        return tagDTO;
    }
}
