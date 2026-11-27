package it.univaq.disim.lpo.unifymusic.model;

import java.time.LocalDateTime;

public record VoceCronologia (
    Brano brano,
    LocalDateTime dataOra,
    int durataAscoltata
    
){

}
