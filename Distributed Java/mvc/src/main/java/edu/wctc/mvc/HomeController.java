package edu.wctc.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class HomeController {
    private Word[] words;

    @PostConstruct
    private void initWordData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            words = mapper.readValue(Paths.get("words.json").toFile(), Word[].class);
        }

        catch (IOException e) {
            e.printStackTrace();
            words = new Word[0];
        }
    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("words", words);
        return "index";
    }

    @RequestMapping("/definition/{word_index}")
    public String getDefinition(Model model, @PathVariable int word_index) {
        model.addAttribute("word", words[word_index]);
        return "definition";
    }
}