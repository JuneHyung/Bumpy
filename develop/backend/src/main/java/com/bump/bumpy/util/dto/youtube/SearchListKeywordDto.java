package com.bump.bumpy.util.dto.youtube;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchListKeywordDto {
    /**
     * {
     *   "kind": "youtube#searchListResponse",
     *   "etag": "FRsYdRLmbIpq_fvyor6FdrITq3g",
     *   "nextPageToken": "CBkQAA",
     *   "regionCode": "KR",
     *   "pageInfo": {
     *     "totalResults": 1000000,
     *     "resultsPerPage": 25
     *   },
     *   "items": [
     *     {
     *       "kind": "youtube#searchResult",
     *       "etag": "LcttQshVj6DEFM7Xyh6DZY0OkB4",
     *       "id": {
     *         "kind": "youtube#video",
     *         "videoId": "hCMdVywETkU"
     *       },
     *       "snippet": {
     *         "publishedAt": "2023-08-21T19:00:19Z",
     *         "channelId": "UCo_q6aOlvPH7M-j_XGWVgXg",
     *         "title": "SURFING SKELETON BAY WITH KELLY SLATER &amp; EVERY PRO SURFER ON THE PLANET OF EARTH!",
     *         "description": "STAY PSYCHED MERCHANDISE: https://staypsyched.com - JAMIE O'BRIEN SURF SCHOOL: ...",
     *         "thumbnails": {
     *           "default": {
     *             "url": "https://i.ytimg.com/vi/hCMdVywETkU/default.jpg",
     *             "width": 120,
     *             "height": 90
     *           },
     *           "medium": {
     *             "url": "https://i.ytimg.com/vi/hCMdVywETkU/mqdefault.jpg",
     *             "width": 320,
     *             "height": 180
     *           },
     *           "high": {
     *             "url": "https://i.ytimg.com/vi/hCMdVywETkU/hqdefault.jpg",
     *             "width": 480,
     *             "height": 360
     *           }
     *         },
     *         "channelTitle": "Jamie O'Brien",
     *         "liveBroadcastContent": "none",
     *         "publishTime": "2023-08-21T19:00:19Z"
     *       }
     *     }
     *   ]
     * }
     */

    @JsonIgnore
    String kind;
    @JsonIgnore
    String etag;
    @JsonIgnore
    String nextPageToken;
    @JsonIgnore
    String regionCode;
    @JsonIgnore
    PageInfo pageInfo;
    Item[] items;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class PageInfo {
        int totalResults;
        int resultsPerPage;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Item {
        @JsonIgnore
        String kind;
        @JsonIgnore
        String etag;
        Id id;
        Snippet snippet;

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class Id {
            @JsonIgnore
            String kind;
            String videoId;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class Snippet {
            @JsonIgnore
            String publishedAt;
            @JsonIgnore
            String channelId;
            String title;
            String description;
            Thumbnails thumbnails;
            String channelTitle;
            @JsonIgnore
            String liveBroadcastContent;
            @JsonIgnore
            String publishTime;

            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            public static class Thumbnails {
                @JsonIgnore
                Default def;
                @JsonIgnore
                Medium medium;
                High high;

                @AllArgsConstructor
                @NoArgsConstructor
                @Data
                public static class Default {
                    String url;
                    int width;
                    int height;
                }

                @AllArgsConstructor
                @NoArgsConstructor
                @Data
                public static class Medium {
                    String url;
                    int width;
                    int height;
                }

                @AllArgsConstructor
                @NoArgsConstructor
                @Data
                public static class High {
                    String url;
                    @JsonIgnore
                    int width;
                    @JsonIgnore
                    int height;
                }
            }
        }
    }
}
