package aldenor.devout_catholic.controller;

import aldenor.devout_catholic.model.Question;
import aldenor.devout_catholic.services.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Autowired
    private AiService service;

    @PostMapping
    public String getReflexao(@RequestBody Question entity) {
        return service.getReflexao(entity.getQuestion());
    }
}
