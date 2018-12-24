package repo;

public final class Repositories {


    private static final PlayerRepo PLAYER_REPOSITORY = new H2PlayerRepo();
    private static final HeroRepo HERO_REPOSITORY = new H2HeroRepo();
    private static final AchievementRepo ACHIEVEMENT_REPOSITORY = new H2AchievementRepo();
    private static final UnlockableRepo UNLOCKABLE_REPOSITORY = new H2UnlockableRepo();
    private static final EraRepo ERA_REPOSITORY = new H2EraRepo();
    private static final EventRepo EVENT_REPOSITORY = new H2EventRepo();
    private static final BlockRepo BLOCK_REPOSITORY = new H2BlockRepo();
    private static final StatRepo STAT_REPOSITORY = new H2StatRepo();
    private static final QuestRepo QUEST_REPOSITORY = new H2QuestRepo();


    private Repositories() {
    }

    public static PlayerRepo getPlayerRepository() {
        return PLAYER_REPOSITORY;
    }

    public static HeroRepo getHeroRepository() {
        return HERO_REPOSITORY;
    }

    public static UnlockableRepo getUnlockableRepository() {
        return UNLOCKABLE_REPOSITORY;
    }

    public static EraRepo getEraRepository() {
        return ERA_REPOSITORY;
    }

    public static BlockRepo getBlockRepository() {
        return BLOCK_REPOSITORY;
    }

    public static StatRepo getStatRepository() {
        return STAT_REPOSITORY;
    }

    public static QuestRepo getQuestRepository() {
        return QUEST_REPOSITORY;
    }

    public static AchievementRepo getAchievementRepository() {
        return ACHIEVEMENT_REPOSITORY;
    }

    public static EventRepo getEventRepository() {
        return EVENT_REPOSITORY;
    }
}
