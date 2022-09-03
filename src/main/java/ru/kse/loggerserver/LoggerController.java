package ru.kse.loggerserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoggerController {
  private final static Logger LOGGER = LoggerFactory.getLogger(LoggerController.class);
  @GetMapping("/log")
  public void log(@RequestParam(value = "name", defaultValue = "defaultLog") String name) {
    LOGGER.info("== {} == {} ==", name, new Date());
  }
}
