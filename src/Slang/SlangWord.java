package Slang;

import java.util.List;

public class SlangWord {
    private String keyword;
    private List<String> definitions;

    public SlangWord(String keyword,List<String> definitions){
        this.setKeyword(keyword);
        this.setDefinitions(definitions);
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
