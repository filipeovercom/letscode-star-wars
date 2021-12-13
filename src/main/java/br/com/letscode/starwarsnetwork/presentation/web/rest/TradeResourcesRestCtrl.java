package br.com.letscode.starwarsnetwork.presentation.web.rest;

import br.com.letscode.starwarsnetwork.application.service.TradeResourcesService;
import br.com.letscode.starwarsnetwork.domain.model.dto.TradeResourcesRequest;
import br.com.letscode.starwarsnetwork.domain.model.dto.TradeResourcesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/trade")
@RequiredArgsConstructor
public class TradeResourcesRestCtrl {

    private final TradeResourcesService tradeResourcesService;

    @PostMapping
    public TradeResourcesResponse tradeResources(
            @RequestBody @Valid TradeResourcesRequest request) {
        return tradeResourcesService.tradeResources(request);
    }
}
