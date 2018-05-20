package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrelloCard {
    private String name;
    private String description;
    private String pos;
    private String listId;

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }
}

