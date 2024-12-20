package com.example.depositrateservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum DocumentsFRONT {

    ДОКУМЕНТ_ДЛЯ_ПОДПИСАНИЯ(207, "Документ для подписания"),
    ДОКУМЕНТ_ДЛЯ_ЗАГРУЗКИ(111, "Документ для загрузки"),
    МИГРАЦИОННАЯ_КАРТА(453, "Миграционная карта"),
    НАЛОГОВАЯ_СПРАВКА(566, "Налоговая справка"),
    ПРОЧИЕ_ДОКУМЕНТЫ(3, "Прочие документы"),
    ВЫПИСКА_ЦЕНТРАЛЬНОГ_БАНКА(5342, "Выписка из центрального банка"),
    ДОКУМЕНТ_РЕЗИДЕНТА(400, "Документ резидента");

    private final Integer documentId;
    private final String documentName;
}
