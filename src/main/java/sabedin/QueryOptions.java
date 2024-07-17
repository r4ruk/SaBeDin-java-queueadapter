package sabedin;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class QueryOptions {
    @JsonProperty("paging_information")
    private PagingInformation pagingInformation;

    @JsonProperty("queries")
    private List<Query> queries;

    @JsonProperty("sorting_information")
    private SortingInformation sortingInformation;

    public static class PagingInformation {
        @JsonProperty("amount_of_items")
        private int amountOfItems;

        @JsonProperty("page_num")
        private int pageNum;

        // Getters and setters
    }

    public static class Query {
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String equals;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String startsWith;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String endsWith;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String contains;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String biggerThan;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String smallerThan;

        // Getters and setters
    }

    public static class SortingInformation {
        @JsonProperty("Ascending")
        private List<String> ascending;

        @JsonProperty("Descending")
        private List<String> descending;

        // Getters and setters
    }

    // Getters and setters
}
