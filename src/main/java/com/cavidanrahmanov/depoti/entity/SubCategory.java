package com.cavidanrahmanov.depoti.entity;

public enum SubCategory {

    BMW(MidCategory.AVTOMOBİLLƏR),
    MERCEDES(MidCategory.AVTOMOBİLLƏR),
    AUDI(MidCategory.AVTOMOBİLLƏR),
    TOYOTA(MidCategory.AVTOMOBİLLƏR),
    KIA(MidCategory.AVTOMOBİLLƏR);

    private final MidCategory midCategory;

    SubCategory(MidCategory midCategory) {
        this.midCategory = midCategory;
    }

    public MidCategory getMidCategory() {
        return midCategory;
    }
}

