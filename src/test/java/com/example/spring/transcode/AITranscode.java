package com.example.spring.transcode;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import java.io.IOException;
import java.net.URL;

public class AITranscode {

    public static void main(String[] args) throws BoilerpipeProcessingException, IOException {
        final URL url = new URL("https://www.bbc.com/news/world-us-canada-64460675");
        String content = CommonExtractors.ARTICLE_EXTRACTOR.getText(url);
        System.out.println(content);
    }


}
