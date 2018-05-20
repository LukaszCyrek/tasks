package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.net.URI;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloCardDto {

@JsonProperty("name")
    private String name;

@JsonProperty("desc")
    private String description;

@JsonProperty("pos")
    private String pos;

@JsonProperty("listid")
    private String listId;

}


