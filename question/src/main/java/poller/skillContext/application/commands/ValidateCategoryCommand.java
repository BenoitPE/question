package poller.skillContext.application.commands;

public class ValidateCategoryCommand {
    private long categoryId;

    public ValidateCategoryCommand() {
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}