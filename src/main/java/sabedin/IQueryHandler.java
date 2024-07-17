package sabedin;

import java.util.List;

public interface IQueryHandler {
    List<String> handle(RequestPostBody body);
}
