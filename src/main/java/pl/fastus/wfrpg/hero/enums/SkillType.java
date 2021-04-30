package pl.fastus.wfrpg.hero.enums;

public enum SkillType {
    BASIC("Podstawowa"), ADVANCED("Zaawansowana");

    private final String name;

    SkillType(String name) {
        this.name = name;
    }
}
