package advanced.board.articleread.service.event.handler;

import advanced.board.common.event.Event;
import advanced.board.common.event.EventPayload;

public interface EventHandler<T extends EventPayload> {
    void handle(Event<T> event);
    boolean supports(Event<T> event);
}
