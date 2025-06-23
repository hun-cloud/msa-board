package msa.board.common.event;

import lombok.Getter;
import msa.board.common.dataserializer.DataSerializer;

@Getter
public class Event<T extends EventPayload> {
    private Long eventId;
    private EventType eventType;
    private T payload;

    public static Event<EventPayload> of(Long eventId, EventType eventType, EventPayload payload) {
        Event<EventPayload> event = new Event<>();
        event.eventId = eventId;
        event.eventType = eventType;
        event.payload = payload;
        return event;
    }

    public String toJson() {
        return DataSerializer.serialize(this);
    }

    public static Event<EventPayload> fromJson(String json) {
        
    }
}
