package com.ch3xy.xronos.api;

import com.ch3xy.xronos.api.model.DayTO;
import com.ch3xy.xronos.api.model.TimeTrackingInfoTO;
import com.ch3xy.xronos.auth.model.User;
import com.ch3xy.xronos.auth.service.UserService;
import com.ch3xy.xronos.timetracking.model.Day;
import com.ch3xy.xronos.timetracking.service.DayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/timetracking")
public class TimeTrackingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTrackingController.class);

    private DayService dayService;

    private DayMapper dayMapper;

    private UserService userService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public TimeTrackingController(DayService dayService,
                                  DayMapper dayMapper,
                                  UserService userService) {
        this.dayService = dayService;
        this.dayMapper = dayMapper;
        this.userService = userService;
    }

    @RequestMapping(value = "/days", method = RequestMethod.GET)
    public ResponseEntity<TimeTrackingInfoTO> getDays(@RequestParam String from,
                                                      @RequestParam String to,
                                                      Principal principal) {
        LocalDate dateFrom;
        LocalDate dateTo;
        try {
            dateFrom = LocalDate.parse(from, formatter);
            dateTo = LocalDate.parse(to, formatter);
        } catch (DateTimeParseException e) {
            LOGGER.warn("incorrect dateFormat for dateString: {}", e.getParsedString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUser(principal.getName());
        List<Day> days = dayService.getDaysForUserBetween(
                user,
                Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(dateTo.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        TimeTrackingInfoTO infoTO = new TimeTrackingInfoTO.Builder()
                .days(dayMapper.mapDays(days))
                .build();
        return new ResponseEntity<>(infoTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/day/{targetDay}", method = RequestMethod.POST)
    public ResponseEntity<DayTO> createDay(@PathVariable String targetDay,
                                           @Valid @RequestBody DayTO day,
                                           Principal principal) {
        Day persitedDay = dayService.save(dayMapper.map(day, principal.getName()));
        return new ResponseEntity<>(dayMapper.map(persitedDay), HttpStatus.OK);
    }
}
