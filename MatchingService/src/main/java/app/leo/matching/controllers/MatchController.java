package app.leo.matching.controllers;

import app.leo.matching.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
    @Autowired
    private MatchService matchService;
}
