package issueabroad.first.controller;

import issueabroad.first.dto.ScrapReplyDTO;
import issueabroad.first.dto.UserReplyDTO;
import issueabroad.first.service.ScrapReplyService;
import issueabroad.first.service.UserReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {

    private final UserReplyService userReplyService;
    private final ScrapReplyService scrapReplyService;

    @GetMapping(value = "/user/{uno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserReplyDTO>> getListByUserBoard(@PathVariable("uno") Long uno) {

        return new ResponseEntity<>(userReplyService.getList(uno), HttpStatus.OK);
    }

    @GetMapping(value = "/scrap/{sno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScrapReplyDTO>> getListByScrapBoard(@PathVariable("sno") Long sno) {

        return new ResponseEntity<>(scrapReplyService.getList(sno), HttpStatus.OK);
    }

    @PostMapping("/scrap/register")
    public ResponseEntity<Long> scrapRegister(@RequestBody ScrapReplyDTO scrapReplyDTO) {
        Long srno = scrapReplyService.register(scrapReplyDTO);

        return new ResponseEntity<>(srno, HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<Long> userRegister(@RequestBody UserReplyDTO userReplyDTO) {

        Long urno = userReplyService.register(userReplyDTO);

        return new ResponseEntity<>(urno, HttpStatus.OK);
    }

    @DeleteMapping("/scrap/{srno}")
    public ResponseEntity<String> scrapRemove(@PathVariable("srno") Long srno) {
        scrapReplyService.remove(srno);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
