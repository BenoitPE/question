package poller.skillContext.domain.service;

import org.springframework.stereotype.Service;
import poller.skillContext.domain.repository.CategoryRepository;
import poller.skillContext.domain.repository.SkillRepository;
import poller.skillContext.domain.repository.TagRepository;

@Service
public class SkillService {

    /** Skill Repository. */
    private final transient SkillRepository skillRepository;

    /**
     * TagService constructor.
     * @param skillRepository a categoryRepository
     */
    public SkillService(
            final SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }


}
