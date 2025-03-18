package fr.cszw.tddevwebavance2025.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Light {

    private Long id;
    private Boolean state;
    private String name;

}
