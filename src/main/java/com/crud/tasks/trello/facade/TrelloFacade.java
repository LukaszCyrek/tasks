package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMaper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TrelloFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloMaper trelloMaper;

    @Autowired
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMaper.mapToBoards(trelloService.fetchTrelloBorads());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
       return trelloMaper.mapToBoardsDto(filteredBoards);
    }
    public CreatedTrelloCardDto createdCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMaper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        if(trelloCard.getName().contains("test")) {
            LOGGER.info("Someone is testing my application");
        } else {
            LOGGER.info("Seems that my application is used in proper way");
        }
        return trelloService.createdTrelloCard(trelloMaper.mapToCardDto(trelloCard));
    }
}
