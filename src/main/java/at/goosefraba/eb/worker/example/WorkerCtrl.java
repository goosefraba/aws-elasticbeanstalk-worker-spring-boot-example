package at.goosefraba.eb.worker.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright goosefraba.at
 * Created by goosefraba on 28/06/2017.
 */
@RestController
public class WorkerCtrl {
    private static final Logger LOG = LogManager.getLogger(WorkerCtrl.class);

    @RequestMapping(value = "/")
    @ResponseBody
    public String index() {
        return "Welcome to the ElasticBeanstalk SpringBoot example";
    }

    @RequestMapping(value = "/sqs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> onSqsMessage(
            @RequestHeader(value = "User-Agent", required = false) String sqsdMessageUserAgent,
            @RequestHeader(value = "X-Aws-Sqsd-Msgid", required = false) String sqsdMessageId,
            @RequestHeader(value = "X-Aws-Sqsd-Queue", required = false) String sqsdMessageQueueName,
            @RequestHeader(value = "X-Aws-Sqsd-First-Received-At", required = false) String sqsdMessageReceivedTimestamp,
            @RequestHeader(value = "X-Aws-Sqsd-Receive-Count", required = false) int sqsdMessageCounts,
            @RequestHeader(value = "Content-Type", required = false) String sqsdMessageContentType,
            @RequestHeader(value = "X-Aws-Sqsd-Taskname", required = false) String sqsdMessagePeriodicTaskName,
            @RequestHeader(value = "X-Aws-Sqsd-Attr-(message-attribute-name)", required = false) String sqsdMessageCustomAttribute,
            @RequestHeader(value = "X-Aws-Sqsd-Scheduled-At", required = false) String sqsdMessageTaskSchdeuleTime,
            @RequestHeader(value = "X-Aws-Sqsd-Sender-Id", required = false) String sqsdMessageSenderId,
            @RequestBody String sqsdMessageBody) {

        LOG.info("User-Agent: {}", sqsdMessageUserAgent);
        LOG.info("Content-Type: {}", sqsdMessageContentType);
        LOG.info("Message-Body: {}", sqsdMessageBody);
        LOG.info("X-Aws-Sqsd-Msgid: {}", sqsdMessageId);
        LOG.info("X-Aws-Sqsd-Taskname: {}", sqsdMessagePeriodicTaskName);
        LOG.info("X-Aws-Sqsd-Queue: {}", sqsdMessageQueueName);
        LOG.info("X-Aws-Sqsd-First-Received-At: {}", sqsdMessageReceivedTimestamp);
        LOG.info("X-Aws-Sqsd-Receive-Count: {}", sqsdMessageCounts);
        LOG.info("X-Aws-Sqsd-Attr-(message-attribute-name): {}", sqsdMessageCustomAttribute);
        LOG.info("X-Aws-Sqsd-Scheduled-At: {}", sqsdMessageTaskSchdeuleTime);
        LOG.info("X-Aws-Sqsd-Sender-Id: {}", sqsdMessageSenderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
