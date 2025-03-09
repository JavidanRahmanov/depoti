package com.cavidanrahmanov.depoti.entity;

public enum MidCategory {
    AVTOMOBİLLƏR(HeadCategory.NƏQLİYYAT),
    MOTOSİKLETLƏR(HeadCategory.NƏQLİYYAT),
    EHTİYAT_HİSSƏLƏRİ(HeadCategory.NƏQLİYYAT),
    MƏNZİLLƏR(HeadCategory.DAŞINMAZ_ƏMLAK),
    EV_VƏ_BAĞLAR(HeadCategory.DAŞINMAZ_ƏMLAK),
    TELEFONLAR(HeadCategory.ELEKTRONİKA),
    NOUTBUKLAR(HeadCategory.ELEKTRONİKA),
    MƏTBƏX_TEXNİKASI(HeadCategory.MƏİŞƏT_TEXNİKASI);

    private final HeadCategory headCategory;

    MidCategory(HeadCategory headCategory) {
        this.headCategory = headCategory;
    }

    public HeadCategory getHeadCategory() {
        return headCategory;
    }
}

