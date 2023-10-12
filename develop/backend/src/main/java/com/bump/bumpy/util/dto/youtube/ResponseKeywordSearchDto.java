package com.bump.bumpy.util.dto.youtube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseKeywordSearchDto {

    List<detail> detailList;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class detail {
        String videoId;
        String title;
        String description;
        String thumbnail;
        String channelTitle;

        public String getVideoId() {
            return "https://www.youtube.com/watch?v=" + videoId;
        }
    }

    public ResponseKeywordSearchDto(SearchListKeywordDto result) {
        detailList = new ArrayList<>();
        for (SearchListKeywordDto.Item item : result.items) {
            detail tmp = detail.builder()
                    .videoId(item.getId().getVideoId())
                    .title(item.getSnippet().getTitle())
                    .description(item.getSnippet().getDescription())
                    .thumbnail(item.getSnippet().getThumbnails().getHigh().getUrl())
                    .channelTitle(item.getSnippet().getChannelTitle())
                    .build();
            detailList.add(tmp);
        }
    }
}
