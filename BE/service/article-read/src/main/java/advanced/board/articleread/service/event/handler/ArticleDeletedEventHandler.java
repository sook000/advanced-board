package advanced.board.articleread.service.event.handler;

import advanced.board.articleread.repository.ArticleIdListRepository;
import advanced.board.articleread.repository.ArticleQueryModelRepository;
import advanced.board.articleread.repository.BoardArticleCountRepository;
import advanced.board.common.event.Event;
import advanced.board.common.event.EventType;
import advanced.board.common.event.payload.ArticleDeletedEventPayload;
import advanced.board.common.event.payload.ArticleUpdatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleDeletedEventHandler implements EventHandler<ArticleDeletedEventPayload> {
    private final ArticleIdListRepository articleIdListRepository;
    private final ArticleQueryModelRepository articleQueryModelRepository;
    private final BoardArticleCountRepository boardArticleCountRepository;

    @Override
    public void handle(Event<ArticleDeletedEventPayload> event) {
        ArticleDeletedEventPayload payload = event.getPayload();
        articleIdListRepository.delete(payload.getBoardId(), payload.getArticleId());
        articleQueryModelRepository.delete(payload.getArticleId());
        boardArticleCountRepository.createOrUpdate(payload.getBoardId(), payload.getBoardArticleCount());
    }

    @Override
    public boolean supports(Event<ArticleDeletedEventPayload> event) {
        return EventType.ARTICLE_DELETED == event.getType();
    }
}